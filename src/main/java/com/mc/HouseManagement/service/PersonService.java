package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;

import java.util.List;

public interface PersonService {
    public Long addUpdatePerson(AddUpdateNewPerson addUpdateNewPerson);
    public <T extends Person> T getPersonById(Long id, Class<T> tClass);
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass);
    public <T extends Person> Long deleteById(Long id);
    public int deleteAllPersons();
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass);
    public <T extends Person> List<T> loadPersonByLastOrFirstName(String oneOfNames);
    public <T extends Person> T loadPersonByID(Long id);
    public <T extends Person> Long addApartmentToPerson(Long personID, Long apartmentId);
    public <T extends Person> Long delApartmentFromPerson(Long personID, Long apartmentId);
}
