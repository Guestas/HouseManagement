package com.mc.HouseManagement.api;

import com.mc.HouseManagement.ProcessToDo;
import com.mc.HouseManagement.entity.*;
import com.mc.HouseManagement.service.ApartmentService;
import com.mc.HouseManagement.service.HouseMeetingService;
import com.mc.HouseManagement.service.PersonService;
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

    private final PersonService personService;
    private final ApartmentService apartmentService;
    private final HouseMeetingService houseMeetingService;

    @Autowired
    public ControllerREST(PersonService personService, ApartmentService apartmentService, HouseMeetingService houseMeetingService) {
        this.personService = personService;
        this.apartmentService = apartmentService;
        this.houseMeetingService = houseMeetingService;
    }


    // TODO finish api
    // Created request's by type POST, PUT, GET and DEL this will communicate with Web browser or Postman ...
    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        boolean load = false;
        System.out.println("Rest controller started!");

        Owner testOwner = Owner.createOwner("Bob","Jar","bob@jar.com",
                123456789,null);
        User testUser = User.createUser("Bob","Jar","bob@jar.com",
                123456789,null);
        SoldMovedOut testSoldMovedOut = SoldMovedOut.createSoldMovedOut("Bob","Jar","bob@jar.com",
                123456789,null);
        Person testOwner1 = new Person("Bob","Jara","bob@jar.com",
                123456789,null);
        List<Person> personArrayList = Arrays.asList(testOwner, testUser, testSoldMovedOut, testOwner1);

        if (load) personArrayList.forEach(personService::addUpdatePerson);

        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        if (load) expectedApartmentList.forEach(apartment -> apartmentService.addUpdateApartment(apartment, ProcessToDo.NEW));


        List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
        HouseMeeting testHouseMeeting = HouseMeeting.createHouseMeeting("20-5-1998",
                "Early meeting", topics1, null);
        if (load) houseMeetingService.addUpdateHouseMeeting(testHouseMeeting);

        if (load) System.out.println("loaded");

        //houseMeetingService.addApartmentToHouseMeeting(1L, 2L);
        //personService.addApartmentToPerson(2L, User.class, 2L);

        //houseMeetingService.delApartmentFromHouseMeeting(3L, 3L);
        //personService.delApartmentFromPerson(1L, Owner.class, 3L);

        //testHouseMeeting.setApartments(new ArrayList<>());

        //houseMeetingService.addUpdateHouseMeeting(testHouseMeeting);


        //System.out.println(apartmentService.getApartmentById(1L));
        //System.out.println(houseMeetingService.getHouseMeetingById(2L));
        //System.out.println(personService.loadPersonByID(1L));


        System.out.println(personService.loadPersonByLastOrFirstName("Jar"));



    }


    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }




}