package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.api.ControllerRest.ControllerRESTApartment;
import com.mc.HouseManagement.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerApartmentTest {

    private final String requestMapping = "/api/v2/apartments";

    @Mock
    private ApartmentService apartmentService;

    @InjectMocks
    private ControllerApartment controllerApartment;

    @Test
    void getAllApartmentsTh() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT_LIST

        // When: Action or behavior that we are going to test
        when(apartmentService.getAllApartments()).thenReturn(TestVariables.APARTMENT_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerApartment).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("allApartmentList", TestVariables.APARTMENT_LIST))
                .andExpect(MockMvcResultMatchers.view().name("apartments"));
    }
}