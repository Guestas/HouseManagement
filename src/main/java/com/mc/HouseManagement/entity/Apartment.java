package com.mc.HouseManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

/** Apartment is blueprint for table of apartments. **/
@Entity
@Table(name="Apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="vote-value")
    @NotNull(message = "Vote value cannot be null.")
    @Min(value = 1, message = "Min vote value is 1.")
    private Integer voteValue;
    @Column(name="branch")
    @NotNull(message = "Branch antenna cannot be null.")
    @Min(value = 1, message = "Min vote value is 1.")
    private Integer branchAntenna;
    @Column(name="flor")
    @NotNull(message = "Flor cannot be null.")
    private Integer flor;
    @Column(name="addressN")
    @NotNull(message = "Branch antenna cannot be null.")
    @Min(value = 1, message = "Min address is 1.")
    private Integer address;
    @NotNull(message = "Street name cannot be null.")
    @Column(name="street")
    private String  street;

    @JsonBackReference //will prevent cycling!!
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "apartment_house_meeting",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "house_meeting_id"))
    private List<HouseMeeting> absolvedMeetings;

    @JsonBackReference //will prevent cycling!!
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "apartments_persons",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personsInApartment;

    public Apartment() {
    }

    public Apartment(Integer voteValue, Integer branchAntenna, Integer flor, Integer address, String street, List<HouseMeeting> absolvedMeetings, List<Person> personsInApartment) {
        this.voteValue = voteValue;
        this.branchAntenna = branchAntenna;
        this.flor = flor;
        this.address = address;
        this.street = street;
        this.absolvedMeetings = absolvedMeetings;
        this.personsInApartment = personsInApartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(Integer voteValue) {
        this.voteValue = voteValue;
    }

    public Integer getBranchAntenna() {
        return branchAntenna;
    }

    public void setBranchAntenna(Integer branchAntenna) {
        this.branchAntenna = branchAntenna;
    }

    public Integer getFlor() {
        return flor;
    }

    public void setFlor(Integer flor) {
        this.flor = flor;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<HouseMeeting> getAbsolvedMeetings() {
        return absolvedMeetings;
    }

    public void setAbsolvedMeetings(List<HouseMeeting> absolvedMeetings) {
        this.absolvedMeetings = absolvedMeetings;
    }

    public List<Person> getPersonsInApartment() {
        return personsInApartment;
    }

    public void setPersonsInApartment(List<Person> personsInApartment) {
        this.personsInApartment = personsInApartment;
    }
    /** This function compare Classes, Ids of objects if CLass and Id is different it returns False else True. **/
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Apartment apartment = (Apartment) obj;
        return Objects.equals(this.getId(), apartment.getId());
    }

    /** Returns string with values from apartment. **/
    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", voteValue=" + voteValue +
                ", branchAntenna=" + branchAntenna +
                ", flor=" + flor +
                ", address=" + address +
                ", street='" + street + '\'' +
                '}';
    }

    /** This function returns Apartment with values which are described above. **/
    public static Apartment createApartment(Integer voteValue, Integer branchAntenna, Integer flor, Integer address, String street, List<HouseMeeting> absolvedMeetings, List<Person> personsInApartment){
        return new Apartment(voteValue, branchAntenna, flor, address,street, absolvedMeetings, personsInApartment);
    }
}
