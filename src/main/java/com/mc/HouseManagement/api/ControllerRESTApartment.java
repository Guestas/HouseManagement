package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.dto.apartment.AddApartments;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.service.ApartmentService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartments")
public class ControllerRESTApartment {
    private final ApartmentService apartmentService;

    public ControllerRESTApartment(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    // Created request's by type POST, PUT, GET and DEL this will communicate with Web browser or Postman ...
    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        System.out.println("Rest controller apartment started!");
    }

    @GetMapping("/")
    public List<Apartment> getAllApartments(){
        return apartmentService.loadAllApartments();
    }

    @GetMapping("/{id}")
    public Apartment getApartmentById(@PathVariable Long id){
        return apartmentService.getApartmentById(id);
    }

    @PostMapping("/")
    public int addApartments(@RequestBody AddApartments apartments){
        apartments.getApartments().forEach(apartmentService::addUpdateApartment);
        return apartments.getApartments().size();
    }

}
