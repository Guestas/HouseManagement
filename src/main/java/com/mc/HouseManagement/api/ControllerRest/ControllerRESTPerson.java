package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.api.Controller.ControllerApartment;
import com.mc.HouseManagement.api.dto.person.*;
import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.User;
import com.mc.HouseManagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class ControllerRESTPerson {

    private final PersonService personService;

    @Autowired
    public ControllerRESTPerson(PersonService personService) {
        this.personService = personService;
    }



    // Created request's by type POST, PUT, GET and DEL this will communicate with Web browser or Postman ...
    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller person started!");
    }

    @RequestMapping("")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/")
    public <T extends Person> List<Person> getAllUsers() {
        List<Person> outputPersons = personService.getAllPersonsByClassType(Person.class);
        Collections.sort(outputPersons);
        return outputPersons;
    }

    @GetMapping("/{personId}")
    public <T extends Person> T getUsersByID(@PathVariable Long personId){
        T loaded = personService.getPersonById(personId);
        if (loaded==null)
            throw new DataNotFoundException("User not found on ID: "+personId);
        return loaded;
    }

    @PostMapping("/")
    public Long addPerson(@Valid @RequestBody AddUpdatePerson AddUpdatePerson){
        return personService.addUpdatePerson(AddUpdatePerson);
    }

    @PutMapping("/")
    public Long updatePerson(@Valid @RequestBody AddUpdatePerson AddUpdatePerson){
        Person person = personService.getPersonById(AddUpdatePerson.getId());
        if (person != null && AddUpdatePerson.getPersonWitType().getClass() != person.getClass()){
            personService.deletePersonById(AddUpdatePerson.getId());
        } else if (person == null) {
            throw new DataNotFoundException(AddUpdatePerson.getTypeOfUser()
                    + " not found with id: "
                    + AddUpdatePerson.getId());
        }
        return personService.addUpdatePerson(AddUpdatePerson);
    }

    @DeleteMapping("/{personId}")
    public Long deletePerson(@PathVariable Long personId){
        Long idOfDelPerson = personService.deletePersonById(personId);
        if (idOfDelPerson==-1)
            throw new DataNotFoundException("User not found with id: "+personId);
        return idOfDelPerson;
    }

    @GetMapping("/name/{name}")
    public <T extends Person> List<Person> getUsersByNameOrLastName(@PathVariable String name){
        if (personService.getPersonByLastOrFirstName(name).isEmpty())
            throw new DataNotFoundException("User not found with firs name or last name: " + name);
        return personService.getPersonByLastOrFirstName(name);
    }

    @GetMapping("/apartment/")
    public List<ReturnMultiplePersonsForApartment> getUsersByApartmentId(@Valid @RequestBody GetPersonsByApartmentId getPersonsByApartmentId){
        return personService.getPersonsByApartmentsIdAndType(
                    getPersonsByApartmentId.getIdApartment(),
                        getPersonsByApartmentId.getPersonType()
                );
    }

    @PostMapping("/multiple/")
    public int addMultiplePersons(@Valid @RequestBody AddUpdatePersons AddUpdatePersons){
        AddUpdatePersons.getPersons().forEach(personService::addUpdatePerson);
        return AddUpdatePersons.getPersons().size();
    }

    @PutMapping("/multiple/")
    public int updatePersons(@Valid @RequestBody AddUpdatePersons AddUpdatePersons){
        AddUpdatePersons.getPersons().forEach(person -> {
            Person personFromDB = personService.getPersonById(person.getId());
            if (personFromDB == null){
                throw new DataNotFoundException(person.getTypeOfUser()
                        + " not found with id: "
                        + person.getId());
            }
        });
        return AddUpdatePersons.getPersons().size();
    }

    @PostMapping("/apartment/")
    public Long addApartmentToPerson(@Valid @RequestBody AddApartmentToPerson addApartmentToPerson){
        Long idOfUpdatedPerson = personService.addApartmentToPerson(addApartmentToPerson.getIdPerson(), addApartmentToPerson.getIdApartment());
        if (idOfUpdatedPerson==-1)
            throw new DataNotFoundException("Person not found with ID: " +addApartmentToPerson.getIdPerson());
        else if (idOfUpdatedPerson==-2) {
            throw new DataNotFoundException("Apartment not found with ID: " +addApartmentToPerson.getIdApartment());
        }
        return idOfUpdatedPerson;
    }

    @DeleteMapping("/apartment/")
    public Long delApartmentToPerson(@Valid @RequestBody AddApartmentToPerson addApartmentToPerson){
        Long idOfUpdatedPerson = personService.delApartmentFromPerson(addApartmentToPerson.getIdPerson(), addApartmentToPerson.getIdApartment());
        if (idOfUpdatedPerson==-1)
            throw new DataNotFoundException("Person not found with ID: " +addApartmentToPerson.getIdPerson());
        else if (idOfUpdatedPerson==-2) {
            throw new DataNotFoundException("Apartment not found with ID: " +addApartmentToPerson.getIdApartment());
        }
        return idOfUpdatedPerson;
    }

}