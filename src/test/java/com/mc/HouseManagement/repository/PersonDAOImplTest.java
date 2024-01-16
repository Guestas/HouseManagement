package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Testing of Person class and inherited classes")
class PersonDAOImplTest {
    private PersonDAO personDAO;

    @Autowired
    public PersonDAOImplTest(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        personDAO.deleteAllPersons();
        List<Owner> actualPersonsList = personDAO.loadAllPersons(Owner.class);

        // Assertions
        assertThat(actualPersonsList).isEqualTo(null);
    }

/*
     --------------------------------Testing Adding and Updating persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner for Add then load by ID finally Update this Owner")
    void canAddOwnerLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testOwner);
    }

    @Test
    @DisplayName("Test User for Add then load by ID finally Update this User")
    void canAddUserLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        User testUser = User.createUser("Anne","Jar","anne@jar.com",
                987654321,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testUser);
    }

    @Test
    @DisplayName("Test SoldMovedOut for Add then load by ID finally Update this SoldMovedOut")
    void canAddSoldMovedOutLoadByIdAndUpdate() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("Bob","Jar","bob@jar.com",
                123456789,null);

        testAddPersonAndLoadByIdThenUpdateThisPerson(testSoldMovedOut);
    }

    private <T extends Person> void testAddPersonAndLoadByIdThenUpdateThisPerson(T testPerson){
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testPerson);
        Person retrieve = personDAO.getPersonById(id, testPerson.getClass());

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());

        // When: Action or behavior that we are going to test
        testPerson.setId(retrieve.getId());
        testPerson.setFirstName("Joe");
        personDAO.addPerson(testPerson);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonById(id, testPerson.getClass());
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testPerson.getFirstName());
    }

/*
     --------------------------------Testing Adding and Deleting persons--------------------------------
*/

    @Test
    @DisplayName("Test Owner for Add then Delete by ID")
    void canAddOwnerAndDeleteById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321,null);

        testAddPersonAndDeleteById(testOwner);
    }

    @Test
    @DisplayName("Test AddUser for Add then Delete by ID")
    void canAddUserAndDeleteById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("Anne","Jar","anne@jar.com",
                987654321,null);

        testAddPersonAndDeleteById(testUser);
    }

    @Test
    @DisplayName("Test MovedOutAndDelete for Add then Delete by ID")
    void canAddSoldMovedOutAndDeleteById() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("Anne","Jar","anne@jar.com",
                987654321,null);

        testAddPersonAndDeleteById(testSoldMovedOut);
    }

    private <T extends Person> void testAddPersonAndDeleteById(T testPerson){
        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testPerson);
        Long deletedPersonId = personDAO.deleteById(id, testPerson.getClass());

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
                123456789,null);
        Owner testOwner2 = Owner.createOwner("Anne","Jar","anne@jar.com",
                987654321,null);
        List<Owner> expectedOwnersList = Arrays.asList(testOwner1,testOwner2);

        testLoadMultiplePersons(expectedOwnersList, Owner.class);
    }

    @Test
    @DisplayName("Test Users for Load them All")
    void canLoadAllUsers() {
        // Given: Setup object or precondition
        User testUser1 = User.createUser("Bob","Jar","bob@jar.com",
                123456789,null);
        User testUser2 = User.createUser("Anne","Jar","anne@jar.com",
                987654321,null);
        List<User> expectedUsersList = Arrays.asList(testUser1,testUser2);

        testLoadMultiplePersons(expectedUsersList, User.class);
    }

    @Test
    @DisplayName("Test SoldMovedOut for Load them All")
    void canLoadAllSoldMovedOuts() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut1 = SoldMovedOut.createSoldMovedOut("Bob","Jar","bob@jar.com",
                123456789,null);
        SoldMovedOut testSoldMovedOut2 = SoldMovedOut.createSoldMovedOut("Anne","Jar","anne@jar.com",
                987654321,null);
        List<SoldMovedOut> expectedSoldMovedOutsList = Arrays.asList(testSoldMovedOut1,testSoldMovedOut2);
        
        testLoadMultiplePersons(expectedSoldMovedOutsList, SoldMovedOut.class);
    }

    private <T extends Person> void testLoadMultiplePersons(List<T> testPersons, Class<T> tClass){
        // When: Action or behavior that we are going to test
        testPersons.forEach(personDAO::addPerson);
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
    @DisplayName("Test Load Person by First andLast name")
    void canLoadPersonByLastOrFirstName() {
        // Given: Setup object or precondition
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789,null);
        Person testOwner2 = new Person("Anne","Jara","anne@jar.com",
                987654321,null);
        Person testOwner3 = new Person("Diana","Anne","diana@anne.com",
                987654321,null);
        Person testOwner4 = new Person("Jara","Anne","diana@anne.com",
                987654321,null);
        List<Person> expectedOwnersList = Arrays.asList(testOwner1,testOwner2,testOwner3,testOwner4);

        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addPerson);
        int expectedSize= (int) expectedOwnersList.stream()
                .filter(owner -> owner.getFirstName().equals("Jara") || owner.getLastName().equals("Jara"))
                .count();

        int actualSize = personDAO.loadPersonByLastOrFirstName("Jara", Person.class).size();

        // Then: Verify the output or expected result
        assertThat(actualSize).isEqualTo(expectedSize);
    }
}

