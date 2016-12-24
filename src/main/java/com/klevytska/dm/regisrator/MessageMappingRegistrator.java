package com.klevytska.dm.regisrator;

import com.klevytska.dm.model.MessageMapping;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@Stateless
public class MessageMappingRegistrator {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public void create(MessageMapping item) throws Exception {
        logger.info("Registered: " + item);
        entityManager.merge(item);
    }


    public void update(MessageMapping item) throws Exception {
        logger.info("Updated: " + item);
        entityManager.merge(item);
    }

    public void delete(MessageMapping item) throws Exception {
        logger.info("Deleted: " + item);
        entityManager.remove(item);
    }
}
