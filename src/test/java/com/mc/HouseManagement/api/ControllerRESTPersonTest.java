package com.mc.HouseManagement.api;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.api.dto.person.AddApartmentToPerson;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.service.PersonService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
class ControllerRESTPersonTest {

    //variables for testing
    private final String requestMapping = "/api/v1/persons";

    @Mock
    private PersonService personService;

    @InjectMocks
    private ControllerRESTPerson controllerRESTPerson;

    @Test
    void contextLoads() throws Exception{
        assertThat(controllerRESTPerson).isNotNull();
    }

    @Test
    void testGreetingShouldReturnDefaultMessageTwo() throws Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Hello, World"));
    }
    //TODO finish testing

    @Test
    void testGetAllPPersons() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.PERSON_LIST

        // When: Action or behavior that we are going to test
        when(personService.loadAllPersons(any())).thenReturn(TestVariables.PERSON_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(asJsonString(TestVariables.PERSON_LIST)));

        verify(personService, times(1)).loadAllPersons(any());

    }

    @Test
    void testGetUsersByID() throws Exception {
        // Given: Setup object or precondition
        Long personId = TestVariables.PERSON.getId();
        // TestVariables.PERSON

        // When: Action or behavior that we are going to test
        when(personService.loadPersonByID(personId)).thenReturn(TestVariables.PERSON);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/" + personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value(TestVariables.PERSON.getId()))
                .andReturn();
        verify(personService, times(1)).loadPersonByID(personId);
    }

    @Test
    void testAddPerson() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_NEW_PERSON1

        // When: Action or behavior that we are going to test
        when(personService.addUpdatePerson(TestVariables.ADD_UPDATE_NEW_PERSON1))
                .thenReturn(TestVariables.ADD_UPDATE_NEW_PERSON1.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(TestVariables.ADD_UPDATE_NEW_PERSON1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));

        verify(personService, times(1)).addUpdatePerson(TestVariables.ADD_UPDATE_NEW_PERSON1);

    }

    @Test
    void testUpdatePerson() throws Exception {
        // Given: Setup object or precondition
        // TestVariables.ADD_UPDATE_NEW_PERSON1

        // When: Action or behavior that we are going to test
        when(personService.loadPersonByID(any())).thenReturn(TestVariables.ADD_UPDATE_NEW_PERSON1.getPersonWitType());
        when(personService.addUpdatePerson(TestVariables.ADD_UPDATE_NEW_PERSON1))
                .thenReturn(TestVariables.ADD_UPDATE_NEW_PERSON1.getId());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.put(requestMapping+"/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(TestVariables.ADD_UPDATE_NEW_PERSON1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));

        verify(personService, times(1)).addUpdatePerson(TestVariables.ADD_UPDATE_NEW_PERSON1);

    }

    @Test
    void testDeletePerson() throws Exception {
        // Given: Setup object or precondition
        Long personId = TestVariables.PERSON.getId();

        // When: Action or behavior that we are going to test
        when(personService.deleteById(personId)).thenReturn(personId);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.delete(requestMapping+"/{id}", personId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(personService, times(1)).deleteById(personId);
    }

    @Test
    void testGetUsersByNameOrLastName() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
    }

    @Test
    void testAddMultiplePersons() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
    }

    @Test
    void testUpdatePersons() throws Exception {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        when(personService.loadPersonByLastOrFirstName("Black")).thenReturn(TestVariables.PERSON_LIST);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result

        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"/name/{name}", "Black")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(TestVariables.PERSON_LIST)));
        verify(personService, times(2)).loadPersonByLastOrFirstName("Black");
    }

    @Test
    void testAddApartmentToPerson() throws Exception {
        // Given: Setup object or precondition
        AddApartmentToPerson addApartmentToPerson = new AddApartmentToPerson(1L, 2L);

        // When: Action or behavior that we are going to test
        when(personService.addApartmentToPerson(1L,2L)).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.post(requestMapping+"/apartment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addApartmentToPerson))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(personService, times(1)).addApartmentToPerson(1L, 2L);
    }

    @Test
    void testDelApartmentToPerson() throws Exception {
        // Given: Setup object or precondition
        AddApartmentToPerson addApartmentToPerson = new AddApartmentToPerson(1L, 2L);

        // When: Action or behavior that we are going to test
        when(personService.delApartmentFromPerson(1L,2L)).thenReturn(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.delete(requestMapping+"/apartment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addApartmentToPerson))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));

        verify(personService, times(1)).delApartmentFromPerson(1L, 2L);
    }
}