package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.repository.PersonDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public <T extends Person> Long addPerson(T person) {
        return personDAO.addPerson(person);
    }

    @Override
    public <T extends Person> T getPersonById(Long id, Class<T> person) {
        return personDAO.getPersonById(id, person);
    }

    @Override
    public <T extends Person> List<T> loadAllPersons(Class<T> person) {
        return personDAO.loadAllPersons(person);
    }

    @Override
    public int deleteAllPersons() {
        return personDAO.deleteAllPersons();
    }
}
