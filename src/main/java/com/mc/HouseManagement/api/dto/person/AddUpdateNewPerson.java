package com.mc.HouseManagement.api.dto.person;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AddUpdateNewPerson {

    private Long id;

    @NotNull(message = "Fist name cannot be null.")
    @NotBlank(message = "Fist name cannot be empty.")
    private String firstName;

    @NotNull(message = "Last name cannot be null.")
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;

    private String email;

    @NotNull(message = "Fist name cannot be null.")
    @Min(value = 99_999_999, message = "Invalid phone number.")
    private Long phone;

    @NotNull(message = "Type of person must be [User, Owner, SoldMovedOut] name cannot be null.")
    @Pattern(regexp = "^(Owner|User|SoldMovedOut)$", message = "typeOfUser must be 'Owner', 'User' or SoldMovedOut")
    private String typeOfUser;

    public AddUpdateNewPerson() {
    }

    public AddUpdateNewPerson(String firstName, String lastName, String email, Long phone, String typeOfUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.typeOfUser = typeOfUser;
    }
    public AddUpdateNewPerson(Long id, String firstName, String lastName, String email, Long phone, String typeOfUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.typeOfUser = typeOfUser;
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

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Long getId() {
        return id;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    /**
     * Creates and returns a Person based on the typeOfUser.
     *
     * @return Person object based on the typeOfUser, or null if the type is unknown.
     */
    public Person getPersonWitType() {
        if ("User".equals(typeOfUser)) {
            User user = User.createUser(firstName, lastName, email, phone, null);
            if (id != null)
                user.setId(id);
            return user;
        } else if ("Owner".equals(typeOfUser)) {
            Owner owner = Owner.createOwner(firstName, lastName, email, phone, null);
            if (id != null)
                owner.setId(id);
            return owner;
        } else if ("SoldMovedOut".equals(typeOfUser)) {
            SoldMovedOut soldMovedOut = SoldMovedOut.createSoldMovedOut(firstName, lastName, email, phone, null);
            if (id != null)
                soldMovedOut.setId(id);
            return soldMovedOut;
        } else {
            return null;
        }
    }

    public static AddUpdateNewPerson creteAddUpdatePerson(String firstName, String lastName, String email, Long phone, String typeOfUser){
        return new AddUpdateNewPerson(firstName, lastName, email, phone, typeOfUser);
    }

    public static AddUpdateNewPerson creteAddUpdatePerson(Long id, String firstName, String lastName, String email, Long phone, String typeOfUser){
        return new AddUpdateNewPerson(id, firstName, lastName, email, phone, typeOfUser);
    }

    @Override
    public String toString() {
        return "AddUpdateNewPerson{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", typeOfUser='" + typeOfUser + '\'' +
                '}';
    }
}
