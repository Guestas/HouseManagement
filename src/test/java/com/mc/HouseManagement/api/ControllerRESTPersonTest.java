package com.mc.HouseManagement.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerRESTPersonTest {
    @Autowired
    ControllerRESTPerson controllerREST;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception{
        assertThat(controllerREST).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/persons",
                String.class)).contains("Hello, World");
    }

}