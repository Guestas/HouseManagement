package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
            TypedQuery<HouseMeeting> query = entityManager.createQuery(
                    "select hm from HouseMeeting hm "
                            + "JOIN FETCH hm.apartments "
                            + "where hm.id = :data", HouseMeeting.class);

            query.setParameter("data", houseMeetingId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return entityManager.find(HouseMeeting.class, houseMeetingId);
        }
    }

    @Override
    public List<HouseMeeting> getAllHouseMeetings() {
        TypedQuery<HouseMeeting> query = entityManager
                .createQuery("SELECT h FROM HouseMeeting h", HouseMeeting.class);
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
        Query query = entityManager.createQuery("DELETE FROM HouseMeeting");
        return query.executeUpdate();
    }
}
