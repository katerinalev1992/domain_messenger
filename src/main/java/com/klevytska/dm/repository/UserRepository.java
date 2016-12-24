package com.klevytska.dm.repository;

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
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    Logger logger;

    public User getById(long id){
        logger.info("Get by id: " + id);
        return entityManager.find(User.class, id);
    }

    public List<User> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        @SuppressWarnings("unused")
        Root<User> element = criteria.from(User.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}
