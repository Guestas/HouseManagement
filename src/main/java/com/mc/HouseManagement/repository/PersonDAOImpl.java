package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.api.adedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO{

    private final EntityManager entityManager;

    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <T extends Person> Long addUpdatePerson(T person) {
        /*if (person.getId() == null)
            return entityManager.merge(person).getId();
        else {
            var existingPerson = getPersonById(person.getId(), person.getClass());
            existingPerson.setApartments(person.getApartments());
            return entityManager.merge(existingPerson).getId();
        }*/
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
        return query.getResultList();
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
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass) {
        try {
            TypedQuery<T> query = entityManager.createQuery("SELECT p FROM " +
                    tClass.getCanonicalName() +
                    " p WHERE lastName=:theData or " +
                    "firstName=:theData", tClass);
            return query.setParameter("theData", oneOfNames).getResultList();
        } catch (DataNotFoundException e){
            return new ArrayList<>();
        }
    }

    @Override
    public <T extends Person> T loadPersonByID(Long id) {
        try {
            String queryString = "SELECT p.person_type FROM person p WHERE id=:theData";
            Query nativeQuery = entityManager.createNativeQuery(queryString);
            var li = nativeQuery.setParameter("theData", id).getResultList();
            if (!li.isEmpty()){
                var out = switch (li.get(0).toString()){
                    case "Owner" -> entityManager.find(Owner.class, id);
                    case "User" -> entityManager.find(User.class, id);
                    case "Sold_moved_out" -> entityManager.find(SoldMovedOut.class, id);
                    default -> null;
                };
                return (T) out;
            }
            return null;
        } catch (DataNotFoundException e){
            return null;
        }
    }

}
