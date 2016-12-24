package com.klevytska.dm.rest;

import com.klevytska.dm.model.User;
import com.klevytska.dm.regisrator.UserRegistrator;
import com.klevytska.dm.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

}
