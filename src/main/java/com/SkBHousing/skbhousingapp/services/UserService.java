package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByLocationRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;

import java.util.List;

public interface UserService {
    List<Apartment> viewAllApartments();
    List <Apartment> viewAllApartmentByHouseType(FindApartmentByTypeRequest apartmentType) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseGeoLocation(FindApartmentByLocationRequest location) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException;
    SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest ,
                                                                                              ApartmentStatus apartmentStatus);
    Apartment findApartmentByName (String apartmentName);
    BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest);
    Booking findByBookingSerialNumber(String bookingSerialNumber);
    String cancelBooking(String bookingSerialNumber);
}
