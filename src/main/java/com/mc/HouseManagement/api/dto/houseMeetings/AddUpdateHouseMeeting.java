package com.mc.HouseManagement.api.dto.houseMeetings;

import com.mc.HouseManagement.entity.HouseMeeting;

import java.util.List;
import java.util.Objects;

public class AddUpdateHouseMeeting {
    private Long id;
    private String date;
    private String name;
    private List<String> topics;
    private List<String> apartmentNumber;

    public AddUpdateHouseMeeting() {

    }

    public AddUpdateHouseMeeting(HouseMeeting houseMeeting) {
        this.id = houseMeeting.getId();
        this.date = houseMeeting.getDate();
        this.name = houseMeeting.getName();
        this.topics = houseMeeting.getTopics();
    }

    public AddUpdateHouseMeeting( String date, String name, List<String> topics) {
        this.date = date;
        this.name = name;
        this.topics = topics;
    }

    public AddUpdateHouseMeeting(Long id, String date, String name, List<String> topics) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.topics = topics;
    }

    public List<String> getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(List<String> apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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

    public static AddUpdateHouseMeeting createAddUpsateHouseMeeting(HouseMeeting houseMeeting) {
        return new AddUpdateHouseMeeting(houseMeeting);
    }

    public static AddUpdateHouseMeeting createAddUpsateHouseMeeting(String date, String name, List<String> topics){
        return new AddUpdateHouseMeeting(date, name, topics);
    }
    public static AddUpdateHouseMeeting createAddUpsateHouseMeeting(Long id, String date, String name, List<String> topics){
        return new AddUpdateHouseMeeting(id, date, name, topics);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AddUpdateHouseMeeting addUpdateHouseMeeting = (AddUpdateHouseMeeting) obj;
        return Objects.equals(this.getId(), addUpdateHouseMeeting.getId()) &&
                Objects.equals(this.getDate(), addUpdateHouseMeeting.getDate());
    }
}
