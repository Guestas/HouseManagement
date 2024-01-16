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
    public <T extends Person> T getPersonById(Long id, Class<T> tClass) {
        return entityManager.find(tClass, id);
    }

    @Override
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass) {
        TypedQuery<T> query = entityManager
                .createQuery("SELECT t FROM " +
                                tClass.getCanonicalName() +
                                " t",
                        tClass);
        List<T> result = query.getResultList();
        return result.isEmpty()?null:result;
    }

    @Override
    @Transactional
    public <T extends Person> Long deleteById(Long id, Class<T> tClass) {
        // Assuming entityManager is your EntityManager instance

        // Find the entity you want to remove
        T entityToRemove = entityManager.find(tClass, id);

        // Remove the entity
        entityManager.remove(entityToRemove);
        // Check if the entity is still present in the database
        T checkEntity = entityManager.find(tClass, id);

        return checkEntity == null?entityToRemove.getId():null;
    }


    @Override
    @Transactional
    public int deleteAllPersons() {
        Query query = entityManager.createQuery("DELETE FROM Person");
        return query.executeUpdate();
    }

    @Override
    public <T extends Person> List<T> loadPersonByLastOrFirstName(String oneOfNames, Class<T> tClass) {
        TypedQuery<T> query1 = entityManager.createQuery("SELECT p FROM " +
                tClass.getCanonicalName() +
                " p WHERE firstName=:theData", tClass);
        query1.setParameter("theData", oneOfNames);

        TypedQuery<T> query2 = entityManager.createQuery("SELECT p FROM " +
                tClass.getCanonicalName() +
                " p WHERE lastName=:theData", tClass);
        query2.setParameter("theData", oneOfNames);
        List<T> out = query1.getResultList();
        out.addAll(query2.getResultList());

        return out;
    }
}
