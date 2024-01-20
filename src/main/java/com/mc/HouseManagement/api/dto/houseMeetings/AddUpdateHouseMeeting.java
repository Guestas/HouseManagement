package com.mc.HouseManagement.api.dto.houseMeetings;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;

public class AddUpdateHouseMeeting {
    private Long id;
    private String date;
    private String name;
    private List<String> topics;

    public AddUpdateHouseMeeting() {

    }

    public AddUpdateHouseMeeting(long id, String date, String name, List<String> topics) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.topics = topics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public HouseMeeting getHouseMeeting(){
        if (id != null){
            HouseMeeting houseMeeting = new HouseMeeting(date, name, topics, null);
            houseMeeting.setId(id);
            return houseMeeting;
        }
        else
            return new HouseMeeting(date, name, topics, null);

    }
}
