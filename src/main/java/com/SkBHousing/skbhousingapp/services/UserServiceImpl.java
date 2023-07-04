package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.models.User;
import com.SkBHousing.skbhousingapp.data.repositories.UserRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.CheckInRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.CheckOutRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.FindUserResponse;
import com.SkBHousing.skbhousingapp.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApartmentService apartmentService;

    @Override
    public FindUserResponse findUser(Long id) {
        FindUserResponse findUserResponse = new FindUserResponse();
        User user = userRepository.findUserById(id);
        UserMapper.map(user, findUserResponse);
        return  findUserResponse;
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id " + id + "has been deleted";
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public BookedApartmentResponse bookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException {
        return apartmentService.userBookApartment(bookApartmentRequest);
    }

    @Override
    public List<Apartment> viewAllApartments() {
        return apartmentService.viewAllApartments();
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByHouseType(apartmentType);
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByHouseGeoLocation(location );
    }

}
