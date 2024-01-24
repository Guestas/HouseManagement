package com.mc.HouseManagement.api;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
class ControllerRESTPersonTest {
    String requestMapping = "/api/v1/persons";

    @Mock
    private PersonService personService;

    @InjectMocks
    private ControllerRESTPerson controllerRESTPerson;

    @Test
    void contextLoads() throws Exception{
        assertThat(controllerRESTPerson).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessageTwo() throws Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllerRESTPerson).build();

        // Then: Verify the output or expected result
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello, World"));
    }
    //TODO finish testing

}