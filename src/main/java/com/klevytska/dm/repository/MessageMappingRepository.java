package com.klevytska.dm.repository;

import com.klevytska.dm.model.Message;
import com.klevytska.dm.model.MessageMapping;
import com.klevytska.dm.model.User;

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
public class MessageMappingRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    Logger logger;

    public List<MessageMapping> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageMapping> criteria = criteriaBuilder.createQuery(MessageMapping.class);
        @SuppressWarnings("unused")
        Root<MessageMapping> element = criteria.from(MessageMapping.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public MessageMapping getById(long id){
        logger.info("Get by id: " + id);
        return entityManager.find(MessageMapping.class, id);
    }

    public List<MessageMapping> getByFromId(User from_id){
        logger.info("Get by from_id");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageMapping> criteriaQuery = criteriaBuilder.createQuery(MessageMapping.class);
        Root<MessageMapping> element = criteriaQuery.from(MessageMapping.class);
        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("from_id"), from_id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<MessageMapping> getByToId(User to_id){
        logger.info("Get by to_id");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageMapping> criteriaQuery = criteriaBuilder.createQuery(MessageMapping.class);
        Root<MessageMapping> element = criteriaQuery.from(MessageMapping.class);
        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("to_id"), to_id));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public MessageMapping getByMessageId(Message message_id){
        logger.info("Get by message id");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageMapping> criteriaQuery = criteriaBuilder.createQuery(MessageMapping.class);
        Root<MessageMapping> element = criteriaQuery.from(MessageMapping.class);
        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("message_id"), message_id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
