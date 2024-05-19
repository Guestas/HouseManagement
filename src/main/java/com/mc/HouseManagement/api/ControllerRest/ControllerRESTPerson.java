package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.api.Controller.ControllerPerson;
import com.mc.HouseManagement.api.dto.person.*;
import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Person;
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
        Logger logger = LoggerFactory.getLogger(ControllerPerson.class);
        logger.debug("Rest controller person started!");
    }

    @RequestMapping("")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/")
    public List<Person> getAllUsers() {
        List<Person> outputPersons = personService.getAllPersonsByType("Person");
        Collections.sort(outputPersons);
        return outputPersons;
    }

    @GetMapping("/{personId}")
    public Person getUsersByID(@PathVariable Long personId){
        Person loaded = personService.getPersonById(personId);
        if (loaded==null)
            throw new DataNotFoundException("User not found on ID: "+personId);
        return loaded;
    }

    @PostMapping("/")
    public Long addPerson(@Valid @RequestBody AddUpdatePerson addUpdatePerson){
        return personService.addUpdatePerson(addUpdatePerson);
    }

    @PutMapping("/")
    public Long updatePerson(@Valid @RequestBody AddUpdatePerson addUpdatePerson){
        Person person = personService.getPersonById(addUpdatePerson.getId());
        if (person != null && addUpdatePerson.getPersonWitType().getClass() != person.getClass()){
            personService.deletePersonById(addUpdatePerson.getId());
        } else if (person == null) {
            throw new DataNotFoundException(addUpdatePerson.getTypeOfUser()
                    + " not found with id: "
                    + addUpdatePerson.getId());
        }
        return personService.addUpdatePerson(addUpdatePerson);
    }

    @DeleteMapping("/{personId}")
    public Long deletePerson(@PathVariable Long personId){
        Long idOfDelPerson = personService.deletePersonById(personId);
        if (idOfDelPerson==-1)
            throw new DataNotFoundException("User not found with id: "+personId);
        return idOfDelPerson;
    }

    @GetMapping("/name/{name}")
    public List<Person> getUsersByNameOrLastName(@PathVariable String name){
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
    public int addMultiplePersons(@Valid @RequestBody AddUpdatePersons addUpdatePersons){
        addUpdatePersons.getPersons().forEach(personService::addUpdatePerson);
        return addUpdatePersons.getPersons().size();
    }

    @PutMapping("/multiple/")
    public int updatePersons(@Valid @RequestBody AddUpdatePersons addUpdatePersons){
        addUpdatePersons.getPersons().forEach(person -> {
            Person personFromDB = personService.getPersonById(person.getId());
            if (personFromDB == null){
                throw new DataNotFoundException(person.getTypeOfUser()
                        + " not found with id: "
                        + person.getId());
            }
        });
        return addUpdatePersons.getPersons().size();
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