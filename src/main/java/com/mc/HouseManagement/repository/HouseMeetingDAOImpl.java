package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;
import jakarta.persistence.EntityManager;
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
    public Long addHouseMeeting(HouseMeeting houseMeeting) {
        return entityManager.merge(houseMeeting).getId();
    }

    @Override
    public HouseMeeting getHouseMeetingById(Long id) {
        return entityManager.find(HouseMeeting.class, id);
    }

    @Override
    public List<HouseMeeting> loadAllHouseMeetings() {
        TypedQuery<HouseMeeting> query = entityManager
                .createQuery("SELECT h FROM HouseMeeting h", HouseMeeting.class);
        List<HouseMeeting> result = query.getResultList();
        return result.isEmpty()?null:result;
    }

    @Override
    @Transactional
    public int deleteAllHouseMeetings() {
        Query query = entityManager.createQuery("DELETE FROM HouseMeeting");
        return query.executeUpdate();
    }
}
