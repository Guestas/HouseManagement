package com.mc.HouseManagement.api.dto.houseMeetings;

import java.util.List;

public class AddUpdateHouseMeetings {
    private List<AddUpdateHouseMeeting> addUpdateHouseMeetings;

    public AddUpdateHouseMeetings() {
    }

    public AddUpdateHouseMeetings(List<AddUpdateHouseMeeting> addUpdateHouseMeetings) {
        this.addUpdateHouseMeetings = addUpdateHouseMeetings;
    }

    public List<AddUpdateHouseMeeting> getHouseMeetings() {
        return addUpdateHouseMeetings;
    }

    public void setHouseMeetings(List<AddUpdateHouseMeeting> addUpdateHouseMeetings) {
        this.addUpdateHouseMeetings = addUpdateHouseMeetings;
    }
}
