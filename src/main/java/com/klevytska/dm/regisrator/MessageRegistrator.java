package com.klevytska.dm.regisrator;

import com.klevytska.dm.model.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@Stateless
public class MessageRegistrator {

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public void create(Message item) throws Exception {
        logger.info("Registered: " + item);
        entityManager.merge(item);
    }


    public void update(Message item) throws Exception {
        logger.info("Updated: " + item);
        entityManager.merge(item);
    }

    public void delete(Message item) throws Exception {
        logger.info("Deleted: " + item);
        entityManager.remove(item);
    }
}
