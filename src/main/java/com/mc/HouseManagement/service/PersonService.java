package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Person;

import java.util.List;

public interface PersonService {
    public <T extends Person> Long addPerson(T person);
    public <T extends Person> T getPersonById(Long id, Class<T> person);
    public <T extends Person> List<T> loadAllPersons(Class<T> person);
    public int deleteAllPersons();
}
