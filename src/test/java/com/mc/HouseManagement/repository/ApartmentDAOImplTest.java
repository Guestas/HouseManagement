package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.entity.Apartment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Testing of ApartmentDao")
class ApartmentDAOImplTest {


    private final ApartmentDAO apartmentDAO;
    private final HouseMeetingDAO houseMeetingDAO;

    @Autowired
    public ApartmentDAOImplTest(ApartmentDAO apartmentDAO, HouseMeetingDAO houseMeetingDAO){
        this.apartmentDAO = apartmentDAO;
        this.houseMeetingDAO = houseMeetingDAO;
    }

    @AfterEach
    void clearAllData(){
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        apartmentDAO.deleteAllApartments();
        List<Apartment> actualApartmentList = apartmentDAO.loadAllApartments();

        // Then: Verify the output or expected result
        assertThat(actualApartmentList).isEmpty();

    }

    @Test
    @DisplayName("Test Add Apartment and load it by ID")
    void addApartmentAndLoadById() {
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null, null);

        // When: Action or behavior that we are going to test
        Long id = apartmentDAO.addUpdateApartment(testApartment1);
        Apartment retrieve = apartmentDAO.getApartmentById(id);

        // Then: Verify the output or expected result
        assertNotNull(retrieve);
        assertThat(id).isEqualTo(testApartment1.getId());
    }

    @Test
    @DisplayName("Test Loading all Apartments")
    void loadAllApartments() {
        // Given: Setup object or precondition
        Apartment testApartment1 = Apartment.createApartment(5, 4, 5,
                2553, "street1", null,null);
        Apartment testApartment2 = Apartment.createApartment(10, 8, 10,
                2553, "street2", null,null);
        List<Apartment> expectedApartmentList = Arrays.asList(testApartment1, testApartment2);

        // When: Action or behavior that we are going to test
        expectedApartmentList.forEach(apartment -> apartmentDAO.addUpdateApartment(apartment));
        List<Apartment> actualApartmentList = apartmentDAO.loadAllApartments();

        // Then: Verify the output or expected result
        assertNotNull(actualApartmentList);
        assertEquals(testApartment1.getAddress(), actualApartmentList.get(0).getAddress());
        assertEquals(testApartment2.getAddress(), actualApartmentList.get(1).getAddress());
    }

}