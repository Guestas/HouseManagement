package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Person;

import java.util.List;

public interface PersonDAO {

    /**
     * Adds or updates a person entity in the database.
     * @param person The person entity to be added or updated.
     * @return The ID of the added or updated person.
     */
    public Long addUpdatePerson(Person person);

    /**
     * Retrieves a person entity by its ID.
     * @param personId     The ID of the person entity to retrieve.
     * @param personType      The type of the person entities.
     * @return The person entity with the specified ID.
     */
    public Person getPersonByIdAndType(Long personId, String personType);

    /**
     * Retrieves a person entity by its ID.
     * @param apartmentId     The ID of the apartment in person entity to retrieve.
     * @param personType      The type of the person entities.
     * @return List of person entities matching the criteria.
     */
    public List<Person> getPersonsByApartmentsIdAndType(Long apartmentId, String personType);

    /**
     * Loads all person entities of a given type.
     * @param personType The type of the person entities.
     * @return List of all person entities of the specified type.
     */
    public List<Person> getAllPersonsByType(String personType);

    /**
     * Deletes a person entity by its ID.
     * @param id The ID of the person entity to delete.
     * @return The ID of the deleted person entity or -1 if the entity was not found.
     */
    public Long deletePersonById(Long id);

    /**
     * Deletes all person entities from the database.
     * @return The number of entities deleted.
     */
    public int deleteAllPersons();

    /**
     * Loads person entities by last or first name and type.
     * @param oneOfNames The last or first name to search for.
     * @param personType The type of the person entities.
     * @return List of person entities matching the criteria.
     */
    public List<Person> getPersonByLastOrFirstNameAndType(String oneOfNames, String personType);

    /**
     * Loads a person entity by its ID with support for different person types.
     * @param id The ID of the person entity to load.
     * @return The loaded person entity or null if not found.
     */
    public Person getPersonById(Long id);
}
