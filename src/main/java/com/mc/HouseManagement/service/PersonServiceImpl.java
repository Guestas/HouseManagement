package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.ReturnMultiplePersonsForApartment;
import com.mc.HouseManagement.entity.*;
import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;
    private final ApartmentDAO apartmentDAO;

    public PersonServiceImpl(PersonDAO personDAO, ApartmentDAO apartmentDAO) {
        this.personDAO = personDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public Long addUpdatePerson(AddUpdatePerson AddUpdatePerson) {
        return personDAO.addUpdatePerson(AddUpdatePerson.getPersonWitType());
    }

    @Override
    public <T extends Person> T getPersonByIdAndType(Long personId, Class<T> personTClass) {
        return personDAO.getPersonByIdAndType(personId, personTClass);
    }

    @Override
    public <T extends Person> List<ReturnMultiplePersonsForApartment> getPersonsByApartmentsIdAndType(Long apartmentId, Class<T> personTClass) {
        return personDAO.getPersonsByApartmentsIdAndType(apartmentId, personTClass).stream()
                .map(ReturnMultiplePersonsForApartment::new)
                .toList();
    }


    @Override
    public <T extends Person> List<T> getAllPersonsByClassType(Class<T> personTClass) {
        if (personTClass.equals(Person.class)){
            List<T> connectedPersons = (List<T>) personDAO.getAllPersonsByClassType(User.class);
            connectedPersons.addAll((List<T>) personDAO.getAllPersonsByClassType(Owner.class));
            return connectedPersons;
        }else {
            return personDAO.getAllPersonsByClassType(personTClass);
        }
    }

    @Override
    public Long deletePersonById(Long id) {
        return personDAO.deletePersonById(id);
    }

    @Override
    public <T extends Person> List<T> getPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> personTClass) {
        return personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, personTClass);
    }

    @Override
    public <T extends Person> List<T> getPersonByLastOrFirstName(String oneOfNames) {
        List<T> connectedPersons = (List<T>) personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, User.class);
        connectedPersons.addAll((List<T>) personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, Owner.class));
        return connectedPersons;
    }

    @Override
    public <T extends Person> T getPersonById(Long id) {
        return personDAO.getPersonById(id);
    }

    @Override
    public <T extends Person> Long addApartmentToPerson(Long personID, Long apartmentId) {
        T person = personDAO.getPersonById(personID);
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
    public <T extends Person> Long delApartmentFromPerson(Long personID, Long apartmentId) {
        T person = personDAO.getPersonById(personID);
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
