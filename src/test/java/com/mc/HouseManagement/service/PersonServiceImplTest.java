package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.api.dto.person.AddUpdateNewPerson;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        personService.addUpdatePerson(testPerson);

        // Then: Verify the output or expected result
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personDAO).addUpdatePerson(personArgumentCaptor.capture());
        Person capturedPerson = personArgumentCaptor.getValue();
        assertThat(capturedPerson).isEqualTo(testPerson.getPersonWitType());
    }

    @Test
    void getPersonById() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.getPersonById(1L, Owner.class);

        // Then: Verify the output or expected result
        verify(personDAO).getPersonById(1L, Owner.class);
    }

    @Test
    void loadAllPersons() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.loadAllPersons(Owner.class);

        // Then: Verify the output or expected result
        verify(personDAO).loadAllPersons(Owner.class);
    }

    @Test
    void deleteById() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        personService.deleteById(1L);

        // Then: Verify the output or expected result
        verify(personDAO).deleteById(1L);
    }

    @Test
    public void testLoadPersonByLastOrFirstNameAndType() {

        // Sample input data
        String oneOfNames = "John";
        Class<Person> personClass = Person.class;

        // Sample data to be returned by the mock
        AddUpdateNewPerson testPerson1 = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        AddUpdateNewPerson testPerson2 = AddUpdateNewPerson.creteAddUpdatePerson("a","b","e",
                123456L,"Owner");
        List<Person> expectedResults = Arrays.asList(testPerson1.getPersonWitType(), testPerson2.getPersonWitType());

        // Configuring the mock behavior
        when(personDAO.loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass)))
                .thenReturn(expectedResults);

        // Calling the method under test
        List<Person> actualResults = personService.loadPersonByLastOrFirstNameAndType(oneOfNames, personClass);

        // Verifying the mock interactions
        verify(personDAO).loadPersonByLastOrFirstNameAndType(eq(oneOfNames), eq(personClass));

        // Asserting the results
        assertEquals(expectedResults, actualResults);
    }

}