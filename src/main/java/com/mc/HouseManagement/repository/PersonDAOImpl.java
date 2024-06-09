package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.Apartment_;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.Person_;
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

            // Define the root
            Root<Person> personRoot = criteriaQuery.from(Person.class);

            // Define the join
            Join<Person, Apartment> apartmentJoin = personRoot.join(Person_.APARTMENTS);

            // Specify the criteria
            criteriaQuery.select(personRoot)
                    .distinct(true) // To avoid duplicates if needed
                    .where(
                            criteriaBuilder.and(
                                    criteriaBuilder.equal(personRoot.get(Person_.TYPE), personType),
                                    criteriaBuilder.equal(apartmentJoin.get(Person_.ID), apartmentId)
                            )
                    );

            // Create and execute the query
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (NoResultException e) {
            // Handle the case where no data is found
            return new ArrayList<>();
        }
    }

    @Override
    public List<Person> getAllPersonsByType(String personType) {

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> personRoot = criteriaQuery.from(Person.class);

            criteriaQuery.select(personRoot)
                    .where(criteriaBuilder.equal(personRoot.get(Person_.TYPE), personType));

            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (NoResultException e){
            return null;
        }
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Person> criteriaDelete = criteriaBuilder.createCriteriaDelete(Person.class);
        Root<Person> personRoot = criteriaDelete.from(Person.class);

        criteriaDelete.where(criteriaBuilder.conjunction());

        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    public List<Person> getPersonByLastOrFirstNameAndType(String oneOfNames, String personType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        Predicate lastNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.LAST_NAME), oneOfNames);
        Predicate firstNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.FIRST_NAME), oneOfNames);
        Predicate personTypePredicate = criteriaBuilder.equal(personRoot.get(Person_.TYPE), personType);

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
                    .where(criteriaBuilder.equal(personRoot.get(Person_.ID), id));

            TypedQuery<Person> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

}
