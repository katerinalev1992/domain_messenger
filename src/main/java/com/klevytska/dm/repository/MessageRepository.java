package com.klevytska.dm.repository;

import com.klevytska.dm.model.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by katerynalevytska on 21.12.16.
 */
@ApplicationScoped
public class MessageRepository {
    @Inject
    private EntityManager entityManager;

    @Inject
    Logger logger;

    public Message getById(long id){
        logger.info("Get by id: " + id);
        return entityManager.find(Message.class, id);
    }

    public List<Message> getAll(){
         CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
         CriteriaQuery<Message> criteria = criteriaBuilder.createQuery(Message.class);
         @SuppressWarnings("unused")
         Root<Message> element = criteria.from(Message.class);
         return entityManager.createQuery(criteria).getResultList();
    }
}
