package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class ApartmentServiceImplTest {
    @Autowired
    private ApartmentService apartmentService;

//    @AfterEach
//    public void tearDown(){
//        apartmentService.deleteAll();
//    }


    @Test
    public void saveAnApartment_Test() throws IllegalAccessException {
        ApartmentRegisterRequest newApartment = new ApartmentRegisterRequest();
        newApartment.setName("houseSix");
        newApartment.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        newApartment.setApartmentType(HouseType.EXECUTIVE_STUDIO);
        newApartment.setAddress("baba yusuf close");
        newApartment.setPrice(BigDecimal.valueOf(25000));
        newApartment.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        apartmentService.saveNewApartment(newApartment);
        List <Apartment> foundApartment = apartmentService.viewAllApartments();
        assertEquals(1, foundApartment.size());
    }

    @Test
    public void Apartment_CannotBeRegistered_WithExistingName_Test() throws IllegalAccessException {
        ApartmentRegisterRequest newApartmentregistration = new ApartmentRegisterRequest();
        newApartmentregistration.setName("talabi");
        newApartmentregistration.setApartmentType(HouseType.PARTY_HOUSE);
        newApartmentregistration.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        newApartmentregistration.setAddress("32, boet estate");
        newApartmentregistration.setPrice(BigDecimal.valueOf(45000));
        apartmentService.saveNewApartment(newApartmentregistration);

        ApartmentRegisterRequest duplicateApartmentRegistration = new ApartmentRegisterRequest();
        duplicateApartmentRegistration.setName("talabi");
        duplicateApartmentRegistration.setApartmentType(HouseType.PARTY_HOUSE);
        duplicateApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        duplicateApartmentRegistration.setAddress("32, boet estate");
        duplicateApartmentRegistration.setPrice(BigDecimal.valueOf(45000));

        if (Objects.equals(newApartmentregistration.getName(), duplicateApartmentRegistration.getName()))
            assertThrows(IllegalAccessException.class, () -> apartmentService.saveNewApartment(duplicateApartmentRegistration));
        }

//    @Test
//    public void viewAllApartment_Test() throws IllegalAccessException {
//        ApartmentRegisterRequest firstApartmentRegistration= new ApartmentRegisterRequest();
//        firstApartmentRegistration.setName("eko court");
//        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
//        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        firstApartmentRegistration.setAddress("32, boet estate");
//        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
//        apartmentService.saveNewApartment(firstApartmentRegistration);
//
//        ApartmentRegisterRequest secondApartmentRegistration = new ApartmentRegisterRequest();
//        secondApartmentRegistration.setName("towers");
//        secondApartmentRegistration.setApartmentType(HouseType.EXECUTIVE_STUDIO);
//        secondApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        secondApartmentRegistration.setAddress("deola deku");
//        secondApartmentRegistration.setPrice(BigDecimal.valueOf(50000));
//        apartmentService.saveNewApartment(secondApartmentRegistration);
//        List<Apartment> apartmentList = apartmentService.viewAllApartments();
//        assertEquals(2, apartmentList.size());
//    }

    @Test
    public void viewApartmentByHouseType_Test() throws IllegalAccessException{
        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
        firstApartmentRegistration.setName("eko court");
        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        firstApartmentRegistration.setAddress("kofo abayomi");
        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));

        apartmentService.saveNewApartment(firstApartmentRegistration);
        List<Apartment> apartment = apartmentService.viewAllApartmentByHouseType(firstApartmentRegistration.getApartmentType());
        assertEquals(apartment.get(0).getApartmentType(), HouseType.STANDARD_STUDIO);
    }

//    @Test
//    public void viewApartmentByName_Test() throws IllegalAccessException{
//        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
//        firstApartmentRegistration.setName("eko court");
//        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
//        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        firstApartmentRegistration.setAddress("kofo abayomi");
//        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
//
//        String newApartment3 = apartmentService.saveNewApartment(firstApartmentRegistration);
//        Apartment apartments = apartmentService.findApartmentByName(firstApartmentRegistration.getName());
//
//        assertEquals(apartments.getApartmentName(), "eko court");
//    }
//
//    @Test
//    public void testThatApartmentCanBeViewedBy_availability_test() throws IllegalAccessException {
//        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
//        firstApartmentRegistration.setName("eko court");
//        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
//        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        firstApartmentRegistration.setAddress("kofo abayomi");
//        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
//        apartmentService.saveNewApartment(firstApartmentRegistration);
//
//        ApartmentRegisterRequest secondApartmentRegistration = new ApartmentRegisterRequest();
//        secondApartmentRegistration.setName("towers");
//        secondApartmentRegistration.setApartmentType(HouseType.EXECUTIVE_STUDIO);
//        secondApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        secondApartmentRegistration.setAddress("deola deku");
//        secondApartmentRegistration.setPrice(BigDecimal.valueOf(50000));
//        apartmentService.saveNewApartment(secondApartmentRegistration);
//
//        ApartmentRegisterRequest thirdApartmentRegistration= new ApartmentRegisterRequest();
//        thirdApartmentRegistration.setName("boet court");
//        thirdApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
//        thirdApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
//        thirdApartmentRegistration.setAddress("32, boet estate");
//        thirdApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
//        apartmentService.saveNewApartment(thirdApartmentRegistration);
//
//
//        List<Apartment> apartmentList = apartmentService.viewAllApartmentByApartmentStatus(ApartmentStatus.IS_AVAILABLE);
//        assertEquals(3, apartmentList.size());
//    }

//    @Test
//    public void deleteApartmentBy_Id() throws IllegalAccessException{
//
//    }
}