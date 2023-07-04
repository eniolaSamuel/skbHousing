package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.ApartmentRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApartmentServiceImplTest {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ApartmentRepository apartmentRepository;

    //@AfterEach
    //public void atTheEndOfEach(){
      //  for (Apartment createdApartment: createdApartments)
    //}


    @Test
    public void saveAnApartment_Test() throws IllegalAccessException {

        apartmentService = new ApartmentServiceImpl(apartmentRepository);

        ApartmentRegisterRequest newApartment = new ApartmentRegisterRequest();

        newApartment.setName("houseSix");
        newApartment.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        newApartment.setApartmentType(HouseType.EXECUTIVE_STUDIO);
        newApartment.setAddress("baba yusuf close");
        newApartment.setPrice(BigDecimal.valueOf(25000));
        newApartment.setBooked(true);
        var savedApartmentOne = apartmentService.saveNewApartment(newApartment);
        assertEquals("houseSix", savedApartmentOne.getName());
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
        duplicateApartmentRegistration.setApartmentType(HouseType.EXECUTIVE_STUDIO);
        duplicateApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        duplicateApartmentRegistration.setAddress("32, boet estate");
        duplicateApartmentRegistration.setPrice(BigDecimal.valueOf(45000));

        if (Objects.equals(newApartmentregistration.getName(), duplicateApartmentRegistration.getName()))
            assertThrows(IllegalAccessException.class, () -> apartmentService.saveNewApartment(duplicateApartmentRegistration));
       // assertEquals(newApartmentregistration.getName(), duplicateApartmentRegistration.getName());
    }

    @Test
    public void viewAllApartment_Test() throws IllegalAccessException {

        ApartmentRegisterRequest firstApartmentRegistration= new ApartmentRegisterRequest();
        firstApartmentRegistration.setName("eko court");
        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        firstApartmentRegistration.setAddress("32, boet estate");
        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
        apartmentService.saveNewApartment(firstApartmentRegistration);

        ApartmentRegisterRequest secondApartmentRegistration = new ApartmentRegisterRequest();
        secondApartmentRegistration.setName("towers");
        secondApartmentRegistration.setApartmentType(HouseType.EXECUTIVE_STUDIO);
        secondApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        secondApartmentRegistration.setAddress("deola deku");
        secondApartmentRegistration.setPrice(BigDecimal.valueOf(50000));
        apartmentService.saveNewApartment(secondApartmentRegistration);
        List<Apartment> apartmentList = apartmentService.viewAllApartments();
        assertEquals(2, apartmentList.size());
    }

    @Test
    public void viewApartmentByHouseType_Test() throws IllegalAccessException{
        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
        firstApartmentRegistration.setName("eko court");
        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        firstApartmentRegistration.setAddress("kofo abayomi");
        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));

       SavedApartmentResponse newApartment2 = apartmentService.saveNewApartment(firstApartmentRegistration);

        List<Apartment> apartments = apartmentService.viewAllApartmentByHouseType(HouseType.STANDARD_STUDIO);
        assertEquals(apartments.get(0).getApartmentType(), newApartment2.getApartmentType());
    }

    @Test
    public void viewApartmentByHouseGeoLocation_Test() throws IllegalAccessException{
        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
        firstApartmentRegistration.setName("eko court");
        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        firstApartmentRegistration.setAddress("kofo abayomi");
        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));

        SavedApartmentResponse newApartment3 = apartmentService.saveNewApartment(firstApartmentRegistration);

        List<Apartment> apartments = apartmentService.viewAllApartmentByHouseGeoLocation(HouseGeoLocation.LAGOS_ISLAND);
        assertEquals(apartments.get(0).getApartmentType(), newApartment3.getApartmentType());
    }

    @Test
    public void testThatApartmentCanBeViewedBy_HouseType_And_HouseGeoLocation() throws IllegalAccessException {
        //var apartmentObject = new SavedApartmentResponse();
        ApartmentRegisterRequest firstApartmentRegistration = new ApartmentRegisterRequest();
        firstApartmentRegistration.setName("eko court");
        firstApartmentRegistration.setApartmentType(HouseType.STANDARD_STUDIO);
        firstApartmentRegistration.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        firstApartmentRegistration.setAddress("kofo abayomi");
        firstApartmentRegistration.setPrice(BigDecimal.valueOf(45000));
        SavedApartmentResponse apartmentObject = apartmentService.saveNewApartment(firstApartmentRegistration);
        var apartmentResponse = apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocation(HouseType.STANDARD_STUDIO, HouseGeoLocation.LAGOS_ISLAND);
        assertEquals(apartmentObject.getLocation(),apartmentResponse.get(0).getLocation() );
        assertEquals(apartmentObject.getApartmentType(), apartmentResponse.get(0).getApartmentType()
        );
    }

    @Test
    public void deleteApartmentBy_Id() throws IllegalAccessException{

    }
}