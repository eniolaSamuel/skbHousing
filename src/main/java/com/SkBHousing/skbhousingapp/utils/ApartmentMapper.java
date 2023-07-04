package com.SkBHousing.skbhousingapp.utils;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.dtos.requests.AdminBookingRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.AdminBookedResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;

import java.util.List;

public class ApartmentMapper {
    public static Apartment mapApartmentRegister (ApartmentRegisterRequest apartmentRegisterRequest){
        Apartment apartment = new Apartment();
        apartment.setApartmentName(apartmentRegisterRequest.getName());
        apartment.setLocation(apartmentRegisterRequest.getLocation());
        apartment.setApartmentType(apartmentRegisterRequest.getApartmentType());
        apartment.setPrice(apartmentRegisterRequest.getPrice());

        return apartment;
    }

    public static void viewSavedApartment(Apartment apartment, SavedApartmentResponse apartmentResponse) {
        apartmentResponse.setName(apartment.getApartmentName());
        apartmentResponse.setApartmentType(apartment.getApartmentType());
        apartmentResponse.setLocation(apartment.getLocation());
        apartmentResponse.setPrice(apartment.getPrice());
    }

    public static void mapViewAdminBookedApartment(Apartment apartment, AdminBookedResponse adminBookedResponse) {
        adminBookedResponse.setName(apartment.getApartmentName());
        adminBookedResponse.setApartmentType(apartment.getApartmentType());
        adminBookedResponse.setLocation(apartment.getLocation());
        adminBookedResponse.setPrice(apartment.getPrice());
        adminBookedResponse.setAddress(apartment.getAddress());
    }

    public static void mapViewBookedApartment(Booking booking, BookedApartmentResponse bookedApartmentResponse) {
        bookedApartmentResponse.setName(booking.getApartmentName());
        bookedApartmentResponse.setApartmentType(booking.getApartmentType());
        bookedApartmentResponse.setLocation(booking.getLocation());
        //bookedApartmentResponse.setPrice(booking.getPrice());
        bookedApartmentResponse.setAddress(booking.getApartmentAddress());
        bookedApartmentResponse.setBooked(booking.isBooked());
    }

    public static Booking mapBookApartment(BookApartmentRequest bookApartmentRequest) {
        Booking booking = new Booking();

        booking.setApartmentId(bookApartmentRequest.getApartmentId());
        booking.setApartmentType(bookApartmentRequest.getApartmentType());
        booking.setLocation(bookApartmentRequest.getLocation());
        booking.setUserFullName(bookApartmentRequest.getUserFullName());
        booking.setUserPhoneNumber(bookApartmentRequest.getUserPhoneNumber());
        return booking;
    }
    public static Apartment mapAdminBooking(AdminBookingRequest adminBookingRequest) {
        Apartment apartment = new Apartment();
        apartment.setApartmentName(adminBookingRequest.getName());
        apartment.setApartmentType(adminBookingRequest.getApartmentType());
        apartment.setLocation(adminBookingRequest.getLocation());
        apartment.setAddress(adminBookingRequest.getAddress());
        apartment.setPrice(adminBookingRequest.getPrice());
        return apartment;
    }
    public static List<Apartment> listOfApartments(List<Apartment> apartments) {
        return apartments;
    }

    public static List<Booking> listOfBookings(List<Booking> bookings) {return bookings; }


}
