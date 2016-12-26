package com.klevytska.dm.rest;

import com.klevytska.dm.model.CheckResponse;
import com.klevytska.dm.model.User;
import com.klevytska.dm.model.UserCredentials;
import com.klevytska.dm.regisrator.UserRegistrator;
import com.klevytska.dm.repository.UserRepository;
import com.klevytska.dm.utils.ActiveDirectoryAuth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@Path("/user")
@RequestScoped
public class UserREST {

    @Inject
    private Logger logger;

    @Inject
    private UserRepository repository;

    @Inject
    private UserRegistrator registrator;

    @Inject
    ActiveDirectoryAuth authService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listAll(){
        logger.info("List of all users");
        List<User> resultList = repository.getAll();
        Collections.sort(resultList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getDomain_name().compareTo(o2.getDomain_name());
            }
        });
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByIdUser(@PathParam("id") long id){
        return repository.getById(id);
    }

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CheckResponse checkWhetherUserInGroup(UserCredentials user) {
        logger.info("Checking user" + user.getUsername());
        CheckResponse response = new CheckResponse();
        boolean isUserInGroup = authService.checkIfUserBelongsTOGroup(user.getUsername(), user.getPassword());
        response.setResult(isUserInGroup);
        if(isUserInGroup) {
            createEmployeeIfNotExist(user.getUsername());
        }
        return response;
    }

    private void createEmployeeIfNotExist(String domainName) {
        if(!idAlreadyExists(domainName)) {
            String userMail = domainName + "@gk-software.com";
            User employee = new User();
            employee.setDomain_name(domainName);
            employee.setNick_name(userMail);
            try {
                registrator.create(employee);
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
    }

    private boolean idAlreadyExists(String domainName) {
        User element = null;
        try {
            element = repository.getByDomainName(domainName);
        } catch (NoResultException e) {
            // ignore
        }
        return element != null;
    }

}
