package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.UserRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BookingService bookingService;
    private final ApartmentService apartmentService;

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

    @Override
    public List<Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByApartmentStatus(apartmentStatus);
    }

    @Override
    public SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest, ApartmentStatus apartmentStatus) {
        return apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(selectLocationAndTypeRequest, apartmentStatus);
    }

    @Override
    public Apartment findApartmentByName(String apartmentName) {
        return apartmentService.findApartmentByName(apartmentName);
    }

    @Override
    public BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest) {
        return bookingService.userBookApartment(bookApartmentRequest);
    }

    @Override
    public String cancelBooking(String bookingSerialNumber) {
        return bookingService.cancelBooking(bookingSerialNumber);
    }

}
