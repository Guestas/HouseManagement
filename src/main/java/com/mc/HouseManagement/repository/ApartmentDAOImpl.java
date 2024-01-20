package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    public Apartment getApartmentById(Long id) {
        try {
            TypedQuery<Apartment> query = entityManager.createQuery(
                    "select a from Apartment a "
                            + "WHERE a.id = :data", Apartment.class);

            query.setParameter("data", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Apartment> loadAllApartments() {
        TypedQuery<Apartment> query = entityManager
                .createQuery("SELECT a FROM Apartment a", Apartment.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public int deleteAllApartments() {
        Query query = entityManager.createQuery("DELETE FROM Apartment");
        return query.executeUpdate();
    }
}
