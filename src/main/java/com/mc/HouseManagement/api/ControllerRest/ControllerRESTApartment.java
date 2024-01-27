package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.api.Controller.ControllerApartment;
import com.mc.HouseManagement.api.dto.apartment.AddApartments;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.service.ApartmentService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
public class ControllerRESTApartment {
    private final ApartmentService apartmentService;

    @Autowired
    public ControllerRESTApartment(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    // Created request's by type POST, PUT, GET and DEL this will communicate with Web browser or Postman ...
    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller apartment started!");
    }

    @GetMapping("/")
    public List<Apartment> getAllApartments(){
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{apartmentId}")
    public Apartment getApartmentById(@PathVariable Long apartmentId){
        return apartmentService.getApartmentById(apartmentId);
    }

    @PostMapping("/")
    public int addApartments(@RequestBody AddApartments addApartments){
        addApartments.getApartments().forEach(apartmentService::addUpdateApartment);
        return addApartments.getApartments().size();
    }

}
