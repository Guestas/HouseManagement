package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
        model.addAttribute("allOwnersList", personService.getAllPersonsByType("Owner"));
        model.addAttribute("allUsersList", personService.getAllPersonsByType("User"));
        model.addAttribute("allSoldMovedOut", personService.getAllPersonsByType("SoldMovedOut"));
        return "persons";
    }

    // addition
    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new AddUpdatePerson());
        return "personAdd";
    }

    @PostMapping("/add")
    public String savePerson(@ModelAttribute("person") AddUpdatePerson addUpdatePerson){
        Long personId = personService.addUpdatePerson(addUpdatePerson);
        addUpdatePerson.getApartmentNumber().forEach(p->personService.addApartmentToPerson(personId, Long.parseLong(p)));
        return "redirect:/api/v2/persons/";
    }

    // updation
    @GetMapping("/update/{personId}")
    public ModelAndView updatePerson(@PathVariable(name = "personId") Long personId, Model model){
        ModelAndView updateView = new ModelAndView("personUpdate");
        Person person = personService.getPersonById(personId);
        AddUpdatePerson addUpdatePerson = AddUpdatePerson.creteAddUpdatePerson(person, person.getClass().getSimpleName());

        addUpdatePerson.setApartmentNumber(person.getApartments().stream().map(apartment -> apartment.getId().toString()).toList());
        updateView.addObject("person", addUpdatePerson);
        model.addAttribute("apartmentList", person.getApartments());
        return updateView;
    }

    @PostMapping("/update/")
    public String handleUpdatePersonPost(@ModelAttribute AddUpdatePerson addUpdatePerson) {
        updatePerson(addUpdatePerson);
        return "redirect:/api/v2/persons/";
    }

    @PutMapping("/update/")
    @ResponseBody
    public void updatePerson(@ModelAttribute AddUpdatePerson addUpdatePerson) {
        Long idOfUpdatedPerson = personService.addUpdatePerson(addUpdatePerson);
        addUpdatePerson.getApartmentNumber().forEach(p->personService.addApartmentToPerson(idOfUpdatedPerson, Long.parseLong(p)));
        if (idOfUpdatedPerson == -1) {
            throw new DataNotFoundException("Person not found with id: " + addUpdatePerson.getId());
        }
    }

    // deletion
    @PostMapping(value = "/{personId}") // This method should handle POST requests
    @ResponseBody
    public RedirectView handleDeleteUserPost(@PathVariable Long personId) {
        // Logic to handle the simulated DELETE request
        deletePerson(personId);// Call the delete method from here
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/v2/persons/");
        return redirectView;
    }

    @DeleteMapping("/{personId}")
    @ResponseBody
    public void deletePerson(@PathVariable Long personId){
        Long idOfDelPerson = personService.deletePersonById(personId);
        if (idOfDelPerson==-1)
            throw new DataNotFoundException("Person not found with id: "+personId);
    }

}
