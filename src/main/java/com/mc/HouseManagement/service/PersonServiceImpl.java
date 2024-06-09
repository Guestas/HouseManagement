package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.api.dto.person.ReturnMultiplePersonsForApartment;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final ApartmentDAO apartmentDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO, ApartmentDAO apartmentDAO) {
        this.personDAO = personDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public Long addUpdatePerson(AddUpdatePerson addUpdatePerson) {
        Long personId = personDAO.addUpdatePerson(addUpdatePerson.getPersonWitType());
        addUpdatePerson.getApartmentNumber().forEach(a->addApartmentToPerson(personId, Long.parseLong(a)));
        return personId;
    }

    @Override
    public Person getPersonByIdAndType(Long personId, String personType) {
        return personDAO.getPersonByIdAndType(personId, personType);
    }

    @Override
    public List<ReturnMultiplePersonsForApartment> getPersonsByApartmentsIdAndType(Long apartmentId, String personType) {
        return personDAO.getPersonsByApartmentsIdAndType(apartmentId, personType).stream()
                .map(ReturnMultiplePersonsForApartment::new)
                .toList();
    }


    @Override
    public List<Person> getAllPersonsByType(String personType) {
        if (personType.equals(Person.PERSON)){
            List<Person> connectedPersons = personDAO.getAllPersonsByType(Person.USER);
            connectedPersons.addAll(personDAO.getAllPersonsByType(Person.OWNER));
            return connectedPersons;
        }else {
            return personDAO.getAllPersonsByType(personType);
        }
    }

    @Override
    public Long deletePersonById(Long id) {
        return personDAO.deletePersonById(id);
    }

    @Override
    public List<Person> getPersonByLastOrFirstNameAndType(String oneOfNames, String personType) {
        return personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, personType);
    }

    @Override
    public List<Person> getPersonByLastOrFirstName(String oneOfNames) {
        List<Person> connectedPersons = new ArrayList<>(personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, Person.USER));
        connectedPersons.addAll(personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, Person.OWNER));
        return connectedPersons;
    }

    @Override
    public Person getPersonById(Long id) {
        return personDAO.getPersonById(id);
    }

    @Override
    public Long addApartmentToPerson(Long personID, Long apartmentId) {
        Person person = personDAO.getPersonById(personID);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);
        if (person==null)
            return -1L;
        else if (apartment == null)
            return -2L;
        else
            person.addApartment(apartment);
        return personDAO.addUpdatePerson(person);
    }

    @Override
    public Long delApartmentFromPerson(Long personID, Long apartmentId) {
        Person person = personDAO.getPersonById(personID);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);
        if (person==null)
            return -1L;
        else if (apartment == null)
            return -2L;
        else
            person.delApartment(apartment);
        return personDAO.addUpdatePerson(person);
    }

}
