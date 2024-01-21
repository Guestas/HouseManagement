package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;

import java.util.List;

public interface PersonService {

    /**
     * Adds or updates a person in the database.
     * @param addUpdateNewPerson An object containing the person details.
     * @return The ID of the added or updated person.
     */
    public Long addUpdatePerson(AddUpdateNewPerson addUpdateNewPerson);

    /**
     * Retrieves a person by ID and class type.
     * @param id The ID of the person.
     * @param tClass The class type of the person.
     * @param <T> Type of the person.
     * @return The person with the specified ID and type.
     */
    public <T extends Person> T getPersonById(Long id, Class<T> tClass);

    /**
     * Returns a list of persons based on the specified class type.
     * If tClass is Person.class, it will return User and Owner; otherwise, it returns the given type.
     * @param tClass The class type of the person.
     * @param <T> Type of the person.
     * @return List of persons based on the specified class type.
     */
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass);

    /**
     * Deletes a person by ID.
     * @param id The ID of the person to be deleted.
     * @return The ID of the deleted person.
     */
    public Long deleteById(Long id);

    /**
     * Loads persons based on the last or first name and specified class type.
     * @param oneOfNames The last or first name to search for.
     * @param tClass The class type of the person.
     * @param <T> Type of the person.
     * @return List of persons based on the last or first name and specified class type.
     */
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass);

    /**
     * Loads persons based on the last or first name.
     * @param oneOfNames The last or first name to search for.
     * @param <T> Type of the person.
     * @return List of persons based on the last or first name.
     */
    public <T extends Person> List<T> loadPersonByLastOrFirstName(String oneOfNames);

    /**
     * Loads a person by ID.
     * @param id The ID of the person to be loaded.
     * @param <T> Type of the person.
     * @return The person with the specified ID.
     */
    public <T extends Person> T loadPersonByID(Long id);

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
