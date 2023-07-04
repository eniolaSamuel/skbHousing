package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.models.User;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.CheckInRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.CheckOutRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.FindUserResponse;

import java.util.List;

public interface UserService {
    FindUserResponse findUser(Long id);
    String deleteUser(Long id);
    List<User> findAllUsers();
    BookedApartmentResponse bookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException;
    List<Apartment> viewAllApartments();

    List <Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException;

    List <Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException;

}
