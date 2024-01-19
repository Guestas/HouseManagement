package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.*;
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
    public <T extends Person> Long addUpdatePerson(T person) {
        return personDAO.addUpdatePerson(person);
    }

    @Override
    public <T extends Person> T getPersonById(Long id, Class<T> tClass) {
        return personDAO.getPersonById(id, tClass);
    }

    @Override
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass) {
        if (tClass.equals(Person.class)){
            List<T> connectedPersons = (List<T>) personDAO.loadAllPersons(User.class);
            connectedPersons.addAll((List<T>) personDAO.loadAllPersons(Owner.class));
            return connectedPersons;
        }else {
            return personDAO.loadAllPersons(tClass);
        }
    }

    @Override
    public <T extends Person> Long deleteById(Long id, Class<T> tClass) {
        return personDAO.deleteById(id, tClass);
    }

    @Override
    public int deleteAllPersons() {
        return personDAO.deleteAllPersons();
    }

    @Override
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass) {
        return personDAO.loadPersonByLastOrFirstNameAndType(oneOfNames, tClass);
    }

    @Override
    public <T extends Person> List<T> loadPersonByLastOrFirstName(String oneOfNames) {
        List<T> connectedPersons = (List<T>) personDAO.loadPersonByLastOrFirstNameAndType(oneOfNames, User.class);
        connectedPersons.addAll((List<T>) personDAO.loadPersonByLastOrFirstNameAndType(oneOfNames, Owner.class));
        return connectedPersons;
    }

    @Override
    public <T extends Person> T loadPersonByID(Long id) {
        return personDAO.loadPersonByID(id);
    }

    @Override
    public <T extends Person> Long addApartmentToPerson(Long personID, Class<T> tClass, Long apartmentId) {
        T person = personDAO.getPersonById(personID, tClass);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);

        person.addApartment(apartment);

        return personDAO.addUpdatePerson(person);
    }

    @Override
    public <T extends Person> Long delApartmentFromPerson(Long personID, Class<T> tClass, Long apartmentId) {
        T person = personDAO.getPersonById(personID, tClass);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);

        person.delApartment(apartment);

        return personDAO.addUpdatePerson(person);
    }

}
