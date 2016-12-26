package com.klevytska.dm.repository;

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
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    @Inject
    Logger logger;

    public User getById(long id){
        logger.info("Get by id: " + id);
        return entityManager.find(User.class, id);
    }

    public User getByDomainName(String domainName){
        logger.info("Get by domainName: " + domainName);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> element = criteriaQuery.from(User.class);
        criteriaQuery.select(element).where(criteriaBuilder.equal(element.get("domain_name"), domainName));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<User> getAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        @SuppressWarnings("unused")
        Root<User> element = criteria.from(User.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}
