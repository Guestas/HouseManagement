package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.api.dto.person.ReturnMultiplePersonsForApartment;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock private PersonDAO personDAO;
    @Mock private ApartmentDAO apartmentDAO;

    private PersonService personService;


    @BeforeEach
    void setUp(){
        personService = new PersonServiceImpl(personDAO, apartmentDAO);
    }

    @Test
    @DisplayName("Can add person")
    void canAddPerson() {
        // Given: Setup object or precondition
        AddUpdatePerson testPerson = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");

        // When: Action or behavior that we are going to test
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.addUpdatePerson(testPerson);
        verify(personDAO).addUpdatePerson(eq(testPerson.getPersonWitType()));
        assertEquals(1L, result);

    }

    @Test
    @DisplayName("Can get person by id and type")
    void tryGetPersonByIdAndType() {
        // Given: Setup object or precondition
        Long personID = 1L;
        AddUpdatePerson testPerson = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByIdAndType(any(), any())).thenReturn(testPerson.getPersonWitType());

        // Then: Verify the output or expected result
        Person returned = personService.getPersonByIdAndType(personID, "Owner");
        verify(personDAO).getPersonByIdAndType(1L, "Owner");
        assertEquals(testPerson.getPersonWitType(), returned);
    }

    @Test
    @DisplayName("Can get all persons type")
    void getAllPersonsOwner() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.getAllPersonsByType("Owner");

        // Then: Verify the output or expected result
        verify(personDAO).getAllPersonsByType("Owner");
    }

    @Test
    @DisplayName("Can get all persons type Person and Owner")
    void getAllPersonsPersonUniversalReturn() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.getAllPersonsByType("Person");

        // Then: Verify the output or expected result
        verify(personDAO).getAllPersonsByType("User");
        verify(personDAO).getAllPersonsByType("Owner");
    }

    @Test
    @DisplayName("Can delete person id")
    void deletePersonById() {
        // Given: Setup object or precondition
        Long personId = 1L;

        // When: Action or behavior that we are going to test
        when(personDAO.deletePersonById(eq(personId))).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.deletePersonById(personId);
        verify(personDAO).deletePersonById(eq(personId));
        assertEquals(personId, result);
    }

    @Test
    @DisplayName("Can get persons by last or first name")
    public void testgetPersonByLastOrFirstNameAndType() {
        // Given: Setup object or precondition
        String oneOfNames = "John";
        String personType = "Person";

        // Sample data to be returned by the mock
        AddUpdatePerson testPerson1 = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        AddUpdatePerson testPerson2 = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        List<Person> expectedResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personType)))
                .thenReturn(expectedResults);

        // Then: Verify the output or expected result
        List<Person> actualResults = personService.getPersonByLastOrFirstNameAndType(oneOfNames, personType);

        verify(personDAO).getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personType));
        assertEquals(expectedResults, actualResults);
    }

    @Test
    @DisplayName("Can get persons by last or first name and type")
    void testGetPersonByLastOrFirstName(){
        // Given: Setup object or precondition
        String oneOfNames = "John";
        AddUpdatePerson testPerson1 = AddUpdatePerson.creteAddUpdatePerson("John", "Doe", "e", 123456L, "User");
        AddUpdatePerson testPerson2 = AddUpdatePerson.creteAddUpdatePerson("Jane", "Doe", "e", 123456L, "User");
        AddUpdatePerson testPerson3 = AddUpdatePerson.creteAddUpdatePerson("John", "Smith", "e", 123456L, "Owner");
        AddUpdatePerson testPerson4 = AddUpdatePerson.creteAddUpdatePerson("Jane", "Smith", "e", 123456L, "Owner");

        List<Person> userPersonResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());
        List<Person> ownerPersonResults = Arrays.asList(testPerson3.getPersonWitType(), testPerson4.getPersonWitType());

        // Set up the mock behavior with the correct arguments
        when(personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, Person.USER)).thenReturn(userPersonResults);
        when(personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, Person.OWNER)).thenReturn(ownerPersonResults);

        // When: Action or behavior that we are going to test
        List<Person> result = personService.getPersonByLastOrFirstName(oneOfNames);

        // Then: Verify the output or expected result
        assertEquals(4, result.size()); // Assuming that all instances are returned
        assertEquals(testPerson1.getPersonWitType(), result.get(0));
        assertEquals(testPerson2.getPersonWitType(), result.get(1));
        assertEquals(testPerson3.getPersonWitType(), result.get(2));
        assertEquals(testPerson4.getPersonWitType(), result.get(3));

        // Given: Setup object or precondition
        /*String oneOfNames = "John";
        AddUpdatePerson testPerson1 = AddUpdatePerson.creteAddUpdatePerson("John", "Doe","e",
                123456L,"User");
        AddUpdatePerson testPerson2 = AddUpdatePerson.creteAddUpdatePerson("John", "Smith","e",
                123456L,"Owner");

        List<Person> personResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByLastOrFirstNameAndType(oneOfNames, "Person")).thenReturn(personResults);
        // Then: Verify the output or expected result
        List<Person> result = personService.getPersonByLastOrFirstName(oneOfNames);

        assertEquals(2, result.size()); // Assuming that all instances are returned
        assertEquals(testPerson1.getPersonWitType(), result.get(0));
        assertEquals(testPerson2.getPersonWitType(), result.get(1));*/

    }

    @Test
    @DisplayName("Can connect persons with apartment")
    void testAddApartmentToPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        Person testPerson = (Person) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                 "john@doe.com",126555111L, "Person").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testPerson);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(testApartment);
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.addApartmentToPerson(personId, apartmentId);
        verify(personDAO).getPersonById(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verify(personDAO).addUpdatePerson(any());

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Can connect persons with apartment. Person not found.")
    void testAddApartmentToPersonPersonNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        Person testPerson = (Person) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "Person").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = personService.addApartmentToPerson(personId, apartmentId);

        verify(personDAO).getPersonById(eq(personId));

        assertEquals(-1L, result);
    }

    @Test
    @DisplayName("Can connect persons with apartment. Apartment not found.")
    void testAddApartmentToPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        Person testPerson = (Person) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "Person").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testPerson);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(null);


        // Then: Verify the output or expected result

        Long result = personService.addApartmentToPerson(personId, apartmentId);

        verify(personDAO).getPersonById(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verifyNoMoreInteractions(personDAO); // Make sure personDAO methods are not called


        assertEquals(-2L, result); // Apartment not found
    }

    @Test
    @DisplayName("Can get person by id.")
    void testLoadPersonByID(){
        // Given: Setup object or precondition
        Long personId = 1L;
        Person testPerson = AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "Person").getPersonWitType();


        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testPerson);

        // Then: Verify the output or expected result
        Person result = personService.getPersonById(personId);

        verify(personDAO).getPersonById(eq(personId));
        assertEquals(testPerson, result);
    }

    @Test
    @DisplayName("Can delete apartment from person.")
    void testDelApartmentFromPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        Person testPerson = (Person) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "Person").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testPerson);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(testApartment);
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.delApartmentFromPerson(personId, apartmentId);
        verify(personDAO).getPersonById(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verify(personDAO).addUpdatePerson(any());

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Can delete apartment from person. Person not found.")
    void testDelApartmentFromPersonPersonNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = personService.delApartmentFromPerson(personId, apartmentId);

        verify(personDAO).getPersonById(eq(personId));

        assertEquals(-1L, result);
    }

    @Test
    @DisplayName("Can delete apartment from person. Apartment not found.")
    void testDelApartmentFromPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        Person testPerson = (Person) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "Person").getPersonWitType();

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testPerson);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(null);


        // Then: Verify the output or expected result

        Long result = personService.delApartmentFromPerson(personId, apartmentId);

        verify(personDAO).getPersonById(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verifyNoMoreInteractions(personDAO); // Make sure personDAO methods are not called


        assertEquals(-2L, result); // Apartment not found
    }

    @Test
    @DisplayName("Test get Persons by apartment id")
    void testGetPersonsByApartmentId(){
        // Given: Setup object or precondition
        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        Person owner = new Person("Anne","Jara","anne@jar.com",
                987654321L,null, "Owner");
        Person soldMovedOut = new Person("Diana","Jara","diana@anne.com",
                987654321L,null, "SoldMovedOut");
        Person Person = new Person("Kala","Anne","diana@anne.com",
                987654321L,null, "Person");
        Person.addApartment(testApartment);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonsByApartmentsIdAndType(1L, "Person")).thenReturn(Arrays.asList(Person));


        // Then: Verify the output or expected result
        List<ReturnMultiplePersonsForApartment> result = personService
                .getPersonsByApartmentsIdAndType(1L, "Person");
        assertNotNull(result);
        assertEquals(new ReturnMultiplePersonsForApartment(Person), result.get(0));

    }

}