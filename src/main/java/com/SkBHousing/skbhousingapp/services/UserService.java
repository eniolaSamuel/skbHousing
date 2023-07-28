package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;

import java.util.List;

public interface UserService {
    List<Apartment> viewAllApartments();
    List <Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException;
    SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest ,
                                                                                              ApartmentStatus apartmentStatus);
    Apartment findApartmentByName (String apartmentName);
    BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest);
    String cancelBooking(String bookingSerialNumber);
}
