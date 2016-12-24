package com.klevytska.dm.rest;

import com.klevytska.dm.model.MessageMapping;
import com.klevytska.dm.regisrator.MessageMappingRegistrator;
import com.klevytska.dm.repository.MessageMappingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@RequestScoped
@Path("/message_mapping")
public class MessageMappingREST {
    @Inject
    private Logger logger;

    @Inject
    private MessageMappingRepository repository;

    @Inject
    private MessageMappingRegistrator registrator;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MessageMapping> listAll(){
        logger.info("List of all users");
        List<MessageMapping> resultList = repository.getAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MessageMapping getByIdUser(@PathParam("id") long id){
        return repository.getById(id);
    }

}
