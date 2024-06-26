package com.mc.HouseManagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** HouseMeeting is blueprint for table of HouseMeetings. **/
@Entity
@Table(name="House_Meeting")
public class HouseMeeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="date")
    private String date;
    @Column(name="name")
    private String name;
    @ElementCollection
    @CollectionTable(name = "house_meeting_topics", joinColumns = @JoinColumn(name = "house_meeting_id"))
    @Column(name = "topic")
    private List<String> topics;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
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

    /** Check if apartment is in list if it is not it will add this apartment. **/
    public void addApartment(Apartment apartment){
        if (apartments == null){
            apartments = new ArrayList<>();
            apartments.add(apartment);
        } else if (!apartments.contains(apartment)) {
            apartments.add(apartment);
        }
        else
            System.out.println("Already in list");
    }
    /** Check if apartment is in list if it is it will delete this apartment. **/
    public void delApartment(Apartment apartment){
        if (apartments == null){
            apartments = new ArrayList<>();
            apartments.add(apartment);
        } else if (apartments.contains(apartment)) {
            apartments.remove(apartment);
        }
        else
            System.out.println("Not in list");
    }

    /** Returns string with values from apartment. **/
    @Override
    public String toString() {
        return "HouseMeeting{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", topics=" + topics +
                ", apartments=" + apartments +
                '}';
    }

    /** This function returns HouseMeeting with values which are described above. **/
    public static HouseMeeting createHouseMeeting(String date, String name, List<String> topics, List<Apartment> apartments){
        return new HouseMeeting(date,name, topics, apartments);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HouseMeeting houseMeeting = (HouseMeeting) obj;
        return Objects.equals(this.getId(), houseMeeting.getId())&&Objects.equals(this.getDate(), houseMeeting.getDate());
    }
}
