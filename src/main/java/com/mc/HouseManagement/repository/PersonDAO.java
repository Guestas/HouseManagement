package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Person;

import java.util.List;

public interface PersonDAO {

    /**
     * Adds or updates a person entity in the database.
     * @param person The person entity to be added or updated.
     * @param <T>    Type parameter extending Person.
     * @return The ID of the added or updated person.
     */
    public <T extends Person> Long addUpdatePerson(T person);

    /**
     * Retrieves a person entity by its ID.
     * @param id     The ID of the person entity to retrieve.
     * @param tClass The class type of the person entity.
     * @param <T>    Type parameter extending Person.
     * @return The person entity with the specified ID.
     */
    public <T extends Person> T getPersonById(Long id, Class<T> tClass);

    /**
     * Loads all person entities of a given type.
     * @param tClass The class type of the person entities.
     * @param <T>    Type parameter extending Person.
     * @return List of all person entities of the specified type.
     */
    public <T extends Person> List<T> loadAllPersons(Class<T> tClass);

    /**
     * Deletes a person entity by its ID.
     * @param id The ID of the person entity to delete.
     * @param <T> Type parameter extending Person.
     * @return The ID of the deleted person entity or -1 if the entity was not found.
     */
    public <T extends Person> Long deleteById(Long id);

    /**
     * Deletes all person entities from the database.
     * @return The number of entities deleted.
     */
    public int deleteAllPersons();

    /**
     * Loads person entities by last or first name and type.
     * @param oneOfNames The last or first name to search for.
     * @param tClass     The class type of the person entities.
     * @param <T>        Type parameter extending Person.
     * @return List of person entities matching the criteria.
     */
    public <T extends Person> List<T> loadPersonByLastOrFirstNameAndType(String oneOfNames, Class<T> tClass);

    /**
     * Loads a person entity by its ID with support for different person types.
     * @param id The ID of the person entity to load.
     * @param <T> Type parameter extending Person.
     * @return The loaded person entity or null if not found.
     */
    public <T extends Person> T loadPersonByID(Long id);
}
