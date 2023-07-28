package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.RegisterAdminRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;

import java.util.List;

public interface AdminService {
    String registerNewAdmin(RegisterAdminRequest registerRequest);
    Admin findAnAdmin(String adminEmail);
    List<Admin> viewAllRegisteredAdmins();
    String saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException;
    List<Booking> viewAllBookings();
    List<Apartment> viewAllApartments();
    List <Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException;
    List <Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException;
    SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest , ApartmentStatus apartmentStatus);
    Apartment findApartmentByName (String apartmentName);
    String deleteApartment(Long id);
    Booking save(Booking booking);
    Booking findByBookingSerialNumber(String bookingSerialNumber);

}
