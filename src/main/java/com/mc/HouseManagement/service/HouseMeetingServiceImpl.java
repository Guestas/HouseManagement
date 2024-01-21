package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.HouseMeetingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseMeetingServiceImpl implements HouseMeetingService {

    private final HouseMeetingDAO houseMeetingDAO;
    private final ApartmentDAO apartmentDAO;

    @Autowired
    public HouseMeetingServiceImpl(HouseMeetingDAO houseMeetingDAO, ApartmentDAO apartmentDAO){
        this.houseMeetingDAO = houseMeetingDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public Long addUpdateHouseMeeting(HouseMeeting houseMeeting) {
        return houseMeetingDAO.addUpdateHouseMeeting(houseMeeting);
    }

    @Override
    public HouseMeeting getHouseMeetingById(Long id) {
        return houseMeetingDAO.getHouseMeetingById(id);
    }

    @Override
    public List<HouseMeeting> loadAllHouseMeetings() {
        return houseMeetingDAO.loadAllHouseMeetings();
    }

    @Override
    public Long deleteHouseMeeting(Long id) {
        return houseMeetingDAO.deleteHouseMeeting(id);
    }

    @Override
    public Long addApartmentToHouseMeeting(Long houseMeetingID, Long apartmentId) {
        HouseMeeting houseMeeting = houseMeetingDAO.getHouseMeetingById(houseMeetingID);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);
        if (houseMeeting==null)
            return -1L;
        else if (apartment == null)
            return -2L;
        else
            houseMeeting.addApartment(apartment);
        return houseMeetingDAO.addUpdateHouseMeeting(houseMeeting);
    }

    @Override
    public Long delApartmentFromHouseMeeting(Long houseMeetingID, Long apartmentId) {
        HouseMeeting houseMeeting = houseMeetingDAO.getHouseMeetingById(houseMeetingID);
        Apartment apartment = apartmentDAO.getApartmentById(apartmentId);
        if (houseMeeting==null)
            return -1L;
        else if (apartment == null)
            return -2L;
        else
            houseMeeting.delApartment(apartment);
        return houseMeetingDAO.addUpdateHouseMeeting(houseMeeting);
    }

}
