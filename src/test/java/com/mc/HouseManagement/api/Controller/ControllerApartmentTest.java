package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.service.ApartmentService;
import com.mc.HouseManagement.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerApartmentTest{

    private final String requestMapping = "/api/v2/apartments";

    @Mock
    private ApartmentService apartmentService;

    @Mock
    private PersonService personService;

    @InjectMocks
    private ControllerApartment controllerApartment;

    @Test
    @DisplayName("Can get all apartments.")
    void getAllApartmentsTh() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT_LIST

        // When: Action or behavior that we are going to test
        when(apartmentService.getAllApartments()).thenReturn(TestVariables.APARTMENT_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("allApartmentList", TestVariables.APARTMENT_LIST))
                .andExpect(view().name("apartments"));
    }

    @Test
    @DisplayName("Can get all persons in apartment.")
    void getAllPeopleInApartment() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT_LIST
        Long apartmentId = 1L;

        // When: Action or behavior that we are going to test
        when(apartmentService.getApartmentById(apartmentId)).thenReturn(TestVariables.APARTMENT);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + apartmentId))
                .andExpect(status().isOk())
                .andExpect(view().name("apartment"))
                .andExpect(MockMvcResultMatchers.model().attribute("apartment", TestVariables.APARTMENT));
    }


}