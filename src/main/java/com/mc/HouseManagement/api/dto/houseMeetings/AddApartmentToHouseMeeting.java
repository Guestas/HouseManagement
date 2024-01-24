package com.mc.HouseManagement.api.dto.houseMeetings;

public class AddApartmentToHouseMeeting {
    private Long idHouseMeeting;
    private Long idApartment;

    public AddApartmentToHouseMeeting() {
    }

    public AddApartmentToHouseMeeting(Long idHouseMeeting, Long idApartment) {
        this.idHouseMeeting = idHouseMeeting;
        this.idApartment = idApartment;
    }

    public Long getIdHouseMeeting() {
        return idHouseMeeting;
    }

    public void setIdHouseMeeting(Long idHouseMeeting) {
        this.idHouseMeeting = idHouseMeeting;
    }

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

}
