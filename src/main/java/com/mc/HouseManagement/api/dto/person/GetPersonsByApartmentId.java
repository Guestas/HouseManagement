package com.mc.HouseManagement.api.dto.person;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.SoldMovedOut;
import com.mc.HouseManagement.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GetPersonsByApartmentId {
    @NotNull(message = "idPerson cannot be null")
    @Min(value = 1, message = "idApartment cannot be les than 1")
    private Long idApartment;

    @NotNull(message = "Type of person must be [User, Owner, SoldMovedOut] name cannot be null.")
    @Pattern(regexp = "^(Owner|User|SoldMovedOut)$", message = "typeOfUser must be 'Owner', 'User' or SoldMovedOut")
    private String typeOfUser;

    public GetPersonsByApartmentId() {
    }

    public GetPersonsByApartmentId(Long idApartment, String typeOfUser) {
        this.idApartment = idApartment;
        this.typeOfUser = typeOfUser;
    }

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public Class<? extends Person> getPersonType() {
        if ("User".equals(typeOfUser)) {
            return User.class;
        } else if ("Owner".equals(typeOfUser)) {
            return Owner.class;
        } else if ("SoldMovedOut".equals(typeOfUser)) {
            return SoldMovedOut.class;
        } else {
            return null;
        }
    }
}
