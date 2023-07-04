package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.dtos.responses.AdminBookedResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;

import java.util.List;

public interface ApartmentService {
    Apartment save(Apartment apartment);
    String deleteApartment(Long id);
    SavedApartmentResponse saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException;

    List<Apartment> viewAllApartments();

    List<Booking> viewAllBookings();

    List <Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseTypeAndHouseGeoLocation(HouseType apartmentType, HouseGeoLocation location);
    Apartment checkIn(Apartment apartment, CheckInRequest checkInRequest);
    Apartment checkOut(Apartment apartment, CheckOutRequest checkOutRequest);
    BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException;
    AdminBookedResponse adminBookApartment(AdminBookingRequest adminBookingRequest) throws IllegalAccessException;
}
