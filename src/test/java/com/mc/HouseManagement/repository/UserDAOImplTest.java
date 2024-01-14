package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class UserDAOImplTest {

    public final UserDAO userDAO;
    @Autowired
    public UserDAOImplTest(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        userDAO.deleteAllUsers();
        List<User> actualUsersList = userDAO.loadAllUsers();

        // Assertions
        assertThat(actualUsersList).isEqualTo(null);

    }

    @Test
    @Disabled
    void canAddUserAndLoadById() {
        // Given: Setup object or precondition
        User testUser = User.createUser("f","l","e",
                1546456, null);

        // When: Action or behavior that we are going to test


        // Then: Verify the output or expected result
    }

    @Test
    @Disabled
    void canGetUserById() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }

    @Test
    @Disabled
    void canLoadAllUsers() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }
}