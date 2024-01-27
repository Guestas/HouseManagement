package com.mc.HouseManagement.repository;

import com.mc.HouseManagement.TestVariables;
import com.mc.HouseManagement.entity.Apartment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DisplayName("Testing of ApartmentDao")
class ApartmentDAOImplTest {


    private final ApartmentDAO apartmentDAO;

    @Autowired
    public ApartmentDAOImplTest(ApartmentDAO apartmentDAO){
        this.apartmentDAO = apartmentDAO;
    }

    @AfterEach
    void clearAllData(){
        // Given: Setup object or precondition

        // When: Action or behavior that we are going to test
        apartmentDAO.deleteAllApartments();
        List<Apartment> actualApartmentList = apartmentDAO.getAllApartments();

        // Then: Verify the output or expected result
        assertThat(actualApartmentList).isEmpty();

    }

    @Test
    @DisplayName("Test Add Apartment and load it by ID")
    void addApartmentAndLoadById() {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT

        // When: Action or behavior that we are going to test
        Long id = apartmentDAO.addUpdateApartment(TestVariables.APARTMENT);
        Apartment retrieve = apartmentDAO.getApartmentById(id);

        // Then: Verify the output or expected result
        assertNotNull(retrieve);
        assertThat(id).isEqualTo(TestVariables.APARTMENT.getId());
    }

    @Test
    @DisplayName("Test Get Apartment by ID without data in DB")
    void getApartmentById() {
        // Given: Setup object or precondition
        Long id = 5L;

        // When: Action or behavior that we are going to test
        Apartment retrieve = apartmentDAO.getApartmentById(id);

        // Then: Verify the output or expected result
        assertNull(retrieve);
    }

    @Test
    @DisplayName("Test Loading all Apartments")
    void getAllApartments() {
        // Given: Setup object or precondition
        // TestVariables.APARTMENT_LIST

        // When: Action or behavior that we are going to test
        TestVariables.APARTMENT_LIST.forEach(apartmentDAO::addUpdateApartment);
        List<Apartment> actualApartmentList = apartmentDAO.getAllApartments();

        // Then: Verify the output or expected result
        assertNotNull(actualApartmentList);
        assertEquals(TestVariables.APARTMENT_LIST.get(0).getAddress(), actualApartmentList.get(0).getAddress());
        assertEquals(TestVariables.APARTMENT_LIST.get(1).getAddress(), actualApartmentList.get(1).getAddress());
    }

}