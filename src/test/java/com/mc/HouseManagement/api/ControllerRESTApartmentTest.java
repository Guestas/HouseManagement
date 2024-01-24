package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.dto.apartment.AddApartment;
import com.mc.HouseManagement.api.dto.apartment.AddApartments;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.service.ApartmentService;
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
    void testGetAllApartments() throws Exception {
        // Given: Setup object or precondition
        List<Apartment> expectedApartments = Arrays.asList(
                Apartment.createApartment(5, 4, 5,
                        2553, "street1", null, null),
                Apartment.createApartment(8, 8, 4,
                        2558, "street2", null, null)
        );

        // When: Action or behavior that we are going to test
        when(apartmentService.loadAllApartments()).thenReturn(expectedApartments);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(UtilityMethods.asJsonString(expectedApartments)));

        verify(apartmentService, times(1)).loadAllApartments();
    }

    @Test
    void testGetApartmentById() throws Exception {
        // Given: Setup object or precondition
        Long apartmentId = 1L;
        Apartment expectedApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null, null);

        // When: Action or behavior that we are going to test
        when(apartmentService.getApartmentById(apartmentId)).thenReturn(expectedApartment);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + apartmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedApartment.getId()))
                .andReturn();
        verify(apartmentService, times(1)).getApartmentById(apartmentId);
    }

    @Test
    void testAddApartments() throws Exception {
        // Given
        AddApartments addApartments = new AddApartments(Arrays.asList(
                AddApartment.getAddApartment(5, 4, 5,
                        2553, "street1"),
                AddApartment.getAddApartment(8, 3, 2,
                        2553, "street2")
        ));

        // When/Then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTApartment).build();

        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilityMethods.asJsonString(addApartments)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2")); // Assuming you are returning the size of the list

        // Verify that the apartmentService method was called for each apartment
        verify(apartmentService, times(addApartments.getApartments().size())).addUpdateApartment(any());
    }
}