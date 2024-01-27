package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/api/v2/persons")
@Controller //returning pages bur RestController returning only data
public class ControllerPerson {

    private final PersonService personService;

    @Autowired
    public ControllerPerson(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct //caled once after start to download new data
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Controller person started!");
    }

    @GetMapping("/")
    public String getUserDetails(Model model) {
        model.addAttribute("allPersonList", personService.getAllPersonsByClassType(Person.class));
        return "persons";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public <T extends Person> T getUsersByID(@PathVariable Long id){
        T loaded = personService.getPersonById(id);
        if (loaded==null)
            throw new DataNotFoundException("User not found on ID: "+id);
        return loaded;
    }

    // addition
    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new AddUpdatePerson());
        return "newPerson";
    }

    @PostMapping("/add")
    public String savePerson(@ModelAttribute("person") AddUpdatePerson AddUpdatePerson){
        personService.addUpdatePerson(AddUpdatePerson);
        return "redirect:/api/v2/persons/";
    }

    // deletion
    @PostMapping(value = "/{id}") // This method should handle POST requests
    @ResponseBody
    public RedirectView handleDeleteUserPost(@PathVariable Long id) {
        // Logic to handle the simulated DELETE request
        deletePerson(id);// Call the delete method from here
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/v2/persons/");
        return redirectView;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Long deletePerson(@PathVariable Long id){
        Long idOfDelPerson = personService.deletePersonById(id);
        if (idOfDelPerson==-1)
            throw new DataNotFoundException("Person not found with id: "+id);
        return idOfDelPerson;
    }

}
