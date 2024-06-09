package com.mc.HouseManagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person implements Comparable<Person>{
    public static final String OWNER = "Owner";
    public static final String USER = "User";
    public static final String SOLD_MOVED_OUT = "SoldMovedOut";
    public static final String PERSON = "Person";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private Long phone;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="apartments_persons",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_id"))
    private List<Apartment> apartments;

    @Column(name="type")
    private String type;

    public Person() {
    }

    public Person(String first_name, String last_name, String email, Long phone, List<Apartment> apartments, String type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.apartments = apartments;
        this.type = type;
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

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public static Person createPerson(String first_name, String last_name, String email, Long phone, List<Apartment> apartments, String type){
        return new Person(first_name, last_name, email, phone, apartments, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(this.getId(), person.getId()) &&
                Objects.equals(this.getFirstName(), person.getFirstName()) &&
                        Objects.equals(this.getLastName(), person.getLastName());
    }

    /**
     * This function returns string with values in Person.
     **/
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", apartments=" + apartments +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.id.compareTo(o.getId());
    }
}
