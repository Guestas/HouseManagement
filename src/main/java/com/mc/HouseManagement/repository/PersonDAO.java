package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Person;

import java.util.List;

public interface PersonDAO {
    public <T extends Person> Long addUpdatePerson(T person);
    public <T extends Person> T getPersonById(Long id, Class<T> person);
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass);
    public <T extends Person> Long deleteById(Long id);
    public int deleteAllPersons();
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass);
    public <T extends Person> T loadPersonByID(Long id);
}
