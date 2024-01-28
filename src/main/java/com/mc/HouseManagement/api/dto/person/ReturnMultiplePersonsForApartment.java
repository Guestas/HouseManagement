package com.mc.HouseManagement.api.dto.person;

import com.mc.HouseManagement.entity.Person;

import java.util.Objects;

public class ReturnMultiplePersonsForApartment {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long phone;

    public ReturnMultiplePersonsForApartment() {
    }

    public ReturnMultiplePersonsForApartment(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.phone = person.getPhone();
    }

    public ReturnMultiplePersonsForApartment(String firstName, String lastName, String email, Long phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ReturnMultiplePersonsForApartment{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReturnMultiplePersonsForApartment person = (ReturnMultiplePersonsForApartment) obj;
        return Objects.equals(this.getPhone(), person.getPhone()) &&
                Objects.equals(this.getFirstName(), person.getFirstName()) &&
                Objects.equals(this.getLastName(), person.getLastName());
    }
}
