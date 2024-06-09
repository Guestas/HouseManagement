package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.entity.HouseMeeting_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseMeetingDAOImpl implements HouseMeetingDAO{

    private final EntityManager entityManager;
    @Autowired
    public HouseMeetingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting) {
        return entityManager.merge(houseMeeting).getId();
    }

    @Override
    public HouseMeeting getHouseMeetingById(Long houseMeetingId) {
        try {
            // these three are important for filtering abd queries
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<HouseMeeting> criteriaQuery = criteriaBuilder.createQuery(HouseMeeting.class);
            Root<HouseMeeting> houseMeetingRoot = criteriaQuery.from(HouseMeeting.class);

            Predicate idPredicate = criteriaBuilder.equal(houseMeetingRoot.get(HouseMeeting_.ID), houseMeetingId);
            criteriaQuery.select(houseMeetingRoot).where(idPredicate);
            TypedQuery<HouseMeeting> query = entityManager.createQuery(criteriaQuery);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return entityManager.find(HouseMeeting.class, houseMeetingId);
        }
    }

    @Override
    public List<HouseMeeting> getAllHouseMeetings() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<HouseMeeting> criteriaQuery = criteriaBuilder.createQuery(HouseMeeting.class);
        Root<HouseMeeting> houseMeetingRoot = criteriaQuery.from(HouseMeeting.class);

        criteriaQuery.where(criteriaBuilder.conjunction());

        TypedQuery<HouseMeeting> query = entityManager
                .createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Long deleteHouseMeetingByIdById(Long id) {
        HouseMeeting entityToRemove = getHouseMeetingById(id);

        entityManager.remove(entityToRemove);
        HouseMeeting checkEntity = getHouseMeetingById(id);
        return checkEntity == null?entityToRemove.getId():-1;
    }

    @Override
    @Transactional
    public int deleteAllHouseMeetings() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<HouseMeeting> criteriaDelete = criteriaBuilder.createCriteriaDelete(HouseMeeting.class);
        Root<HouseMeeting> houseMeetingRoot = criteriaDelete.from(HouseMeeting.class);

        criteriaDelete.where(criteriaBuilder.conjunction());

        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
