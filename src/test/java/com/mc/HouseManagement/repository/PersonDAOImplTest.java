package com.mc.HouseManagement.repository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        List<Owner> actualPersonsList = personDAO.loadAllPersons(Owner.class);

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
        Owner testOwner = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321L,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testOwner);
    }

    @Test
    @DisplayName("Test User Add then load by ID finally Update this User")
    void canAddUserLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        User testUser = User.createUser("Anne","Jar","anne@jar.com",
                987654321L,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testUser);
    }

    @Test
    @DisplayName("Test SoldMovedOut Add then load by ID finally Update this SoldMovedOut")
    void canAddSoldMovedOutLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("Bob","Jar","bob@jar.com",
                123456789L,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testSoldMovedOut);
    }

    private <T extends Person> void testAddPersonAndLoadByIdThenUpdateThisPerson(T testPerson){
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        Long id = personDAO.addUpdatePerson(testPerson);
        Person retrieve = personDAO.getPersonById(id, testPerson.getClass());

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());

        // When: Action or behavior that we are going to test
        testPerson.setId(retrieve.getId());
        testPerson.setFirstName("Joe");
        personDAO.addUpdatePerson(testPerson);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonById(id, testPerson.getClass());
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());
    }

/*
     --------------------------------Testing Adding and Deleting persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner Delete by ID")
    void canAddOwnerAndDeleteById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321L,null);

        testAddPersonAndDeleteById(testOwner);
    }

    @Test
    @DisplayName("Test AddUser Delete by ID")
    void canAddUserAndDeleteById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("Anne","Jar","anne@jar.com",
                987654321L,null);

        testAddPersonAndDeleteById(testUser);
    }

    @Test
    @DisplayName("Test MovedOut Delete by ID")
    void canAddSoldMovedOutAndDeleteById() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("Anne","Jar","anne@jar.com",
                987654321L,null);

        testAddPersonAndDeleteById(testSoldMovedOut);
    }

    private <T extends Person> void testAddPersonAndDeleteById(T testPerson){
        // When: Action or behavior that we are going to test
        Long id = personDAO.addUpdatePerson(testPerson);
        Long deletedPersonId = personDAO.deleteById(id);

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
        Owner testOwner1 = Owner.createOwner("Bob","Jar","bob@jar.com",
                123456789L,null);
        Owner testOwner2 = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321L,null);
        List<Owner> expectedOwnersList = Arrays.asList(testOwner1,testOwner2);

        testLoadMultiplePersons(expectedOwnersList, Owner.class);
    }

    @Test
    @DisplayName("Test Users for Load them All")
    void canLoadAllUsers() {
        // Given: Setup object or precondition
        User testUser1 = User.createUser("Bob","Jar","bob@jar.com",
                123456789L,null);
        User testUser2 = User.createUser("Anne","Jar","anne@jar.com",
                987654321L,null);
        List<User> expectedUsersList = Arrays.asList(testUser1,testUser2);

        testLoadMultiplePersons(expectedUsersList, User.class);
    }

    @Test
    @DisplayName("Test SoldMovedOut for Load them All")
    void canLoadAllSoldMovedOuts() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut1 = SoldMovedOut.createSoldMovedOut("Bob","Jar","bob@jar.com",
                123456789L,null);
        SoldMovedOut testSoldMovedOut2 = SoldMovedOut.createSoldMovedOut("Anne","Jar","anne@jar.com",
                987654321L,null);
        List<SoldMovedOut> expectedSoldMovedOutsList = Arrays.asList(testSoldMovedOut1,testSoldMovedOut2);
        
        testLoadMultiplePersons(expectedSoldMovedOutsList, SoldMovedOut.class);
    }

    private <T extends Person> void testLoadMultiplePersons(List<T> testPersons, Class<T> tClass){
        // When: Action or behavior that we are going to test
        testPersons.forEach(personDAO::addUpdatePerson);
        List<T> returnedOwners = personDAO.loadAllPersons(tClass);

        // Then: Verify the output or expected result
        assertNotNull(returnedOwners);
        assertEquals(testPersons.get(0).getFirstName(), returnedOwners.get(0).getFirstName());
        assertEquals(testPersons.get(1).getFirstName(), returnedOwners.get(1).getFirstName());
    }
    
/*
     --------------------------------Testing LoadAll persons--------------------------------
*/

    @Test
    @DisplayName("Test Load Person by First and Last name")
    void canLoadPersonByLastOrFirstNameAndType() {
        // Given: Setup object or precondition
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789L,null);
        Person testOwner2 = new Person("Anne","Jara","anne@jar.com",
                987654321L,null);
        Person testOwner3 = new Person("Diana","Anne","diana@anne.com",
                987654321L,null);
        Person testOwner4 = new Person("Jara","Anne","diana@anne.com",
                987654321L,null);
        List<Person> expectedOwnersList = Arrays.asList(testOwner1,testOwner2,testOwner3,testOwner4);

        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addUpdatePerson);
        int expectedSize= (int) expectedOwnersList.stream()
                .filter(owner -> owner.getFirstName().equals("Jara") || owner.getLastName().equals("Jara"))
                .count();

        int actualSize = personDAO.loadPersonByLastOrFirstNameAndType("Jara", Person.class).size();

        // Then: Verify the output or expected result
        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("Testing loading all persons without SoldMoved")
    <T extends Person> void loadingAllPersonsByNameOrLName(){
        // Given: Setup object or precondition
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789L,null);
        Owner testOwner2 = new Owner("Anne","Jara","anne@jar.com",
                987654321L,null);
        SoldMovedOut testOwner3 = new SoldMovedOut("Diana","Jara","diana@anne.com",
                987654321L,null);
        User testOwner4 = new User("Kala","Anne","diana@anne.com",
                987654321L,null);
        List<T> expectedOwnersList = new ArrayList<>();
        expectedOwnersList.add((T) testOwner1);
        expectedOwnersList.add((T) testOwner2);
        expectedOwnersList.add((T) testOwner3);
        expectedOwnersList.add((T) testOwner4);

        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addUpdatePerson);
        int expectedSize= (int) expectedOwnersList.stream()
                .filter(owner -> (owner.getFirstName().equals("Jara") || owner.getLastName().equals("Jara")) &&
                        (owner.getClass() == Owner.class || owner.getClass() == User.class))
                .count();

        int actualSize1 = personDAO.loadPersonByLastOrFirstNameAndType("Jara", User.class).size();
        int actualSize2 = personDAO.loadPersonByLastOrFirstNameAndType("Jara", Owner.class).size();

        // Then: Verify the output or expected result
        assertThat(actualSize1+actualSize2).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("Test deleting and updating Apartments to Person")
    void addApartmentToPersonAndUpdateDel(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        User testUser1 = User.createUser("Bob","Jar","bob@jar.com",
                123456789L,null);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartmentDAO::addUpdateApartment);
        testUser1.setApartments(apartmentDAO.loadAllApartments());

        Long id = personDAO.addUpdatePerson(testUser1);

        User userWillBeUpdated = personDAO.loadPersonByID(id);
        userWillBeUpdated.delApartment(testApartment2);
        personDAO.addUpdatePerson(userWillBeUpdated);

        User returnedUser = personDAO.loadPersonByID(id);

        // Then: Verify the output or expected result
        assertNotNull(returnedUser.getApartments());
        assertEquals(userWillBeUpdated.getApartments().size(), returnedUser.getApartments().size());
    }

    @Test
    @DisplayName("Test adding and updating Apartments to Person")
    void addApartmentToPersonAndUpdateAdd(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1);

        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        User testUser1 = User.createUser("Bob","Jar","bob@jar.com",
                123456789L,null);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartmentDAO::addUpdateApartment);
        testUser1.setApartments(apartmentDAO.loadAllApartments());

        Long id = personDAO.addUpdatePerson(testUser1);

        User userWillBeUpdated = personDAO.loadPersonByID(id);
        userWillBeUpdated.addApartment(testApartment2);
        personDAO.addUpdatePerson(userWillBeUpdated);

        User returnedUser = personDAO.loadPersonByID(id);

        // Then: Verify the output or expected result
        assertNotNull(returnedUser.getApartments());
        assertEquals(userWillBeUpdated.getApartments().size(), returnedUser.getApartments().size());
    }

}

