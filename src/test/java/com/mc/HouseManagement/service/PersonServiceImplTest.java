package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Owner;
import com.mc.HouseManagement.entity.Person;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

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
        Owner testOwner = Owner.createOwner("a","b","e",
                123456,null);

        // When: Action or behavior that we are going to test
        personService.addUpdatePerson(testOwner);

        // Then: Verify the output or expected result
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personDAO).addUpdatePerson(personArgumentCaptor.capture());
        Person capturedPerson = personArgumentCaptor.getValue();
        assertThat(capturedPerson).isEqualTo(testOwner);
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
        personService.deleteById(1L, Owner.class);

        // Then: Verify the output or expected result
        verify(personDAO).deleteById(1L, Owner.class);
    }

    @Test
    //@Disabled
    void deleteAllPersons() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test

        // Then: Verify the output or expected result
    }

}