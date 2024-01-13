package com.mc.HouseManagement.api;

import com.mc.HouseManagement.service.OwnerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ControllerREST {

    private final OwnerService ownerService;

    @Autowired
    public ControllerREST(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostConstruct //caled once after start to download new data
    public void loadData(){

        //ownerService.addOwner();
        System.out.println("Rest controller started!");
    }
}