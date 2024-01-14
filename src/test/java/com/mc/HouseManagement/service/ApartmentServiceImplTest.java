package com.mc.HouseManagement.service;

import com.mc.HouseManagement.entity.Apartment;
import com.mc.HouseManagement.repository.ApartmentDAO;
import com.mc.HouseManagement.repository.HouseMeetingDAO;
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
    @Mock private HouseMeetingDAO houseMeetingDAO;
    private ApartmentService apartmentService;

    @BeforeEach
    void setUp(){
        apartmentService = new ApartmentServiceImpl(apartmentDAO);
    }

    @Test
    void canAddApartment() {
        // Given: Setup object or precondition

        Apartment testApartment = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);

        // When: Action or behavior that we are going to test
        apartmentService.addApartment(testApartment);

        // Then: Verify the output or expected result
        ArgumentCaptor<Apartment> apartmentArgumentCaptor = ArgumentCaptor.forClass(Apartment.class);
        verify(apartmentDAO).addApartment(apartmentArgumentCaptor.capture());
        Apartment capturedApartment = apartmentArgumentCaptor.getValue();
        assertThat(capturedApartment).isEqualTo(testApartment);
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