package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Owner;
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
class OwnerDAOImplTest {

    public final OwnerDAO ownerDAO;

    @Autowired
    public OwnerDAOImplTest(OwnerDAO ownerDAO){
        this.ownerDAO = ownerDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        ownerDAO.deleteAllOwners();
        List<Owner> actualOwnersList = ownerDAO.loadAllOwners();

        // Assertions
        assertThat(actualOwnersList).isEqualTo(null);

    }

    @Test
    void canAddOwnerAndLoadById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("f","l","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = ownerDAO.addOwner(testOwner);
        Owner retrieve = ownerDAO.getOwnerById(id);

        // Then: Verify the output or expected result
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());
    }

    @Test
    void canUpdateOwnerAndLoadById() {
        // Given: Setup object or precondition
        Owner testOwner = Owner.createOwner("a","b","e",
                123456,null);

        // When: Action or behavior that we are going to test
        Long id = ownerDAO.addOwner(testOwner);
        // Then: Verify the output or expected result
        Owner retrieve = ownerDAO.getOwnerById(id);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());

        // When: Action or behavior that we are going to test
        testOwner.setId(retrieve.getId());
        testOwner.setFirstName("ff");
        ownerDAO.addOwner(testOwner);

        // Then: Verify the output or expected result
        retrieve = ownerDAO.getOwnerById(id);
        assertNotNull(retrieve.getFirstName());
        assertEquals(retrieve.getFirstName(), testOwner.getFirstName());
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
        expectedOwnersList.forEach(ownerDAO::addOwner);

        // Then: Verify the output or expected result
        List<Owner> owners = ownerDAO.loadAllOwners();
    }

}