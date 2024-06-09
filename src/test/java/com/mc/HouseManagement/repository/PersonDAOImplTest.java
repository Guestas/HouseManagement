package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing persons subclasses User, Owner and SoldMovedOut**/
@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Testing of Person class and inherited classes")
class PersonDAOImplTest {

    private final PersonDAO personDAO;
    private final ApartmentDAO apartmentDAO;

    @Autowired
    public PersonDAOImplTest(PersonDAO personDAO, ApartmentDAO apartmentDAO) {
        this.personDAO = personDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        personDAO.deleteAllPersons();
        List<Person> actualPersonsList = personDAO.getAllPersonsByType(Person.OWNER);

        // Assertions
        assertThat(actualPersonsList).isEmpty();
    }

/*
     --------------------------------Testing Adding and Updating persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner Add then load by ID finally Update this Owner")
    void canAddOwnerLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        Person testOwner = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.OWNER);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testOwner);
    }

    @Test
    @DisplayName("Test User Add then load by ID finally Update this User")
    void canAddUserLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        Person testUser = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, "Person");

        testAddPersonAndLoadByIdThenUpdateThisPerson(testUser);
    }

    @Test
    @DisplayName("Test SoldMovedOut Add then load by ID finally Update this SoldMovedOut")
    void canAddSoldMovedOutLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        Person testSoldMovedOut = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null, Person.SOLD_MOVED_OUT);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testSoldMovedOut);
    }

    private void testAddPersonAndLoadByIdThenUpdateThisPerson(Person testPerson){
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        Long id = personDAO.addUpdatePerson(testPerson);
        Person retrieve = personDAO.getPersonByIdAndType(id, testPerson.getType());

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());

        // When: Action or behavior that we are going to test
        testPerson.setId(retrieve.getId());
        testPerson.setFirstName("Joe");
        personDAO.addUpdatePerson(testPerson);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonByIdAndType(id, testPerson.getType());
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());
    }

/*
     --------------------------------Testing Adding and Deleting persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner Delete by ID")
    void canAddOwnerAndDeletePersonById() {
        // Given: Setup object or precondition
        Person testOwner = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null,Person.OWNER);

        testAddPersonAnddeletePersonById(testOwner);
    }

    @Test
    @DisplayName("Test AddUser Delete by ID")
    void canAddUserAndDeletePersonById() {
        // Given: Setup object or precondition
        Person testUser = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.USER);

        testAddPersonAnddeletePersonById(testUser);
    }

    @Test
    @DisplayName("Test MovedOut Delete by ID")
    void canAddSoldMovedOutAndDeletePersonById() {
        // Given: Setup object or precondition
        Person testSoldMovedOut = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.SOLD_MOVED_OUT);

        testAddPersonAnddeletePersonById(testSoldMovedOut);
    }

    private void testAddPersonAnddeletePersonById(Person testPerson){
        // When: Action or behavior that we are going to test
        Long id = personDAO.addUpdatePerson(testPerson);
        Long deletedPersonId = personDAO.deletePersonById(id);

        // Then: Verify the output or expected result
        assertEquals(deletedPersonId, id);
    }

/*
     --------------------------------Testing LoadAll persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner for Load them All")
    void canLoadAllOwners() {
        // Given: Setup object or precondition
        Person testOwner1 = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null, Person.OWNER);
        Person testOwner2 = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.OWNER);
        List<Person> expectedOwnersList = Arrays.asList(testOwner1,testOwner2);

        testLoadMultiplePersons(expectedOwnersList, Person.OWNER);
    }

    @Test
    @DisplayName("Test Users for Load them All")
    void canLoadAllUsers() {
        // Given: Setup object or precondition
        Person testUser1 = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null, Person.USER);
        Person testUser2 = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.USER);
        List<Person> expectedUsersList = Arrays.asList(testUser1,testUser2);

        testLoadMultiplePersons(expectedUsersList, Person.USER);
    }

    @Test
    @DisplayName("Test SoldMovedOut for Load them All")
    void canLoadAllSoldMovedOuts() {
        // Given: Setup object or precondition
        Person testSoldMovedOut1 = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null, Person.SOLD_MOVED_OUT);
        Person testSoldMovedOut2 = Person.createPerson("Anne","Jar","anne@jar.com",
                987654321L,null, Person.SOLD_MOVED_OUT);
        List<Person> expectedSoldMovedOutsList = Arrays.asList(testSoldMovedOut1,testSoldMovedOut2);

        testLoadMultiplePersons(expectedSoldMovedOutsList, Person.SOLD_MOVED_OUT);
    }

    private void testLoadMultiplePersons(List<Person> testPersons, String personType){
        // When: Action or behavior that we are going to test
        testPersons.forEach(personDAO::addUpdatePerson);
        List<Person> returnedOwners = personDAO.getAllPersonsByType(personType);

        // Then: Verify the output or expected result
        assertNotNull(returnedOwners);
        assertEquals(testPersons.get(0).getFirstName(), returnedOwners.get(0).getFirstName());
        assertEquals(testPersons.get(1).getFirstName(), returnedOwners.get(1).getFirstName());
    }

/*
     --------------------------------Testing LoadAll persons--------------------------------
*/

    @Test
    @DisplayName("Test Load Person by First or Last name")
    void cangetPersonByLastOrFirstNameAndType() {
        // Given: Setup object or precondition
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789L,null, Person.OWNER);
        Person testOwner2 = new Person("Anne","Jara","anne@jar.com",
                987654321L,null, Person.OWNER);
        Person testOwner3 = new Person("Diana","Anne","diana@anne.com",
                987654321L,null, Person.OWNER);
        Person testOwner4 = new Person("Jara","Anne","diana@anne.com",
                987654321L,null, Person.OWNER);
        List<Person> expectedOwnersList = Arrays.asList(testOwner1,testOwner2,testOwner3,testOwner4);

        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addUpdatePerson);
        int expectedSize= (int) expectedOwnersList.stream()
                .filter(owner -> owner.getFirstName().equals("Jara") || owner.getLastName().equals("Jara"))
                .count();

        int actualSize = personDAO.getPersonByLastOrFirstNameAndType("Jara", Person.OWNER).size();

        // Then: Verify the output or expected result
        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("Testing loading all persons without SoldMoved")
    void loadingAllPersonsByNameOrLName(){
        // Given: Setup object or precondition
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789L,null, Person.OWNER);
        Person testOwner2 = new Person("Anne","Jara","anne@jar.com",
                987654321L,null, Person.USER);
        Person testOwner3 = new Person("Diana","Jara","diana@anne.com",
                987654321L,null, Person.SOLD_MOVED_OUT);
        Person testOwner4 = new Person("Kala","Anne","diana@anne.com",
                987654321L,null, Person.USER);
        List<Person> expectedOwnersList = new ArrayList<>();
        expectedOwnersList.add(testOwner1);
        expectedOwnersList.add(testOwner2);
        expectedOwnersList.add(testOwner3);
        expectedOwnersList.add(testOwner4);

        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addUpdatePerson);
        int expectedSize= (int) expectedOwnersList.stream()
                .filter(owner -> (owner.getFirstName().equals("Jara") || owner.getLastName().equals("Jara")) &&
                        (Objects.equals(owner.getType(), Person.OWNER) || Objects.equals(owner.getType(), Person.USER)))
                .count();

        int actualSize1 = personDAO.getPersonByLastOrFirstNameAndType("Jara", Person.USER).size();
        int actualSize2 = personDAO.getPersonByLastOrFirstNameAndType("Jara", Person.OWNER).size();

        // Then: Verify the output or expected result
        assertThat(actualSize1+actualSize2).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("Testing loading all persons without SoldMoved first or last name is not in DB")
    void loadingAllPersonsByNameOrLNameNamesNotFound(){
        // Given: Setup object or precondition


        // When: Action or behavior that we are going to test
        int actualSize1 = personDAO.getPersonByLastOrFirstNameAndType("Jara", Person.USER).size();
        int actualSize2 = personDAO.getPersonByLastOrFirstNameAndType("Jara", Person.OWNER).size();

        // Then: Verify the output or expected result
        assertThat(actualSize1+actualSize2).isEqualTo(0);
    }

    @Test
    @DisplayName("Test disconnecting  person with apartment")
    void delApartmentFromPersonAndUpdateDel(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        Person testUser1 = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null,Person.USER);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartmentDAO::addUpdateApartment);
        testUser1.setApartments(apartmentDAO.getAllApartments());

        Long id = personDAO.addUpdatePerson(testUser1);

        Person userWillBeUpdated = personDAO.getPersonById(id);
        userWillBeUpdated.delApartment(testApartment2);
        personDAO.addUpdatePerson(userWillBeUpdated);

        Person returnedUser = personDAO.getPersonById(id);

        // Then: Verify the output or expected result
        assertNotNull(returnedUser.getApartments());
        assertEquals(userWillBeUpdated.getApartments().size(), returnedUser.getApartments().size());
    }

    @Test
    @DisplayName("Test connecting Apartment with Person")
    void addApartmentToPersonAndUpdateAdd(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1);

        Person testUser1 = Person.createPerson("Bob","Jar","bob@jar.com",
                123456789L,null, Person.USER);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartmentDAO::addUpdateApartment);
        testUser1.setApartments(apartmentDAO.getAllApartments());

        Long id = personDAO.addUpdatePerson(testUser1);

        Person userWillBeUpdated = personDAO.getPersonById(id);
        userWillBeUpdated.addApartment(testApartment2);
        personDAO.addUpdatePerson(userWillBeUpdated);

        Person returnedUser = personDAO.getPersonById(id);

        // Then: Verify the output or expected result
        assertNotNull(returnedUser.getApartments());
        assertEquals(userWillBeUpdated.getApartments().size(), returnedUser.getApartments().size());
    }

    @Test
    @DisplayName("Test get Persons by apartment id")
    void testGetPersonsByApartmentId(){
        // Given: Setup object or precondition
        Person user = new Person("Kala","Anne","diana@anne.com",
                987654321L,null, Person.USER);

        // When: Action or behavior that we are going to test
        Long apartmentId = apartmentDAO.addUpdateApartment(TestVariables.APARTMENT);

        user.addApartment(TestVariables.APARTMENT);

        Long userId = personDAO.addUpdatePerson(user);

        Person returnedPerson = personDAO.getPersonsByApartmentsIdAndType(apartmentId, Person.USER).get(0);

        // Then: Verify the output or expected result
        assertNotNull(returnedPerson);
        assertEquals(user, returnedPerson);

    }

}

