package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.repository.HouseMeetingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseMeetingServiceImpl implements HouseMeetingService {

    private final HouseMeetingDAO houseMeetingDAO;

    @Autowired
    public HouseMeetingServiceImpl(HouseMeetingDAO houseMeetingDAO){
        this.houseMeetingDAO = houseMeetingDAO;
    }

    @Override
    public Long addHouseMeeting(HouseMeeting houseMeeting) {
        return houseMeetingDAO.addHouseMeeting(houseMeeting);
    }

    @Override
    public HouseMeeting getHouseMeetingById(Long id) {
        return houseMeetingDAO.getHouseMeetingById(id);
    }

    @Override
    public List<HouseMeeting> loadAllHouseMeetings() {
        return houseMeetingDAO.loadAllHouseMeetings();
    }
}
