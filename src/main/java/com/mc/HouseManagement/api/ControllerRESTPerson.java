package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.api.dto.person.AddApartmentToPerson;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPersons;
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
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller person started!");
    }

    @RequestMapping("")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/")
    public <T extends Person> List<Person> getAllUsers() {
        List<Person> outputPersons = personService.loadAllPersons(Person.class);
        Collections.sort(outputPersons);
        return outputPersons;
    }

    @GetMapping("/{id}")
    public <T extends Person> T getUsersByID(@PathVariable Long id){
        T loaded = personService.loadPersonByID(id);
        if (loaded==null)
            throw new DataNotFoundException("User not found on ID: "+id);
        return loaded;
    }

    @PostMapping("/")
    public Long addPerson(@Valid @RequestBody AddUpdateNewPerson addUpdateNewPerson){
        return personService.addUpdatePerson(addUpdateNewPerson);
    }

    @PutMapping("/")
    public Long updatePerson(@Valid @RequestBody AddUpdateNewPerson addUpdateNewPerson){
        Person person = personService.loadPersonByID(addUpdateNewPerson.getId());
        if (person != null && addUpdateNewPerson.getPersonWitType().getClass() != person.getClass()){
            personService.deleteById(addUpdateNewPerson.getId());
        } else if (person == null) {
            throw new DataNotFoundException(addUpdateNewPerson.getTypeOfUser()
                    + " not found with id: "
                    + addUpdateNewPerson.getId());
        }
        return personService.addUpdatePerson(addUpdateNewPerson);
    }

    @DeleteMapping("/{id}")
    public Long deletePerson(@PathVariable Long id){
        Long idOfDelPerson = personService.deleteById(id);
        if (idOfDelPerson==-1)
            throw new DataNotFoundException("User not found with id: "+id);
        return idOfDelPerson;
    }

    @GetMapping("/name/{name}")
    public <T extends Person> List<Person> getUsersByNameOrLastName(@PathVariable String name){
        if (personService.loadPersonByLastOrFirstName(name).isEmpty())
            throw new DataNotFoundException("User not found with firs name or last name: " + name);
        return personService.loadPersonByLastOrFirstName(name);
    }

    @PostMapping("/multiple")
    public int addMultiplePersons(@Valid @RequestBody AddUpdateNewPersons addUpdateNewPersons){
        addUpdateNewPersons.getPersons().forEach(personService::addUpdatePerson);
        return addUpdateNewPersons.getPersons().size();
    }

    @PutMapping("/multiple")
    public int updatePersons(@Valid @RequestBody AddUpdateNewPersons addUpdateNewPersons){
        System.out.println(addUpdateNewPersons.getPersons().get(0).getFirstName());
        addUpdateNewPersons.getPersons().forEach(person -> {
            Person personFromDB = personService.loadPersonByID(person.getId());
            System.out.println(person);
            if (personFromDB == null){
                throw new DataNotFoundException(person.getTypeOfUser()
                        + " not found with id: "
                        + person.getId());
            }
        });
        return addUpdateNewPersons.getPersons().size();
    }

    @PostMapping("/apartment")
    public Long addApartmentToPerson(@Valid @RequestBody AddApartmentToPerson addApartmentToPerson){
        Long idOfUpdatedPerson = personService.addApartmentToPerson(addApartmentToPerson.getIdPerson(), addApartmentToPerson.getIdApartment());
        if (idOfUpdatedPerson==-1)
            throw new DataNotFoundException("Person not found with ID: " +addApartmentToPerson.getIdPerson());
        else if (idOfUpdatedPerson==-2) {
            throw new DataNotFoundException("Apartment not found with ID: " +addApartmentToPerson.getIdApartment());
        }
        return idOfUpdatedPerson;
    }

    @DeleteMapping("/apartment")
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