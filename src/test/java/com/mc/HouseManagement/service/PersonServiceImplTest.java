package com.mc.HouseManagement.service;

import com.mc.HouseManagement.api.dto.person.AddUpdatePerson;
import com.mc.HouseManagement.api.dto.person.ReturnMultiplePersonsForApartment;
import com.mc.HouseManagement.entity.*;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
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
    void trygetPersonByIdAndType() {
        // Given: Setup object or precondition
        Long personID = 1L;
        AddUpdatePerson testPerson = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByIdAndType(any(), any())).thenReturn(testPerson.getPersonWitType());

        // Then: Verify the output or expected result
        Person returned = personService.getPersonByIdAndType(personID, Owner.class);
        verify(personDAO).getPersonByIdAndType(1L, Owner.class);
        assertEquals(testPerson.getPersonWitType(), returned);
    }

    @Test
    void getAllPersonsOwner() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.getAllPersonsByClassType(Owner.class);

        // Then: Verify the output or expected result
        verify(personDAO).getAllPersonsByClassType(Owner.class);
    }

    @Test
    void getAllPersonsPersonUniversalReturn() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.getAllPersonsByClassType(Person.class);

        // Then: Verify the output or expected result
        verify(personDAO).getAllPersonsByClassType(User.class);
        verify(personDAO).getAllPersonsByClassType(Owner.class);
    }

    @Test
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
    public void testgetPersonByLastOrFirstNameAndType() {
        // Given: Setup object or precondition
        String oneOfNames = "John";
        Class<Person> personClass = Person.class;

        // Sample data to be returned by the mock
        AddUpdatePerson testPerson1 = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        AddUpdatePerson testPerson2 = AddUpdatePerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        List<Person> expectedResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass)))
                .thenReturn(expectedResults);

        // Then: Verify the output or expected result
        List<Person> actualResults = personService.getPersonByLastOrFirstNameAndType(oneOfNames, personClass);

        verify(personDAO).getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass));
        assertEquals(expectedResults, actualResults);
    }

    @Test
    <T extends Person> void testgetPersonByLastOrFirstName(){
        // Given: Setup object or precondition
        String oneOfNames = "John";
        AddUpdatePerson testPerson1 = AddUpdatePerson.creteAddUpdatePerson("John", "Doe","e",
                123456L,"User");
        AddUpdatePerson testPerson2 = AddUpdatePerson.creteAddUpdatePerson("John", "Smith","e",
                123456L,"User");

        List<User> userResults = Arrays.asList((User) testPerson1.getPersonWitType(), (User) testPerson2.getPersonWitType());

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(User.class)))
                .thenReturn(userResults);

        // Then: Verify the output or expected result
        List<Person> result = personService.getPersonByLastOrFirstName(oneOfNames);
        verify(personDAO).getPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(User.class));

        assertEquals(2, result.size()); // Assuming that all three instances are returned
        assertEquals(testPerson1.getPersonWitType(), result.get(0));
        assertEquals(testPerson2.getPersonWitType(), result.get(1));

    }

    @Test
    void testAddApartmentToPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                 "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testUser);
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
    void testAddApartmentToPersonPersonNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
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
    void testAddApartmentToPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testUser);
        when(apartmentDAO.getApartmentById(eq(apartmentId))).thenReturn(null);


        // Then: Verify the output or expected result

        Long result = personService.addApartmentToPerson(personId, apartmentId);

        verify(personDAO).getPersonById(eq(personId));
        verify(apartmentDAO).getApartmentById(eq(apartmentId));
        verifyNoMoreInteractions(personDAO); // Make sure personDAO methods are not called


        assertEquals(-2L, result); // Apartment not found
    }

    @Test
    void testLadPersonByID(){
        // Given: Setup object or precondition
        Long personId = 1L;
        Person testUser = AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();


        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testUser);

        // Then: Verify the output or expected result
        User result = personService.getPersonById(personId);

        verify(personDAO).getPersonById(eq(personId));
        assertEquals(testUser, result);
    }

    @Test
    void testDelApartmentToPerson() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();
        Apartment testApartment = Apartment.createApartment(50,5,4,6,
                "Lombart st.", null, null);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testUser);
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
    void testDelApartmentFromPersonApartmentNotFound() {
        // Given: Setup object or precondition
        Long personId = 1L;
        Long apartmentId = 101L;

        User testUser = (User) AddUpdatePerson.creteAddUpdatePerson("John", "Doe",
                "john@doe.com",126555111L, "User").getPersonWitType();

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonById(eq(personId))).thenReturn(testUser);
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

        Owner owner = new Owner("Anne","Jara","anne@jar.com",
                987654321L,null);
        SoldMovedOut soldMovedOut = new SoldMovedOut("Diana","Jara","diana@anne.com",
                987654321L,null);
        User user = new User("Kala","Anne","diana@anne.com",
                987654321L,null);
        user.addApartment(testApartment);

        // When: Action or behavior that we are going to test
        when(personDAO.getPersonsByApartmentsIdAndType(1L, User.class)).thenReturn(Arrays.asList(user));


        // Then: Verify the output or expected result
        List<ReturnMultiplePersonsForApartment> result = personService
                .getPersonsByApartmentsIdAndType(1L, User.class);
        assertNotNull(result);
        assertEquals(new ReturnMultiplePersonsForApartment(user), result.get(0));

    }

}