package com.mc.HouseManagement.api.dto.person;

import java.util.List;

public class AddUpdatePersons {

    private List<AddUpdatePerson> addUpdatePersons;

    public AddUpdatePersons() {
    }

    public AddUpdatePersons(List<AddUpdatePerson> addUpdatePersons) {
        this.addUpdatePersons = addUpdatePersons;
    }

    public List<AddUpdatePerson> getPersons() {
        return addUpdatePersons;
    }

    public void setPersons(List<AddUpdatePerson> addUpdatePersons) {
        this.addUpdatePersons = addUpdatePersons;
    }

    @Override
    public String toString() {
        if (addUpdatePersons.isEmpty())
            return "Nothing in list";
        else
            return "AddUpdatePersons{" +
                    "addUpdatePersons=" + addUpdatePersons +
                    '}';
    }
}
