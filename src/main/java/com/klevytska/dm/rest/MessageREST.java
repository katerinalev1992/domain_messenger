package com.klevytska.dm.rest;

import com.klevytska.dm.model.Message;
import com.klevytska.dm.regisrator.MessageRegistrator;
import com.klevytska.dm.repository.MessageRepository;

import javax.enterprise.context.ApplicationScoped;
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
@ApplicationScoped
@Path("/message")
public class MessageREST {
    @Inject
    private Logger logger;

    @Inject
    private MessageRepository repository;

    @Inject
    private MessageRegistrator registrator;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> listAll(){
        logger.info("List of all users");
        List<Message> resultList = repository.getAll();
        return resultList;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getByIdUser(@PathParam("id") long id){
        return repository.getById(id);
    }
}
