package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.User;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.FindUserResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements AdminService{

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private UserService userService;

    @Override
    public SavedApartmentResponse register(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        return apartmentService.saveNewApartment(apartmentRegisterRequest);
    }

    @Override
    public FindUserResponse findUser(Long id) {
        return userService.findUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public String deleteUser(Long id) {
        return userService.deleteUser(id);
    }


    @Override
    public BookedApartmentResponse bookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException {
        return apartmentService.userBookApartment(bookApartmentRequest);
    }
}
