package com.mc.HouseManagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private Integer phone;

    @Column(name="lastUpdate")
    private LocalDate lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name="apartments_persons",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_id"))
    private List<Apartment> apartments;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, Integer phone, List<Apartment> apartments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.apartments = apartments;
        this.lastUpdate = LocalDate.now();
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", apartments=" + apartments +
                '}';
    }
}
