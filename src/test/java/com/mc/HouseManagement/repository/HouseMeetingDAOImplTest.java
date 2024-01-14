package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;
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
class HouseMeetingDAOImplTest {
    
    private final HouseMeetingDAO houseMeetingDAO;

    @Autowired
    public HouseMeetingDAOImplTest(HouseMeetingDAO houseMeetingDAO) {
        this.houseMeetingDAO = houseMeetingDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        houseMeetingDAO.deleteAllHouseMeetings();
        List<HouseMeeting> actualHouseMeetingList = houseMeetingDAO.loadAllHouseMeetings();

        // Assertions
        assertThat(actualHouseMeetingList).isEqualTo(null);

    }
    

    @Test
    @Disabled
    void canAddHouseMeeting() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }

    @Test
    @Disabled
    void canGetHouseMeetingById() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }

    @Test
    @Disabled
    void canLoadAllHouseMeetings() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }
}