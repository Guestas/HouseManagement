package com.mc.HouseManagement.api.dto.person;

import java.util.List;

public class AddUpdateNewPersons {

    private List<AddUpdateNewPerson> persons;

    public AddUpdateNewPersons() {
    }

    public AddUpdateNewPersons(List<AddUpdateNewPerson> persons) {
        this.persons = persons;
    }

    public List<AddUpdateNewPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<AddUpdateNewPerson> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        if (persons.isEmpty())
            return "Nothing in list";
        else
            return "AddUpdateNewPersons{" +
                    "persons=" + persons +
                    '}';
    }
}
