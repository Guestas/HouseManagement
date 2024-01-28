package com.mc.HouseManagement.api.Controller;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;
import com.mc.HouseManagement.service.ApartmentService;
import com.mc.HouseManagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v2/apartments")
public class ControllerApartment {
    private final ApartmentService apartmentService;
    private final PersonService personService;

    @Autowired
    public ControllerApartment(ApartmentService apartmentService, PersonService personService) {
        this.apartmentService = apartmentService;
        this.personService = personService;
    }

    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller apartment started!");
    }

    @GetMapping("/")
    public String getAllApartments(Model model){
        model.addAttribute("allApartmentList", apartmentService.getAllApartments());
        return "apartments";
    }

    @GetMapping("/{apartmentId}")
    public String getApartmentByID(@PathVariable(name = "apartmentId") Long apartmentId, Model model){
        model.addAttribute("apartment", apartmentService.getApartmentById(apartmentId));
        model.addAttribute("ownersList", personService.getPersonsByApartmentsIdAndType(apartmentId, Owner.class));
        model.addAttribute("usersList", personService.getPersonsByApartmentsIdAndType(apartmentId, User.class));
        model.addAttribute("soldMovedOutsList", personService.getPersonsByApartmentsIdAndType(apartmentId, SoldMovedOut.class));

        return "apartment";
    }

}
