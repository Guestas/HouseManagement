package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.api.UtilityMethods;
import com.mc.HouseManagement.service.ApartmentService;
import org.junit.jupiter.api.DisplayName;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
class ControllerRESTApartmentTest {
    String requestMapping = "/api/v1/apartments";

    @Mock
    private ApartmentService apartmentService;

    @InjectMocks
    private ControllerRESTApartment controllerRESTApartment;

    @Test
    void contextLoads() throws Exception{
        assertThat(controllerRESTApartment).isNotNull();
    }

    @Test
    @DisplayName("Can get all apartments.")
    void testGetAllApartments() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT_LIST

        // When: Action or behavior that we are going to test
        when(apartmentService.getAllApartments()).thenReturn(TestVariables.APARTMENT_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(UtilityMethods.asJsonString(TestVariables.APARTMENT_LIST)));

        verify(apartmentService, times(1)).getAllApartments();
    }

    @Test
    @DisplayName("Can get apartment by id.")
    void testGetApartmentById() throws Exception {
        // Given: Setup object or precondition
        Long apartmentId = 1L;
        // TestVariables.ADD_APARTMENT1

        // When: Action or behavior that we are going to test
        when(apartmentService.getApartmentById(apartmentId)).thenReturn(TestVariables.ADD_APARTMENT1.getApartment());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + apartmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(TestVariables.ADD_APARTMENT1.getApartment().getId()))
                .andReturn();
        verify(apartmentService, times(1)).getApartmentById(apartmentId);
    }

    @Test
    @DisplayName("Can add apartment.")
    void testAddApartments() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_APARTMENTS

        // When: Action or behavior that we are going to test
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilityMethods.asJsonString(TestVariables.ADD_APARTMENTS)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2")); // Assuming you are returning the size of the list

        // Then: Verify the output or expected result
        verify(apartmentService, times(TestVariables.ADD_APARTMENTS.getApartments().size())).addUpdateApartment(any());
    }
}