package com.mc.HouseManagement.api;

import com.mc.HouseManagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ControllerREST {

    private final PersonService personService;

    @Autowired
    public ControllerREST(PersonService personService) {

        this.personService = personService;
    }

    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        System.out.println("Rest controller started!");

    }

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}