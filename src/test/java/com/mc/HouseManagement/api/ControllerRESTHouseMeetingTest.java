package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.dto.houseMeetings.AddApartmentToHouseMeeting;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeetings;
import com.mc.HouseManagement.entity.HouseMeeting;
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

import java.util.Arrays;
import java.util.List;

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
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        List<HouseMeeting> expectedHouseMeetings = Arrays.asList(
            HouseMeeting.createHouseMeeting("20-5-1998",
                    "Early meeting1", topics1, null),
            HouseMeeting.createHouseMeeting("20-5-1999",
                    "Early meeting2", topics1, null)
        );


        // When: Action or behavior that we are going to test
        when(houseMeetingService.loadAllHouseMeetings()).thenReturn(expectedHouseMeetings);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(expectedHouseMeetings)));

        verify(houseMeetingService, times(1)).loadAllHouseMeetings();
    }

    @Test
    void testGetHouseMeetingById() throws Exception {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998",
                "Early meeting1", topics1, null);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.getHouseMeetingById(houseMeetingId)).thenReturn(testHouseMeeting);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + houseMeetingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testHouseMeeting.getId()))
                .andReturn();
        verify(houseMeetingService, times(1)).getHouseMeetingById(houseMeetingId);
    }

    @Test
    void testAddHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting(0L,"20-5-1998",
                "Early meeting1", topics1);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.addUpdateHouseMeeting(addUpdateHouseMeeting)).thenReturn(0L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addUpdateHouseMeeting)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));

        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(addUpdateHouseMeeting);
    }

    @Test
    void updateAddHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        AddUpdateHouseMeeting testAdUpdate = AddUpdateHouseMeeting.createAddUpsateHouseMeeting(0L,"20-5-1998",
                "Early meeting1", topics1);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.addUpdateHouseMeeting(testAdUpdate)).thenReturn(testAdUpdate.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testAdUpdate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));

        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(testAdUpdate);
    }

    @Test
    void testUpdateHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        AddUpdateHouseMeeting testAdUpdate = AddUpdateHouseMeeting.createAddUpsateHouseMeeting("20-5-1998",
                "Early meeting1", null);
        testAdUpdate.setId(1L);

        // When: Action or behavior that we are going to test
        when(houseMeetingService.getHouseMeetingById(testAdUpdate.getId())).thenReturn(testAdUpdate.getHouseMeeting());
        when(houseMeetingService.addUpdateHouseMeeting(any())).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.put(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testAdUpdate.getHouseMeeting())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));  // Assuming your service returns a Long

        verify(houseMeetingService, times(1)).getHouseMeetingById(testAdUpdate.getId());
        verify(houseMeetingService, times(1)).addUpdateHouseMeeting(any());
    }

    @Test
    void testDelHouseMeeting() throws Exception {
        // Given: Setup object or precondition
        Long houseMeetingId = 1L;

        // When: Action or behavior that we are going to test
        when(houseMeetingService.deleteHouseMeeting(houseMeetingId)).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.delete(requestMapping+"/{id}", houseMeetingId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(houseMeetingService, times(1)).deleteHouseMeeting(houseMeetingId);
    }

    @Test
    void testAddHouseMeetings() throws Exception {
        // Given: Setup object or precondition
        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");

        AddUpdateHouseMeetings expectedHouseMeetings = new AddUpdateHouseMeetings(Arrays.asList(
                AddUpdateHouseMeeting.createAddUpsateHouseMeeting(1L,"20-5-1998",
                        "Early meeting1", null),
                AddUpdateHouseMeeting.createAddUpsateHouseMeeting(2L,"20-5-1999",
                        "Early meeting1", null)
        ));

        // When: Action or behavior that we are going to test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTHouseMeeting).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/multiple/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilityMethods.asJsonString(expectedHouseMeetings)))
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