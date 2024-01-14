package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO{

    private final EntityManager entityManager;

    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <T extends Person> Long addPerson(T person) {
        return entityManager.merge(person).getId();
    }

    @Override
    public <T extends Person> T getPersonById(Long id, Class<T> person) {
        return entityManager.find(person, id);
    }

    @Override
    public <T extends Person> List<T> loadAllPersons(Class<T> person) {
        TypedQuery<T> query = entityManager
                .createQuery("SELECT t FROM " +
                                person.getCanonicalName() +
                                " t",
                        person);
        List<T> result = query.getResultList();
        return result.isEmpty()?null:result;
    }


    @Override
    @Transactional
    public int deleteAllPersons() {
        Query query = entityManager.createQuery("DELETE FROM Person");
        return query.executeUpdate();
    }
}
