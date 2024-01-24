package com.mc.HouseManagement.api.dto.houseMeetings;

import java.util.List;

public class AddUpdateHouseMeetings {
    private List<AddUpdateHouseMeeting> houseMeetings;

    public AddUpdateHouseMeetings() {
    }

    public AddUpdateHouseMeetings(List<AddUpdateHouseMeeting> houseMeetings) {
        this.houseMeetings = houseMeetings;
    }

    public List<AddUpdateHouseMeeting> getHouseMeetings() {
        return houseMeetings;
    }

    public void setHouseMeetings(List<AddUpdateHouseMeeting> houseMeetings) {
        this.houseMeetings = houseMeetings;
    }
}
