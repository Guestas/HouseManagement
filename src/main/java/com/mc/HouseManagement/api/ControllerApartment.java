package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.service.ApartmentService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v2/apartments")
public class ControllerApartment {
    private final ApartmentService apartmentService;

    @Autowired
    public ControllerApartment(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller apartment started!");
    }

    @GetMapping("/")
    public String getAllApartments(Model model){
        model.addAttribute("allApartmentList", apartmentService.loadAllApartments());
        return "apartments";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Apartment getApartmentByID(@PathVariable Long id){
        Apartment loaded = apartmentService.getApartmentById(id);
        if (loaded==null)
            throw new DataNotFoundException("Apartment not found on ID: "+id);
        return loaded;
    }

}
