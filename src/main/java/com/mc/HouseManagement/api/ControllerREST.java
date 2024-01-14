package com.mc.HouseManagement.api;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.User;
import com.mc.HouseManagement.service.OwnerService;
import com.mc.HouseManagement.service.PersonService;
import com.mc.HouseManagement.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ControllerREST {

    private final OwnerService ownerService;
    private final UserService userService;
    private final PersonService personService;

    @Autowired
    public ControllerREST(OwnerService ownerService, UserService userService, PersonService personService) {
        this.ownerService = ownerService;
        this.userService = userService;
        this.personService = personService;
    }

    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        System.out.println("Rest controller started!");
        Owner testOwner1 = Owner.createOwner("a","a","e",
                123456,null);
        Owner testOwner2 = Owner.createOwner("b","b","e",
                123456,null);
        List<Owner> expectedOwnersList = Arrays.asList(testOwner1,testOwner2);
        // When: Action or behavior that we are going to test
        expectedOwnersList.forEach(personService::addPerson);


        User testUser1 = User.createUser("c","c","e",
                123456,null);
        User testUser2 = User.createUser("d","d","e",
                123456,null);
        List<User> expectedUsersList = Arrays.asList(testUser1, testUser2);
        // When: Action or behavior that we are going to test
        expectedUsersList.forEach(personService::addPerson);


        personService.loadAllPersons(User.class).stream()
                .map(Person::getFirstName)
                .toList()
                .forEach(System.out::println);
        System.out.println(personService.getPersonById(1L, Owner.class));

        //personService.deleteAllPersons();


    }

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}