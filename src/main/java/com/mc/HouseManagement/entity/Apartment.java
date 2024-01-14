package com.mc.HouseManagement.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="Apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="vote-value")
    private Integer voteValue;
    @Column(name="branch")
    private Integer branchAntenna;
    @Column(name="flor")
    private Integer flor;
    @Column(name="addressN")
    private Integer address;
    @Column(name="street")
    private String  street;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "apartment_house_meeting",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "house_meeting_id"))
    private List<HouseMeeting> absolvedMeetings;

    public Apartment() {
    }

    public Apartment(Integer voteValue, Integer branchAntenna, Integer flor, Integer address, String street, List<HouseMeeting> absolvedMeetings) {
        this.voteValue = voteValue;
        this.branchAntenna = branchAntenna;
        this.flor = flor;
        this.address = address;
        this.street = street;
        this.absolvedMeetings = absolvedMeetings;
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

    public static Apartment createApartment(Integer voteValue, Integer branchAntenna, Integer flor, Integer address, String street, List<HouseMeeting> absolvedMeetings){
        return new Apartment(voteValue, branchAntenna, flor, address,street, absolvedMeetings);
    }
}
