package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.HouseMeetingDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HouseMeetingServiceImplTest {
    @Mock private HouseMeetingDAO houseMeetingDAO;
    @Mock private ApartmentDAO apartmentDAO;

    private HouseMeetingService houseMeetingService;

    @BeforeEach
    void setUp(){
        houseMeetingService = new HouseMeetingServiceImpl(houseMeetingDAO, apartmentDAO);
    }

    @Test
    void addUpdateHouseMeeting() {
        // Given: Setup object or precondition
        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();
        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.addUpdateHouseMeeting(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.addUpdateHouseMeeting(testHouseMeeting);
        verify(houseMeetingDAO).addUpdateHouseMeeting(eq(testHouseMeeting));
        assertEquals(1L, result);
    }

    @Test
    void getHouseMeetingById() {
        // Given: Setup object or precondition
        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(eq(houseMeetingID))).thenReturn(testHouseMeeting);

        // Then: Verify the output or expected result
        HouseMeeting result = houseMeetingService.getHouseMeetingById(houseMeetingID);
        verify(houseMeetingDAO).getHouseMeetingById(eq(houseMeetingID));
        assertEquals(result, testHouseMeeting);

    }

    @Test
    void loadAllHouseMeetings() {
        // Given: Setup object or precondition
        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting1 = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting1", topics);
        AddUpdateHouseMeeting addUpdateHouseMeeting2 = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1999", "Early meeting2", topics);
        HouseMeeting testHouseMeeting1 = addUpdateHouseMeeting1.getHouseMeeting();
        HouseMeeting testHouseMeeting2 = addUpdateHouseMeeting2.getHouseMeeting();
        List<HouseMeeting> expectedResult = Arrays.asList(testHouseMeeting1,testHouseMeeting2);

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.loadAllHouseMeetings()).thenReturn(expectedResult);

        // Then: Verify the output or expected result
        List<HouseMeeting> result = houseMeetingService.loadAllHouseMeetings();

        verify(houseMeetingDAO).loadAllHouseMeetings();
        assertEquals(expectedResult, result);

    }

    @Test
    void deleteHouseMeetingIsInDB() {
        // Given: Setup object or precondition
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.deleteHouseMeeting(houseMeetingID)).thenReturn(houseMeetingID);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.deleteHouseMeeting(houseMeetingID);
        verify(houseMeetingDAO).deleteHouseMeeting(houseMeetingID);
        assertEquals(houseMeetingID, result);
    }

    @Test
    void deleteHouseMeetingIsNotInDB() {
        // Given: Setup object or precondition
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.deleteHouseMeeting(houseMeetingID)).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.deleteHouseMeeting(houseMeetingID);
        verify(houseMeetingDAO).deleteHouseMeeting(houseMeetingID);
        assertNull(result);
    }

    @Test
    void addApartmentToHouseMeeting() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting1", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();

        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(testHouseMeeting);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(testApartment);
        when(houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting)).thenReturn(houseMeetingId);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(houseMeetingId, returnedValue);
    }

    @Test
    void addApartmentToHouseMeetingHouseMeetingNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(null);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(testApartment);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-1L, returnedValue);
    }

    @Test
    void addApartmentToHouseMeetingApartmentNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting1", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();


        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(testHouseMeeting);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(null);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-2L, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeeting() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting1", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();

        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(testHouseMeeting);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(testApartment);
        when(houseMeetingDAO.addUpdateHouseMeeting(testHouseMeeting)).thenReturn(houseMeetingId);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(houseMeetingId, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeetingHouseMeetingNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(null);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(testApartment);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-1L, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeetingApartmentNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        Long apartmentId = 101L;

        List<String> topics = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998", "Early meeting1", topics);
        HouseMeeting testHouseMeeting = addUpdateHouseMeeting.getHouseMeeting();


        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(testHouseMeeting);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(null);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-2L, returnedValue);
    }
}