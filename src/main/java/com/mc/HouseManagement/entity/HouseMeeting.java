package com.mc.HouseManagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="HouseMeeting")
public class HouseMeeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="date")
    private String date;
    @Column(name="name")
    private String name;
    @Column(name="topics")
    private List<String> topics;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "apartment_house_meeting",
            joinColumns = @JoinColumn(name = "house_meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_id"))
    private List<Apartment> apartments;

    public HouseMeeting() {
    }

    public HouseMeeting(String date, String name, List<String> topics, List<Apartment> apartments) {
        this.date = date;
        this.name = name;
        this.topics = topics;
        this.apartments = apartments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public static HouseMeeting createHouseMeeting(String date, String name, List<String> topics, List<Apartment> apartments){
        return new HouseMeeting(date,name, topics, apartments);
    }
}
