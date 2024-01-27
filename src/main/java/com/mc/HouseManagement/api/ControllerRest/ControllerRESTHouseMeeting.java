package com.mc.HouseManagement.api.ControllerRest;

import com.mc.HouseManagement.api.Controller.ControllerApartment;
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
        return houseMeetingService.getAllHouseMeetings();
    }

    @GetMapping("/{houseMeetingId}")
    public HouseMeeting getHouseMeetingById(@PathVariable Long houseMeetingId){
        HouseMeeting houseMeeting = houseMeetingService.getHouseMeetingById(houseMeetingId);
        if (houseMeeting==null)
            throw new DataNotFoundException("House meeting not found on ID: "+houseMeetingId);
        return houseMeeting;
    }

    @PostMapping("/")
    public Long addHouseMeeting(@RequestBody AddUpdateHouseMeeting addNewHouseMeeting){
        return houseMeetingService.addUpdateHouseMeeting(addNewHouseMeeting);
    }

    @PutMapping("/")
    public Long updateHouseMeeting(@RequestBody AddUpdateHouseMeeting addUpdateHouseMeeting){
        HouseMeeting houseMeetingToUp = houseMeetingService.getHouseMeetingById(addUpdateHouseMeeting.getId());
        if (houseMeetingToUp==null)
            throw new DataNotFoundException("House meeting not found with id: " + addUpdateHouseMeeting.getId());
        return houseMeetingService.addUpdateHouseMeeting(addUpdateHouseMeeting);
    }

    @DeleteMapping("/{houseMeetingId}")
    public Long delHouseMeeting(@PathVariable Long houseMeetingId){
        Long result = houseMeetingService.deleteHouseMeetingById(houseMeetingId);
        if (result==-1)
            throw new DataNotFoundException("House meeting not found with id: " + houseMeetingId);
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
