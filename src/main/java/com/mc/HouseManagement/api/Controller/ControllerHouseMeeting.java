package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.service.HouseMeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;

@Controller
@RequestMapping("/api/v2/houseMeetings")
public class ControllerHouseMeeting {
    private final HouseMeetingService houseMeetingService;

    public ControllerHouseMeeting(HouseMeetingService houseMeetingService) {
        this.houseMeetingService = houseMeetingService;
    }

    @GetMapping("/")
    public String getAllMeetings(Model model){
        model.addAttribute("meetings", houseMeetingService.getAllHouseMeetings());
        return "houseMeetings";
    }

    // addition
    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("addUpdateHouseMeeting", new AddUpdateHouseMeeting());
        return "houseMeetingAdd";
    }

    @PostMapping("/add")
    public String savePerson(@ModelAttribute("person") AddUpdateHouseMeeting addUpdateHouseMeeting){
        Long personId = houseMeetingService.addUpdateHouseMeeting(addUpdateHouseMeeting);
        addUpdateHouseMeeting.getApartmentNumber().forEach(a->houseMeetingService.addApartmentToHouseMeeting(personId, Long.parseLong(a)));
        return "redirect:/api/v2/houseMeetings/";
    }

    // updation
    @GetMapping("/update/{houseMeetingId}")
    public ModelAndView updatePerson(@PathVariable(name = "houseMeetingId") Long houseMeetingId, Model model){
        ModelAndView updateView = new ModelAndView("houseMeetingUpdate");
        HouseMeeting houseMeeting = houseMeetingService.getHouseMeetingById(houseMeetingId);
        AddUpdateHouseMeeting addUpdateHouseMeeting = AddUpdateHouseMeeting.createAddUpsateHouseMeeting(houseMeeting);
        addUpdateHouseMeeting.setApartmentNumber(houseMeeting.getApartments().stream().map(apartment -> apartment.getId().toString()).toList());
        updateView.addObject("houseMeeting", addUpdateHouseMeeting);
        model.addAttribute("apartmentsOnHouseMeeting", houseMeeting.getApartments().stream().sorted(Comparator.comparing(Apartment::getId)));
        return updateView;
    }

    @PostMapping("/update/")
    public String handleUpdateHouseMeetingPost(@ModelAttribute AddUpdateHouseMeeting addUpdateHouseMeeting) {
        updateHouseMeeting(addUpdateHouseMeeting);
        return "redirect:/api/v2/houseMeetings/";
    }

    @PutMapping("/update/")
    @ResponseBody
    public void updateHouseMeeting(@ModelAttribute AddUpdateHouseMeeting addUpdateHouseMeeting) {
        Long idOfUpdatedPerson = houseMeetingService.addUpdateHouseMeeting(addUpdateHouseMeeting);
        addUpdateHouseMeeting.getApartmentNumber().forEach(a->houseMeetingService.addApartmentToHouseMeeting(idOfUpdatedPerson, Long.parseLong(a)));
        if (idOfUpdatedPerson == -1) {
            throw new DataNotFoundException("Person not found with id: " + addUpdateHouseMeeting.getId());
        }
    }


    // deletion
    @PostMapping(value = "/update/{houseMeetingId}") // This method should handle POST requests
    @ResponseBody
    public RedirectView handleDeleteUserPost(@PathVariable(name = "houseMeetingId") Long houseMeetingId) {
        // Logic to handle the simulated DELETE request
        deleteHouseMeeting(houseMeetingId);// Call the delete method from here
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/v2/houseMeetings/");
        return redirectView;
    }

    @DeleteMapping("/{houseMeetingId}")
    @ResponseBody
    public void deleteHouseMeeting(@PathVariable Long houseMeetingId){
        Long idOfDelHouseMeeting = houseMeetingService.deleteHouseMeetingById(houseMeetingId);
        if (idOfDelHouseMeeting==-1)
            throw new DataNotFoundException("Person not found with id: "+houseMeetingId);
    }

}
