package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;

import java.util.List;

public interface ApartmentService {
    Apartment save(Apartment apartment);
    String deleteApartment(Long id);
    String saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException;
    List<Apartment> viewAllApartments();
    List <Apartment> viewAllApartmentByHouseType(FindApartmentByTypeRequest findApartMentByTypeRequest) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseGeoLocation(FindApartmentByLocationRequest location) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException;
    SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest ,
                                                                                              ApartmentStatus apartmentStatus);
    Apartment findApartmentByName (String apartmentName);
    String generateApartmentSerialNumber();
    Apartment viewApartmentByApartmentSerialNumber(String apartmentSerialNumber);
    void deleteAll();
}
