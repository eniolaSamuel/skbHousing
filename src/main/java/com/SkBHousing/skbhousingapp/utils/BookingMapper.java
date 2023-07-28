package com.SkBHousing.skbhousingapp.utils;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.dtos.requests.AdminBookingRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;

import java.util.List;

public class BookingMapper {

    public static void mapViewBookedApartment(Booking booking, BookedApartmentResponse bookedApartmentResponse) {
        bookedApartmentResponse.setApartmentType(booking.getApartmentType());
        bookedApartmentResponse.setPrice(booking.getPrice());;
    }

    public static Booking mapBookApartment(BookApartmentRequest bookApartmentRequest) {
        Booking booking = new Booking();
        //booking.setApartmentName(bookApartmentRequest.getApartmentName());
        booking.setUserFullName(bookApartmentRequest.getUserFullName());
        booking.setUserPhoneNumber(bookApartmentRequest.getUserPhoneNumber());
        booking.setStartBookDate(bookApartmentRequest.getStartBookDate());
        booking.setEndBookDate(bookApartmentRequest.getEndBookDate());
        booking.setPaymentStatus(bookApartmentRequest.getPaymentStatus());
        return booking;
    }

    public static List<Booking> listOfBookings(List<Booking> bookings) {return bookings; }
}
