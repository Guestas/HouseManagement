package com.mc.HouseManagement.api;

import com.mc.HouseManagement.api.modifyedExceptions.DataNotFoundException;
import com.mc.HouseManagement.api.dto.houseMeetings.AddApartmentToHouseMeeting;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeetings;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.service.HouseMeetingService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/houseMeetings")
public class ControllerRESTHouseMeeting {

    HouseMeetingService houseMeetingService;

    @Autowired
    public ControllerRESTHouseMeeting(HouseMeetingService houseMeetingService) {
        this.houseMeetingService = houseMeetingService;
    }

    @PostConstruct //caled once after start to download new data or other functionality
    public void loadData(){
        //TODO finish logger
        Logger logger = LoggerFactory.getLogger(ControllerApartment.class);
        logger.debug("Rest controller apartment started!");
    }

    @GetMapping("/")
    public List<HouseMeeting> getAllHouseMeetings(){
        return houseMeetingService.loadAllHouseMeetings();
    }

    @GetMapping("/{id}")
    public HouseMeeting getHouseMeetingById(@PathVariable Long id){
        HouseMeeting houseMeeting = houseMeetingService.getHouseMeetingById(id);
        if (houseMeeting==null)
            throw new DataNotFoundException("House meeting not found on ID: "+id);
        return houseMeeting;
    }

    @PostMapping("/")
    public Long addHouseMeeting(@RequestBody AddUpdateHouseMeeting addNewHouseMeeting){
        System.out.println(addNewHouseMeeting.getHouseMeeting());
        return houseMeetingService.addUpdateHouseMeeting(addNewHouseMeeting.getHouseMeeting());
    }

    @PutMapping("/")
    public Long updateHouseMeeting(@RequestBody AddUpdateHouseMeeting addUpdateHouseMeeting){
        HouseMeeting houseMeetingToUp = houseMeetingService.getHouseMeetingById(addUpdateHouseMeeting.getId());
        if (houseMeetingToUp!=null)
            throw new DataNotFoundException("House meeting not found with id: " + addUpdateHouseMeeting.getId());
        return houseMeetingService.addUpdateHouseMeeting(houseMeetingToUp);
    }

    @DeleteMapping("/{id}")
    public Long delHouseMeeting(@PathVariable Long id){
        Long result = houseMeetingService.deleteHouseMeeting(id);
        if (result==-1)
            throw new DataNotFoundException("House meeting not found with id: " + id);
        return result;
    }

    @PostMapping("/multiple/")
    public int addHouseMeetings(@RequestBody AddUpdateHouseMeetings addNewHouseMeetings){
        addNewHouseMeetings.getHouseMeetings().forEach(houseMeetingService::addUpdateHouseMeeting);
        return addNewHouseMeetings.getHouseMeetings().size();
    }

    @PostMapping("/apartment/")
    public Long addApartmentToMeeting(@Valid @RequestBody AddApartmentToHouseMeeting addApartmentToHouseMeeting){
        Long idOfUpdatedMeeting = houseMeetingService.addApartmentToHouseMeeting(addApartmentToHouseMeeting.getIdHouseMeeting(),
                addApartmentToHouseMeeting.getIdApartment());
        if (idOfUpdatedMeeting==-1)
            throw new DataNotFoundException("Person not found with ID: " + addApartmentToHouseMeeting.getIdHouseMeeting());
        else if (idOfUpdatedMeeting==-2) {
            throw new DataNotFoundException("Apartment not found with ID: " + addApartmentToHouseMeeting.getIdApartment());
        }
        return idOfUpdatedMeeting;
    }

    @DeleteMapping("/apartment/")
    public Long delApartmentToMeeting(@Valid @RequestBody AddApartmentToHouseMeeting addApartmentToHouseMeeting){
        Long idOfUpdatedMeeting = houseMeetingService.delApartmentFromHouseMeeting(addApartmentToHouseMeeting.getIdHouseMeeting(),
                addApartmentToHouseMeeting.getIdApartment());
        if (idOfUpdatedMeeting==-1)
            throw new DataNotFoundException("Person not found with ID: " + addApartmentToHouseMeeting.getIdHouseMeeting());
        else if (idOfUpdatedMeeting==-2) {
            throw new DataNotFoundException("Apartment not found with ID: " + addApartmentToHouseMeeting.getIdApartment());
        }
        return idOfUpdatedMeeting;
    }
}
