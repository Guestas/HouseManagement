package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Person;

import java.util.List;

public interface PersonService {
    public <T extends Person> Long addPerson(T person);
    public <T extends Person> T getPersonById(Long id, Class<T> tClass);
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass);
    public <T extends Person> Long deleteById(Long id, Class<T> tClass);
    public int deleteAllPersons();
    public <T extends Person> List<T> loadPersonByLastOrFirstName(String oneOfNames, Class<T> tClass);
}
