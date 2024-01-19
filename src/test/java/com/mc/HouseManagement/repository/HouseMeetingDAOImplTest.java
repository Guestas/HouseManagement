package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.ProcessToDo;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
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
class HouseMeetingDAOImplTest {
    
    private final HouseMeetingDAO houseMeetingDAO;
    private final ApartmentDAO apartmentDAO;

    @Autowired
    public HouseMeetingDAOImplTest(HouseMeetingDAO houseMeetingDAO, ApartmentDAO apartmentDAO) {
        this.houseMeetingDAO = houseMeetingDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @AfterEach
    void clearAllData(){
        // Call the method to be tested
        houseMeetingDAO.deleteAllHouseMeetings();
        List<HouseMeeting> actualHouseMeetingList = houseMeetingDAO.loadAllHouseMeetings();

        // Assertions
        assertThat(actualHouseMeetingList).isEmpty();
    }


    @Test
    @DisplayName("Test Adding HouseMeeting and load it by ID")
    void canAddAndLoadByIDHouseMeeting() {
        // Given: Setup object or precondition
        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics,null);

        // When: Action or behavior that we are going to test
        Long id = houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting);
        HouseMeeting returned = houseMeetingDAO.getHouseMeetingById(id);

        // Then: Verify the output or expected result
        assertNotNull(returned);
        assertThat(testHouseMeeting.getDate()).isEqualTo(returned.getDate());
    }

    @Test
    @DisplayName("Test Loading all HouseMeetings")
    void canLoadAllHouseMeetings() {
        // Given: Setup object or precondition
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting1 = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics1,null);
        List<String> topics2 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting2 = HouseMeeting.createHouseMeeting("20-5-1998", "Early meeting", topics2,null);
        List<HouseMeeting> expectedHouseMeetingsList = Arrays.asList(testHouseMeeting1, testHouseMeeting2);

        // When: Action or behavior that we are going to test
        expectedHouseMeetingsList.forEach(houseMeetingDAO::addUpdateHouseMeeting);
        List<HouseMeeting> returnedHouseMeetingList = houseMeetingDAO.loadAllHouseMeetings();
        System.out.println(returnedHouseMeetingList);

        // Then: Verify the output or expected result
        assertNotNull(returnedHouseMeetingList);
        assertThat(expectedHouseMeetingsList.size()).isEqualTo(returnedHouseMeetingList.size());
        assertEquals(testHouseMeeting1.getName(), returnedHouseMeetingList.get(0).getName());
        assertEquals(testHouseMeeting2.getName(), returnedHouseMeetingList.get(1).getName());
    }

    @Test
    @DisplayName("Test adding Apartments to Meetings")
    void addApartmentToMeeting(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998",
                "Early meeting", topics1, null);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartment -> apartmentDAO.addUpdateApartment(apartment, ProcessToDo.NEW));
        testHouseMeeting.setApartments(apartmentDAO.loadAllApartments());

        Long id = houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting);

        HouseMeeting returnedHouseMeeting = houseMeetingDAO.getHouseMeetingById(id);

        // Then: Verify the output or expected result
        assertNotNull(returnedHouseMeeting.getApartments());
        assertEquals(testHouseMeeting.getApartments().size(), returnedHouseMeeting.getApartments().size());
    }

    @Test
    @DisplayName("Test adding and updating Apartments to Meetings")
    void addApartmentToMeetingAndUpdateDell(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998",
                "Early meeting", topics1, null);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartment -> apartmentDAO.addUpdateApartment(apartment, ProcessToDo.NEW));
        testHouseMeeting.setApartments(apartmentDAO.loadAllApartments());

        Long id = houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting);

        HouseMeeting houseMeetingWillBeUpdated = houseMeetingDAO.getHouseMeetingById(id);
        houseMeetingWillBeUpdated.delApartment(testApartment1);
        houseMeetingDAO.addUpdateHouseMeeting(houseMeetingWillBeUpdated);
        System.out.println(houseMeetingWillBeUpdated);

        HouseMeeting returnedHouseMeeting = houseMeetingDAO.getHouseMeetingById(id);
        System.out.println(returnedHouseMeeting);
        // Then: Verify the output or expected result
        assertNotNull(returnedHouseMeeting.getApartments());
        assertEquals(houseMeetingWillBeUpdated.getApartments().size(), returnedHouseMeeting.getApartments().size());
    }

    @Test
    @DisplayName("Test adding and updating Apartments to Meetings")
    void addApartmentToMeetingAndUpdateAdd(){
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1);

        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998",
                "Early meeting", topics1, null);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartment -> apartmentDAO.addUpdateApartment(apartment, ProcessToDo.NEW));
        testHouseMeeting.setApartments(apartmentDAO.loadAllApartments());

        Long id = houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting);

        HouseMeeting houseMeetingWillBeUpdated = houseMeetingDAO.getHouseMeetingById(id);
        houseMeetingWillBeUpdated.addApartment(testApartment2);
        houseMeetingDAO.addUpdateHouseMeeting(houseMeetingWillBeUpdated);
        System.out.println(houseMeetingWillBeUpdated);

        HouseMeeting returnedHouseMeeting = houseMeetingDAO.getHouseMeetingById(id);
        System.out.println(returnedHouseMeeting);
        // Then: Verify the output or expected result
        assertNotNull(returnedHouseMeeting.getApartments());
        assertEquals(houseMeetingWillBeUpdated.getApartments().size(), returnedHouseMeeting.getApartments().size());
    }

}