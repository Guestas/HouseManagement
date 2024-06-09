package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.entity.HouseMeeting_;
import com.mc.HouseManagement.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**Description**/
@Repository
public class ApartmentDAOImpl implements ApartmentDAO{

    private final EntityManager entityManager;
    @Autowired
    public ApartmentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Long addUpdateApartment(Apartment apartment) {
        return entityManager.merge(apartment).getId();
    }

    @Override
    public Apartment getApartmentById(Long apartmentId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
            //Select what:
            Root<Apartment> houseMeetingRoot = criteriaQuery.from(Apartment.class);

            //Conditions:
            criteriaQuery.select(houseMeetingRoot).where(criteriaBuilder.equal(houseMeetingRoot.get(HouseMeeting_.ID), apartmentId));

            TypedQuery<Apartment> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Apartment> getAllApartments() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> personRoot = criteriaQuery.from(Apartment.class);


        TypedQuery<Apartment> query = entityManager.createQuery(criteriaQuery.where(criteriaBuilder.conjunction()));

        return query.getResultList();
    }

    @Override
    @Transactional
    public int deleteAllApartments() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Apartment> criteriaDelete = criteriaBuilder.createCriteriaDelete(Apartment.class);
        Root<Apartment> personRoot = criteriaDelete.from(Apartment.class);
        criteriaDelete.where(criteriaBuilder.conjunction());
        Query query = entityManager.createQuery(criteriaDelete);
        return query.executeUpdate();
    }
}
