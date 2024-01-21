package com.mc.HouseManagement.api.dto.apartment;

import com.mc.HouseManagement.entity.Apartment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddApartment {

    @NotNull(message = "VoteValue antenna cannot be null.")
    @Min(value = 1, message = "Min vote value is 1.")
    private Integer voteValue;

    @NotNull(message = "Branch antenna cannot be null.")
    @Min(value = 1, message = "Min vote value is 1.")
    private Integer branchAntenna;

    @NotNull(message = "Flor cannot be null.")
    private Integer flor;

    @NotNull(message = "Branch antenna cannot be null.")
    @Min(value = 1, message = "Min address is 1.")
    private Integer address;

    @NotNull(message = "Street name cannot be null.")
    @Column(name="street")
    private String  street;

    public AddApartment() {
    }

    public AddApartment(Integer voteValue, Integer branchAntenna, Integer flor, Integer address, String street) {
        this.voteValue = voteValue;
        this.branchAntenna = branchAntenna;
        this.flor = flor;
        this.address = address;
        this.street = street;
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

    public Apartment getApartment(){
        return new Apartment(voteValue, branchAntenna, flor, address, street, null, null);
    }
}
