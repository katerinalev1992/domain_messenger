package com.klevytska.dm.regisrator;

import com.klevytska.dm.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@Stateless
public class UserRegistrator{

    @Inject
    private Logger logger;

    @Inject
    private EntityManager entityManager;

    public void create(User item) throws Exception {
        logger.info("Registered: " + item);
        entityManager.merge(item);
    }


    public void update(User item) throws Exception {
        logger.info("Updated: " + item);
        entityManager.merge(item);
    }

    public void delete(User item) throws Exception {
        logger.info("Deleted: " + item);
        entityManager.remove(item);
    }
}
