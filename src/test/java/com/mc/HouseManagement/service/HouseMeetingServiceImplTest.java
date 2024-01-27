package com.mc.HouseManagement.service;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
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

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.addUpdateHouseMeeting(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.addUpdateHouseMeeting(addUpdateHouseMeeting);
        verify(houseMeetingDAO).addUpdateHouseMeeting(eq(addUpdateHouseMeeting.getHouseMeeting()));
        assertEquals(1L, result);
    }

    @Test
    void getHouseMeetingById() {
        // Given: Setup object or precondition
        // TestVariables.HOUSE_MEETING
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(eq(houseMeetingID))).thenReturn(TestVariables.HOUSE_MEETING);

        // Then: Verify the output or expected result
        HouseMeeting result = houseMeetingService.getHouseMeetingById(houseMeetingID);
        verify(houseMeetingDAO).getHouseMeetingById(eq(houseMeetingID));
        assertEquals(result, TestVariables.HOUSE_MEETING);

    }

    @Test
    void getAllHouseMeetings() {
        // Given: Setup object or precondition
        // TestVariables.HOUSE_MEETING_LIST

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getAllHouseMeetings()).thenReturn(TestVariables.HOUSE_MEETING_LIST);

        // Then: Verify the output or expected result
        List<HouseMeeting> result = houseMeetingService.getAllHouseMeetings();

        verify(houseMeetingDAO).getAllHouseMeetings();
        assertEquals(TestVariables.HOUSE_MEETING_LIST, result);

    }

    @Test
    void deleteHouseMeetingByIdIsInDB() {
        // Given: Setup object or precondition
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.deleteHouseMeetingByIdById(houseMeetingID)).thenReturn(houseMeetingID);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.deleteHouseMeetingById(houseMeetingID);
        verify(houseMeetingDAO).deleteHouseMeetingByIdById(houseMeetingID);
        assertEquals(houseMeetingID, result);
    }

    @Test
    void deleteHouseMeetingByIdIsNotInDB() {
        // Given: Setup object or precondition
        Long houseMeetingID = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.deleteHouseMeetingByIdById(houseMeetingID)).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = houseMeetingService.deleteHouseMeetingById(houseMeetingID);
        verify(houseMeetingDAO).deleteHouseMeetingByIdById(houseMeetingID);
        assertNull(result);
    }

    @Test
    void addApartmentToHouseMeeting() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.HOUSE_MEETING
        // TestVariables.APARTMENT

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(TestVariables.HOUSE_MEETING);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(TestVariables.APARTMENT);
        when(houseMeetingDAO.addUpdateHouseMeeting(TestVariables.HOUSE_MEETING)).thenReturn(houseMeetingId);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(houseMeetingId, returnedValue);
    }

    @Test
    void addApartmentToHouseMeetingHouseMeetingNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.APARTMENT

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(null);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(TestVariables.APARTMENT);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-1L, returnedValue);
    }

    @Test
    void addApartmentToHouseMeetingApartmentNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.HOUSE_MEETING


        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(TestVariables.HOUSE_MEETING);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(null);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.addApartmentToHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-2L, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeeting() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.HOUSE_MEETING
        // TestVariables.APARTMENT

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(TestVariables.HOUSE_MEETING);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(TestVariables.APARTMENT);
        when(houseMeetingDAO.addUpdateHouseMeeting(TestVariables.HOUSE_MEETING)).thenReturn(houseMeetingId);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(houseMeetingId, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeetingHouseMeetingNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.APARTMENT

        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(null);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(TestVariables.APARTMENT);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-1L, returnedValue);
    }

    @Test
    void delApartmentFromHouseMeetingApartmentNotFound() {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        Long apartmentId = TestVariables.APARTMENT.getId();
        // TestVariables.HOUSE_MEETING


        // When: Action or behavior that we are going to test
        when(houseMeetingDAO.getHouseMeetingById(houseMeetingId)).thenReturn(TestVariables.HOUSE_MEETING);
        when(apartmentDAO.getApartmentById(apartmentId)).thenReturn(null);

        // Then: Verify the output or expected result
        Long returnedValue = houseMeetingService.delApartmentFromHouseMeeting(houseMeetingId, apartmentId);

        assertNotNull(returnedValue);
        assertEquals(-2L, returnedValue);
    }
}