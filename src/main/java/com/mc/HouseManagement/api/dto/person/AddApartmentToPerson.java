package com.mc.HouseManagement.api.dto.person;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddApartmentToPerson {
    @NotNull(message = "idApartment cannot be null")
    @Min(value = 1, message = "idPerson cannot be les than 1")
    private Long idPerson;
    @NotNull(message = "idPerson cannot be null")
    @Min(value = 1, message = "idApartment cannot be les than 1")
    private Long idApartment;

    public AddApartmentToPerson() {
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(Long idApartment) {
        this.idApartment = idApartment;
    }
}
