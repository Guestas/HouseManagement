package com.mc.HouseManagement.service;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.repository.ApartmentDAO;
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
class ApartmentServiceImplTest {

    @Mock private ApartmentDAO apartmentDAO;
    private ApartmentService apartmentService;

    @BeforeEach
    void setUp(){
        apartmentService = new ApartmentServiceImpl(apartmentDAO);
    }

    @Test
    void canAddApartment() {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT
        // When: Action or behavior that we are going to test
        apartmentService.addUpdateApartment(TestVariables.APARTMENT);

        // Then: Verify the output or expected result
        ArgumentCaptor<Apartment> apartmentArgumentCaptor = ArgumentCaptor.forClass(Apartment.class);
        verify(apartmentDAO).addUpdateApartment(apartmentArgumentCaptor.capture());
        Apartment capturedApartment = apartmentArgumentCaptor.getValue();
        assertThat(capturedApartment).isEqualTo(TestVariables.APARTMENT);
    }

    @Test
    void canGetApartmentById() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        apartmentService.getApartmentById(1L);

        // Then: Verify the output or expected result
        verify(apartmentDAO).getApartmentById(1L);
    }

    @Test
    void loadAllApartments() {
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        apartmentService.loadAllApartments();

        // Then: Verify the output or expected result
        verify(apartmentDAO).loadAllApartments();
    }
}