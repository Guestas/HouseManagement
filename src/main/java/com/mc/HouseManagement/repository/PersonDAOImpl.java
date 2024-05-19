package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO{

    private final EntityManager entityManager;
    @Autowired
    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Long addUpdatePerson(Person person) {
        return entityManager.merge(person).getId();
    }

    @Override
    public Person getPersonByIdAndType(Long id, String personType) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> getPersonsByApartmentsIdAndType(Long apartmentId, String personType) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> personRoot = criteriaQuery.from(Person.class);
            Join<Person, Apartment> apartmentJoin = personRoot.join("apartments");

            criteriaQuery.select(personRoot)
                    .where(criteriaBuilder.equal(apartmentJoin.get("id"), apartmentId));

            TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (NoResultException e) {
            // Handle the case where no data is found
            return new ArrayList<>();
        }
    }

    @Override
    public List<Person> getAllPersonsByType(String personType) {
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p " +
                "WHERE type=:theData", Person.class);
        return query.setParameter("theData", personType).getResultList();
    }

    @Override
    @Transactional
    public Long deletePersonById(Long personId) {
        Person entityToRemove = getPersonById(personId);

        entityManager.remove(entityToRemove);
        Person checkEntity = getPersonById(personId);

        return checkEntity == null?entityToRemove.getId():-1;
    }


    @Override
    @Transactional
    public int deleteAllPersons() {
        Query query = entityManager.createQuery("DELETE FROM Person");
        return query.executeUpdate();
    }

    @Override
    public List<Person> getPersonByLastOrFirstNameAndType(String oneOfNames, String personType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        Predicate lastNamePredicate = criteriaBuilder.equal(personRoot.get("lastName"), oneOfNames);
        Predicate firstNamePredicate = criteriaBuilder.equal(personRoot.get("firstName"), oneOfNames);
        Predicate personTypePredicate = criteriaBuilder.equal(personRoot.get("type"), personType);

        Predicate nameOrTypePredicate = criteriaBuilder.or(lastNamePredicate, firstNamePredicate);
        Predicate finalPredicate = criteriaBuilder.and(nameOrTypePredicate, personTypePredicate);

        criteriaQuery.select(personRoot).where(finalPredicate);

        TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Person getPersonById(Long id) {

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> personRoot = criteriaQuery.from(Person.class);

            criteriaQuery.select(personRoot)
                    .where(criteriaBuilder.equal(personRoot.get("id"), id));

            TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }



        /*try {
            TypedQuery<Person> query = entityManager
                    .createQuery("SELECT p.person_type FROM Person p WHERE id=:theData", Person.class);
            return query.setParameter("theData", id).getSingleResult();
        } catch (DataNotFoundException e){
            return null;
        }*/
    }

}
