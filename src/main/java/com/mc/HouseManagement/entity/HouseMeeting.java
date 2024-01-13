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
}
