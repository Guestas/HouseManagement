package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.api.ControllerRest.ControllerRESTHouseMeeting;
import com.mc.HouseManagement.api.UtilityMethods;
import com.mc.HouseManagement.api.dto.houseMeetings.AddApartmentToHouseMeeting;
import com.mc.HouseManagement.service.HouseMeetingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.mc.HouseManagement.api.UtilityMethods.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
class ControllerRESTHouseMeetingTest {
    @Mock
    private HouseMeetingService houseMeetingService;

    @InjectMocks
    private ControllerRESTHouseMeeting controllerRESTHouseMeeting;

    String requestMapping = "/api/v1/houseMeetings";


    @Test
    void contextLoads() throws Exception{
        assertThat(controllerRESTHouseMeeting).isNotNull();
    }

    @Test
    void testGetAllHouseMeetings() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.HOUSE_MEETING_LIST


        // When: Action or behavior that we are going to test
        when(houseMeetingService.getAllHouseMeetings()).thenReturn(TestVariables.HOUSE_MEETING_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(TestVariables.HOUSE_MEETING_LIST)));

        verify(houseMeetingService, times(1)).getAllHouseMeetings();
    }

    @Test
    void testGetHouseMeetingById() throws Exception {
        // Given: Setup object or precondition
        Long houseMeetingId = TestVariables.HOUSE_MEETING.getId();
        // TestVariables.HOUSE_MEETING

        // When: Action or behavior that we are going to test
        when(houseMeetingService.getHouseMeetingById(houseMeetingId)).thenReturn(TestVariables.HOUSE_MEETING);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + houseMeetingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(TestVariables.HOUSE_MEETING.getId()))
                .andReturn();
        verify(houseMeetingService, times(1)).getHouseMeetingById(houseMeetingId);
    }

    @Test
    void testAddHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_HOUSE_MEETING1

        // When: Action or behavior that we are going to test
        when(houseMeetingService.addUpdateHouseMeeting(TestVariables.ADD_UPDATE_HOUSE_MEETING1)).thenReturn(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(TestVariables.ADD_UPDATE_HOUSE_MEETING1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));

        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(TestVariables.ADD_UPDATE_HOUSE_MEETING1);
    }

    @Test
    void testUpdateAddHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_HOUSE_MEETING1

        // When: Action or behavior that we are going to test
        when(houseMeetingService.addUpdateHouseMeeting(TestVariables.ADD_UPDATE_HOUSE_MEETING1)).thenReturn(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(TestVariables.ADD_UPDATE_HOUSE_MEETING1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));

        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(TestVariables.ADD_UPDATE_HOUSE_MEETING1);
    }

    @Test
    void testUpdateHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_HOUSE_MEETING1

        // When: Action or behavior that we are going to test
        when(houseMeetingService.getHouseMeetingById(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getId())).thenReturn(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getHouseMeeting());
        when(houseMeetingService.addUpdateHouseMeeting(any())).thenReturn(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.put(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getHouseMeeting())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));  // Assuming your service returns a Long

        verify(houseMeetingService, times(1)).getHouseMeetingById(TestVariables.ADD_UPDATE_HOUSE_MEETING1.getId());
        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(any());
    }

    @Test
    void testDelHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingService.deleteHouseMeetingById(houseMeetingId)).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.delete(requestMapping+"/{id}", houseMeetingId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(houseMeetingService, times(1)).deleteHouseMeetingById(houseMeetingId);
    }

    @Test
    void testAddHouseMeetings() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_HOUSE_MEETINGS

        // When: Action or behavior that we are going to test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/multiple/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilityMethods.asJsonString(TestVariables.ADD_UPDATE_HOUSE_MEETINGS)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));

        verify(houseMeetingService, times(2)).addUpdateHouseMeeting(any());
    }

    @Test
    void testAddApartmentToMeeting() throws Exception {
        // Given: Setup object or precondition
        AddApartmentToHouseMeeting addApartmentToHouseMeeting = new AddApartmentToHouseMeeting(1L, 101L);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.addApartmentToHouseMeeting(any(), any())).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/apartment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addApartmentToHouseMeeting))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(houseMeetingService, times(1)).addApartmentToHouseMeeting(1L, 101L);
    }

    @Test
    void testDelApartmentToMeeting() throws Exception {
        // Given: Setup object or precondition
        AddApartmentToHouseMeeting addApartmentToHouseMeeting = new AddApartmentToHouseMeeting(1L, 101L);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.delApartmentFromHouseMeeting(any(), any())).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.delete(requestMapping+"/apartment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addApartmentToHouseMeeting))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(houseMeetingService, times(1)).delApartmentFromHouseMeeting(1L, 101L);
    }

}