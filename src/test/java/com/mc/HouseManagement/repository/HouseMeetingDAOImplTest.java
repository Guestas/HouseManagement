package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.HouseMeeting;
import org.junit.jupiter.api.AfterEach;
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
    void canAddAndLoadByIDHouseMeeting() {
        // Given: Setup object or precondition
        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics,null);

        // When: Action or behavior that we are going to test
        Long id = houseMeetingDAO.addHouseMeeting(testHouseMeeting);
        HouseMeeting returned = houseMeetingDAO.getHouseMeetingById(id);

        // Then: Verify the output or expected result
        assertNotNull(returned);
        assertThat(testHouseMeeting.getDate()).isEqualTo(returned.getDate());
    }

    @Test
    void canLoadAllHouseMeetings() {
        // Given: Setup object or precondition
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting1 = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics1,null);
        List<String> topics2 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting2 = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics2,null);
        List<HouseMeeting> expectedHouseMeetingsList = Arrays.asList(testHouseMeeting1, testHouseMeeting2);

        // When: Action or behavior that we are going to test
        expectedHouseMeetingsList.forEach(houseMeetingDAO::addHouseMeeting);
        List<HouseMeeting> returnedHouseMeetingList = houseMeetingDAO.loadAllHouseMeetings();

        // Then: Verify the output or expected result
        assertNotNull(returnedHouseMeetingList);
        assertThat(expectedHouseMeetingsList.size()).isEqualTo(returnedHouseMeetingList.size());
        assertEquals(testHouseMeeting1.getName(), returnedHouseMeetingList.get(0).getName());
        assertEquals(testHouseMeeting2.getName(), returnedHouseMeetingList.get(1).getName());

    }
}