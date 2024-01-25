package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.entity.User;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void canAddPerson() {
        // Given: Setup object or precondition
        AddUpdateNewPerson testPerson = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");

        // When: Action or behavior that we are going to test
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.addUpdatePerson(testPerson);
        verify(personDAO).addUpdatePerson(eq(testPerson.getPersonWitType()));
        assertEquals(1L, result);

    }

    @Test
    void tryGetPersonById() {
        // Given: Setup object or precondition
        Long personID = 1L;
        AddUpdateNewPerson testPerson = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(any(), any())).thenReturn(testPerson.getPersonWitType());

        // Then: Verify the output or expected result
        Person returned = personService.getPersonById(personID, Owner.class);
        verify(personDAO).getPersonById(1L, Owner.class);
        assertEquals(testPerson.getPersonWitType(), returned);
    }

    @Test
    void loadAllPersonsOwner() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.loadAllPersons(Owner.class);

        // Then: Verify the output or expected result
        verify(personDAO).loadAllPersons(Owner.class);
    }

    @Test
    void loadAllPersonsPersonUniversalReturn() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.loadAllPersons(Person.class);

        // Then: Verify the output or expected result
        verify(personDAO).loadAllPersons(User.class);
        verify(personDAO).loadAllPersons(Owner.class);
    }

    @Test
    void deleteById() {
        // Given: Setup object or precondition
        Long personId = 1L;

        // When: Action or behavior that we are going to test
        when(personDAO.deleteById(eq(personId))).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.deleteById(personId);
        verify(personDAO).deleteById(eq(personId));
        assertEquals(personId, result);
    }

    @Test
    public void testLoadPersonByLastOrFirstNameAndType() {
        // Given: Setup object or precondition
        String oneOfNames = "John";
        Class<Person> personClass = Person.class;

        // Sample data to be returned by the mock
        AddUpdateNewPerson testPerson1 = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        AddUpdateNewPerson testPerson2 = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        List<Person> expectedResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass)))
                .thenReturn(expectedResults);

        // Then: Verify the output or expected result
        List<Person> actualResults = personService.loadPersonByLastOrFirstNameAndType(oneOfNames, personClass);

        verify(personDAO).loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass));
        assertEquals(expectedResults, actualResults);
    }

    @Test
    <T extends Person> void testLoadPersonByLastOrFirstName(){
        // Given: Setup object or precondition
        String oneOfNames = "John";
        AddUpdateNewPerson testPerson1 = AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe","e",
                123456L,"User");
        AddUpdateNewPerson testPerson2 = AddUpdateNewPerson.creteAddUpdatePerson("John", "Smith","e",
                123456L,"User");

        List<User> userResults = Arrays.asList((User) testPerson1.getPersonWitType(), (User) testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(User.class)))
                .thenReturn(userResults);

        // Then: Verify the output or expected result
        List<Person> result = personService.loadPersonByLastOrFirstName(oneOfNames);
        verify(personDAO).loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(User.class));

        assertEquals(2, result.size()); // Assuming that all three instances are returned
        assertEquals(testPerson1.getPersonWitType(), result.get(0));
        assertEquals(testPerson2.getPersonWitType(), result.get(1));

    }

    @Test
    void testAddApartmentToPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                 "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(testUser);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(testApartment);
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.addApartmentToPerson(personId, apartmentId);
        verify(personDAO).loadPersonByID(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verify(personDAO).addUpdatePerson(any());

        assertEquals(1L, result);
    }

    @Test
    void testAddApartmentToPersonPersonNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = personService.addApartmentToPerson(personId, apartmentId);

        verify(personDAO).loadPersonByID(eq(personId));

        assertEquals(-1L, result);
    }

    @Test
    void testAddApartmentToPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(testUser);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(null);


        // Then: Verify the output or expected result

        Long result = personService.addApartmentToPerson(personId, apartmentId);

        verify(personDAO).loadPersonByID(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verifyNoMoreInteractions(personDAO); // Make sure personDAO methods are not called


        assertEquals(-2L, result); // Apartment not found
    }

    @Test
    void testLadPersonByID(){
        // Given: Setup object or precondition
        Long personId = 1L;
        Person testUser = AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();


        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(testUser);

        // Then: Verify the output or expected result
        User result = personService.loadPersonByID(personId);

        verify(personDAO).loadPersonByID(eq(personId));
        assertEquals(testUser, result);
    }

    @Test
    void testDelApartmentToPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(testUser);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(testApartment);
        when(personDAO.addUpdatePerson(any())).thenReturn(1L);

        // Then: Verify the output or expected result
        Long result = personService.delApartmentFromPerson(personId, apartmentId);
        verify(personDAO).loadPersonByID(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verify(personDAO).addUpdatePerson(any());

        assertEquals(1L, result);
    }

    @Test
    void testDelApartmentFromPersonPersonNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(null);

        // Then: Verify the output or expected result
        Long result = personService.delApartmentFromPerson(personId, apartmentId);

        verify(personDAO).loadPersonByID(eq(personId));

        assertEquals(-1L, result);
    }

    @Test
    void testDelApartmentFromPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdateNewPerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();

        // When: Action or behavior that we are going to test
        when(personDAO.loadPersonByID(eq(personId))).thenReturn(testUser);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(null);


        // Then: Verify the output or expected result

        Long result = personService.delApartmentFromPerson(personId, apartmentId);

        verify(personDAO).loadPersonByID(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verifyNoMoreInteractions(personDAO); // Make sure personDAO methods are not called


        assertEquals(-2L, result); // Apartment not found
    }

}