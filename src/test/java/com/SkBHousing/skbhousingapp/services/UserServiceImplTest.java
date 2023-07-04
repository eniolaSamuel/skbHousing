package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.ApartmentRepository;
import com.SkBHousing.skbhousingapp.data.repositories.UserRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    public void bookAnApartment_Test() throws IllegalAccessException {

        apartmentService = new ApartmentServiceImpl(apartmentRepository);

        BookApartmentRequest bookApartmentRequest = new BookApartmentRequest();
        bookApartmentRequest.setApartmentId(1L);
        bookApartmentRequest.setUserFullName("cocoa");
        bookApartmentRequest.setUserPhoneNumber("07080");
        bookApartmentRequest.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        bookApartmentRequest.setApartmentType(HouseType.EXECUTIVE_STUDIO);
        var firstBooking = apartmentService.userBookApartment(bookApartmentRequest);
        assertEquals(HouseGeoLocation.LAGOS_MAINLAND, firstBooking.getLocation());

    }
}