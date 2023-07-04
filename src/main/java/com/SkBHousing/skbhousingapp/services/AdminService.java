package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.User;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.FindUserResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;

import java.util.List;

public interface AdminService {
    SavedApartmentResponse register(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException;

    FindUserResponse findUser(Long id);
    List<User> findAllUsers();
    String deleteUser(Long id);
    BookedApartmentResponse bookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException;

}
