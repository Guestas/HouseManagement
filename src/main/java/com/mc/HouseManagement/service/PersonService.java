package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.ReturnMultiplePersonsForApartment;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;

import java.util.List;

public interface PersonService {

    /**
     * Adds or updates a person in the database.
     * @param addUpdatePerson An object containing the person details.
     * @return The ID of the added or updated person.
     */
    public Long addUpdatePerson(AddUpdatePerson addUpdatePerson);

    /**
     * Retrieves a person by ID and class type.
     * @param personID The ID of the person.
     * @param personTClass The class type of the person.
     * @param <T> Type of the person.
     * @return The person with the specified ID and type.
     */
    public <T extends Person> T getPersonByIdAndType(Long personID, Class<T> personTClass);

    /**
     * Retrieves a person by ID and class type.
     * @param apartmentId The ID of the person.
     * @param personTClass The class type of the person.
     * @param <T> Type of the person.
     * @return List of person entities matching the criteria.
     */
    public <T extends Person> List<ReturnMultiplePersonsForApartment>  getPersonsByApartmentsIdAndType(Long apartmentId, Class<T> personTClass);

    /**
     * Returns a list of persons based on the specified class type.
     * If personTClass is Person.class, it will return User and Owner; otherwise, it returns the given type.
     * @param personTClass The class type of the person.
     * @param <T> Type of the person.
     * @return List of persons based on the specified class type.
     */
    public <T extends Person> List<T> getAllPersonsByClassType(Class<T> personTClass);

    /**
     * Deletes a person by ID.
     * @param personId The ID of the person to be deleted.
     * @return The ID of the deleted person.
     */
    public Long deletePersonById(Long personId);

    /**
     * Loads persons based on the last or first name and specified class type.
     * @param oneOfNames The last or first name to search for.
     * @param personTClass The class type of the person.
     * @param <T> Type of the person.
     * @return List of persons based on the last or first name and specified class type.
     */
    public <T extends Person> List<T> getPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> personTClass);

    /**
     * Loads persons based on the last or first name.
     * @param oneOfNames The last or first name to search for.
     * @param <T> Type of the person.
     * @return List of persons based on the last or first name.
     */
    public <T extends Person> List<T> getPersonByLastOrFirstName(String oneOfNames);

    /**
     * Loads a person by ID.
     * @param id The ID of the person to be loaded.
     * @param <T> Type of the person.
     * @return The person with the specified ID.
     */
    public <T extends Person> T getPersonById(Long id);
    

    /**
     * Adds an apartment to a person.
     * @param personID The ID of the person.
     * @param apartmentId The ID of the apartment to be added.
     * @param <T> Type of the person.
     * @return The ID of the person after adding the apartment, -1 if person is null or -2 if apartment is null.
     */
    public <T extends Person> Long addApartmentToPerson(Long personID, Long apartmentId);

    /**
     * Removes an apartment from a person.
     * @param personID The ID of the person.
     * @param apartmentId The ID of the apartment to be removed.
     * @param <T> Type of the person.
     * @return The ID of the person after removing the apartment, -1 if person is null or -2 if apartment is null.
     */
    public <T extends Person> Long delApartmentFromPerson(Long personID, Long apartmentId);
}
