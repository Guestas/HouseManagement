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


    @Test
    @DisplayName("JUnit test for Add owner and load by ID")
    void canAddOwnerAndLoadById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testOwner);
        Person retrieve = personDAO.getPersonById(id, Owner.class);

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());
    }


    @Test
    @DisplayName("JUnit test for Update owner and load by ID")
    void canUpdateOwnerAndLoadById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("a","b","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testOwner);
        // Then: Verify the output or expected result
        Owner retrieve = personDAO.getPersonById(id, Owner.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());

        // When: Action or behavior that we are going to test
        testOwner.setId(retrieve.getId());
        testOwner.setFirstName("ff");
        personDAO.addPerson(testOwner);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonById(id, Owner.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());
    }

    @Test
    @DisplayName("JUnit test for Add Owner and Delete by ID")
    void canAddOwnerAndDeleteById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testOwner);
        Long deletedPersonId = personDAO.deleteById(id, Owner.class);

        // Then: Verify the output or expected result
        assertEquals(deletedPersonId, id);
    }

    @Test
    @DisplayName("JUnit test for get All Owners")
    void canLoadAllOwners() {
        // Given: Setup object or precondition
        Owner testOwner1 = Owner.createOwner("a","b","e",
                123456,null);
        Owner testOwner2 = Owner.createOwner("a","b","e",
                123456,null);
        List<Owner> expectedOwnersList = Arrays.asList(testOwner1,testOwner2);
        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personDAO::addPerson);

        // Then: Verify the output or expected result
        List<Owner> owners = personDAO.loadAllPersons(Owner.class);
    }


    @Test
    @DisplayName("JUnit test for Add user and load by ID")
    void canAddUserAndLoadById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testUser);
        Person retrieve = personDAO.getPersonById(id, User.class);

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testUser.getFirstName());
    }


    @Test
    @DisplayName("JUnit test for Update user and load by ID")
    void canUpdateUserAndLoadById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("a","b","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testUser);
        // Then: Verify the output or expected result
        User retrieve = personDAO.getPersonById(id, User.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testUser.getFirstName());

        // When: Action or behavior that we are going to test
        testUser.setId(retrieve.getId());
        testUser.setFirstName("ff");
        personDAO.addPerson(testUser);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonById(id, User.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testUser.getFirstName());
    }

    @Test
    @DisplayName("JUnit test for Add User and Delete by ID")
    void canAddUserAndDeleteById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testUser);
        Long deletedPersonId = personDAO.deleteById(id, User.class);

        // Then: Verify the output or expected result
        assertEquals(deletedPersonId, id);
    }
    
    @Test
    @DisplayName("JUnit test for get All Users")
    void canLoadAllUsers() {
        // Given: Setup object or precondition
        User testUser1 = User.createUser("a","b","e",
                123456,null);
        User testUser2 = User.createUser("a","b","e",
                123456,null);
        List<User> expectedUsersList = Arrays.asList(testUser1,testUser2);
        // When: Action or behavior that we are going to test
        expectedUsersList.forEach(personDAO::addPerson);

        // Then: Verify the output or expected result
        List<User> owners = personDAO.loadAllPersons(User.class);
    }

    @Test
    @DisplayName("JUnit test for Add SoldMovedOut and load by ID")
    void canAddSoldMovedOutAndLoadById() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testSoldMovedOut);
        Person retrieve = personDAO.getPersonById(id, SoldMovedOut.class);

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testSoldMovedOut.getFirstName());
    }


    @Test
    @DisplayName("JUnit test for Update SoldMovedOut and load by ID")
    void canUpdateSoldMovedOutAndLoadById() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("a","b","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testSoldMovedOut);
        // Then: Verify the output or expected result
        SoldMovedOut retrieve = personDAO.getPersonById(id, SoldMovedOut.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testSoldMovedOut.getFirstName());

        // When: Action or behavior that we are going to test
        testSoldMovedOut.setId(retrieve.getId());
        testSoldMovedOut.setFirstName("ff");
        personDAO.addPerson(testSoldMovedOut);

        // Then: Verify the output or expected result
        retrieve = personDAO.getPersonById(id, SoldMovedOut.class);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testSoldMovedOut.getFirstName());
    }

    @Test
    @DisplayName("JUnit test for Add SoldMovedOut and Delete by ID")
    void canAddSoldMovedOutAndDeleteById() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = personDAO.addPerson(testSoldMovedOut);
        Long deletedPersonId = personDAO.deleteById(id, SoldMovedOut.class);

        // Then: Verify the output or expected result
        assertEquals(deletedPersonId, id);
    }

    @Test
    @DisplayName("JUnit test for get All SoldMovedOuts")
    void canLoadAllSoldMovedOuts() {
        // Given: Setup object or precondition
        SoldMovedOut testSoldMovedOut1 = SoldMovedOut.createSoldMovedOut("a","b","e",
                123456,null);
        SoldMovedOut testSoldMovedOut2 = SoldMovedOut.createSoldMovedOut("a","b","e",
                123456,null);
        List<SoldMovedOut> expectedSoldMovedOutsList = Arrays.asList(testSoldMovedOut1,testSoldMovedOut2);
        // When: Action or behavior that we are going to test
        expectedSoldMovedOutsList.forEach(personDAO::addPerson);

        // Then: Verify the output or expected result
        List<SoldMovedOut> owners = personDAO.loadAllPersons(SoldMovedOut.class);
    }

}