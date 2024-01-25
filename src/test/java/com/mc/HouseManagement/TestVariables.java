package com.mc.HouseManagement;

import com.mc.HouseManagement.api.dto.apartment.AddApartment;
import com.mc.HouseManagement.api.dto.apartment.AddApartments;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeeting;
import com.mc.HouseManagement.api.dto.houseMeetings.AddUpdateHouseMeetings;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPersons;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.HouseMeeting;
import com.mc.HouseManagement.entity.Person;

import java.util.Arrays;
import java.util.List;

public record TestVariables() {
    /* Person */

    /** Test data for a person **/
    public static final AddUpdateNewPerson ADD_UPDATE_NEW_PERSON1 = AddUpdateNewPerson
            .creteAddUpdatePerson(1L, "Joe", "Black",
                    "joe@black.com", 123456789L, "Owner");

    /** Extracting the person from ADD_UPDATE_NEW_PERSON1 for easier reference **/
    public static final Person PERSON = ADD_UPDATE_NEW_PERSON1.getPersonWitType();

    /** Another test data for a person**/
    public static final AddUpdateNewPerson ADD_UPDATE_NEW_PERSON2 = AddUpdateNewPerson
            .creteAddUpdatePerson(2L, "Nina", "Black",
                    "nina@black.com", 987654321L, "Owner");

    /** Lists of test addPersons**/
    public static final List<AddUpdateNewPerson> ADD_UPDATE_NEW_PERSON_LIST = Arrays.asList(
            ADD_UPDATE_NEW_PERSON1,
            ADD_UPDATE_NEW_PERSON2
    );
    /** Lists of test persons**/
    public static final List<Person> PERSON_LIST = Arrays.asList(
            ADD_UPDATE_NEW_PERSON1.getPersonWitType(),
            ADD_UPDATE_NEW_PERSON2.getPersonWitType()
    );

    /** Test data for a collection of persons**/
    public static final AddUpdateNewPersons ADD_UPDATE_NEW_PERSONS = new AddUpdateNewPersons(ADD_UPDATE_NEW_PERSON_LIST);

    /* House Meeting */
    /** Test data for a house meeting**/
    public static final List<String> topics1 = Arrays.asList("Topic 1", "Topic 2", "Topic 3");
    public static final AddUpdateHouseMeeting ADD_UPDATE_HOUSE_MEETING1 = AddUpdateHouseMeeting
            .createAddUpsateHouseMeeting(1L, "20-5-1998", "Early meeting1", topics1);

    /** Extracting the house meeting from ADD_UPDATE_HOUSE_MEETING1 for easier reference**/
    public static final HouseMeeting HOUSE_MEETING = ADD_UPDATE_HOUSE_MEETING1.getHouseMeeting();

    /** Another test data for a house meeting**/
    public static final AddUpdateHouseMeeting ADD_UPDATE_HOUSE_MEETING2 = AddUpdateHouseMeeting
            .createAddUpsateHouseMeeting(2L, "20-5-1999", "Early meeting2", topics1);

    /** Lists of test AddUpdateHouseMeetings**/
    public static final List<AddUpdateHouseMeeting> ADD_UPDATE_HOUSE_MEETING_LIST = Arrays.asList(
            ADD_UPDATE_HOUSE_MEETING1,
            ADD_UPDATE_HOUSE_MEETING2
    );

    /** Lists of test HouseMeetings**/
    public static final List<HouseMeeting> HOUSE_MEETING_LIST = Arrays.asList(
            ADD_UPDATE_HOUSE_MEETING1.getHouseMeeting(),
            ADD_UPDATE_HOUSE_MEETING2.getHouseMeeting()
    );
    /** Test data for a collection of house meetings**/
    public static final AddUpdateHouseMeetings ADD_UPDATE_HOUSE_MEETINGS = new AddUpdateHouseMeetings(ADD_UPDATE_HOUSE_MEETING_LIST);

    /* Apartment */
    /** Test data for an apartment**/
    public static final AddApartment ADD_APARTMENT1 = AddApartment
            .createAddApartment(1L, 5, 4, 5,
                    2553, "street1");
    /** Extracting the apartment from ADD_APARTMENT1 for easier reference**/
    public static final Apartment APARTMENT = ADD_APARTMENT1.getApartment();

    /** Another test data for an apartment**/
    public static final AddApartment ADD_APARTMENT2 = AddApartment
            .createAddApartment(2L, 8, 5, 2,
                    2585, "street2");

    /** Lists of test addApartments**/
    public static final List<AddApartment> ADD_APARTMENT_LIST = Arrays.asList(
            ADD_APARTMENT1,
            ADD_APARTMENT2
    );

    /** Lists of test apartments**/
    public static final List<Apartment> APARTMENT_LIST = Arrays.asList(
            ADD_APARTMENT1.getApartment(),
            ADD_APARTMENT2.getApartment()
    );

    /** Test data for a collection of apartments**/
    public static final AddApartments ADD_APARTMENTS = new AddApartments(ADD_APARTMENT_LIST);

}
