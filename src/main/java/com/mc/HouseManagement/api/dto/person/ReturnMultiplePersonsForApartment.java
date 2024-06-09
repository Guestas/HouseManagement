package com.mc.HouseManagement.api.dto.person;

import com.mc.HouseManagement.entity.Person;

import java.util.Objects;

public class ReturnMultiplePersonsForApartment {
    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    private Long phone;

    public ReturnMultiplePersonsForApartment() {
    }

    public ReturnMultiplePersonsForApartment(Person person){
        this.id = person.getId();
        this.first_name = person.getFirstName();
        this.last_name = person.getLastName();
        this.email = person.getEmail();
        this.phone = person.getPhone();
    }

    public ReturnMultiplePersonsForApartment(String first_name, String last_name, String email, Long phone) {
        this.first_name = first_name;
        this.last_name = last_name;
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
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
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
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
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
