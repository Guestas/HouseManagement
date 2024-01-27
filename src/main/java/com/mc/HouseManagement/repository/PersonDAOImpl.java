package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
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
        return entityManager.merge(person).getId();
    }

    @Override
    public <T extends Person> T getPersonByIdAndType(Long id, Class<T> personTClass) {
        return entityManager.find(personTClass, id);
    }

    @Override
    public <T extends Person> List<T> getPersonsByApartmentsIdAndType(Long apartmentId, Class<T> personTClass) {
        try {
            TypedQuery<T> query = entityManager.createQuery("SELECT p FROM " +
                    personTClass.getCanonicalName() +
                    " p JOIN FETCH p.apartments a WHERE a.id=:theData", personTClass);
            return query.setParameter("theData", apartmentId).getResultList();
        } catch (DataNotFoundException e){
            return new ArrayList<>();
        }
    }

    @Override
    public <T extends Person> List<T> getAllPersonsByClassType(Class<T> personTClass) {
        TypedQuery<T> query = entityManager
                .createQuery("SELECT t FROM " +
                                personTClass.getCanonicalName() +
                                " t",
                        personTClass);
        return query.getResultList();
    }

    @Override
    @Transactional
    public <T extends Person> Long deletePersonById(Long personId) {
        T entityToRemove = getPersonById(personId);

        entityManager.remove(entityToRemove);
        T checkEntity = getPersonById(personId);

        return checkEntity == null?entityToRemove.getId():-1;
    }


    @Override
    @Transactional
    public int deleteAllPersons() {
        Query query = entityManager.createQuery("DELETE FROM Person");
        return query.executeUpdate();
    }

    @Override
    public <T extends Person> List<T> getPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> personTClass) {
        try {
            TypedQuery<T> query = entityManager.createQuery("SELECT p FROM " +
                    personTClass.getCanonicalName() +
                    " p WHERE lastName=:theData or " +
                    "firstName=:theData", personTClass);
            return query.setParameter("theData", oneOfNames).getResultList();
        } catch (DataNotFoundException e){
            return new ArrayList<>();
        }
    }

    @Override
    public <T extends Person> T getPersonById(Long id) {
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
