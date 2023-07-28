package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class BookingServiceImplTest {
    @Autowired
    private BookingService bookingService;


//    @AfterEach
//    void tearDown() {
//        bookingService.deleteAll();
//    }

    @Test
    void save() {
        Booking booking = new Booking();
        booking.setBookingSerialNumber(bookingService.generateBookingSerialNumber());
        booking.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        booking.setPrice(BigDecimal.valueOf(45000));
        booking.setApartmentName("eko court");
        booking.setApartmentType(HouseType.STANDARD_STUDIO);
        booking.setApartmentAddress("kofo abayomi");
        booking.setLocation(HouseGeoLocation.LAGOS_ISLAND);
        booking.setPaymentStatus(Payment_Status.PENDING);
        bookingService.save(booking);
        List<Booking> foundBooking = bookingService.viewAllBookings();
        assertEquals(1, foundBooking.size());
    }

    @Test
    void cancelBooking() {
        Booking booking = new Booking();
        String serial = bookingService.generateBookingSerialNumber();
        booking.setBookingSerialNumber(serial);
        booking.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        booking.setPrice(BigDecimal.valueOf(45000));
        booking.setApartmentName("talabi");
        booking.setApartmentType(HouseType.PARTY_HOUSE);
        booking.setApartmentAddress("32, boet estate");
        booking.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        booking.setPaymentStatus(Payment_Status.PENDING);
        bookingService.save(booking);
        List<Booking> foundBooking = bookingService.viewAllBookings();
        assertEquals(2, foundBooking.size());
        bookingService.cancelBooking(serial);
        List<Booking> foundBooking2 = bookingService.viewAllBookings();
        assertEquals(1, foundBooking2.size());
    }

    @Test
    void viewAllBookings() {
        Booking booking = new Booking();
        String serial = bookingService.generateBookingSerialNumber();
        booking.setBookingSerialNumber(serial);
        booking.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        booking.setPrice(BigDecimal.valueOf(45000));
        booking.setApartmentName("talabi");
        booking.setApartmentType(HouseType.PARTY_HOUSE);
        booking.setApartmentAddress("32, boet estate");
        booking.setLocation(HouseGeoLocation.LAGOS_MAINLAND);
        booking.setPaymentStatus(Payment_Status.PENDING);
        bookingService.save(booking);

        List<Booking> foundBooking3 = bookingService.viewAllBookings();
        assertEquals(1, foundBooking3.size());

    }
}