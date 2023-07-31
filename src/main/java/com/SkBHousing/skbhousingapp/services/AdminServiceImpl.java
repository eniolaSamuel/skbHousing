package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.data.repositories.AdminRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
    private final ApartmentService apartmentService;
    private final BookingService bookingService;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public String registerNewAdmin(RegisterAdminRequest registerRequest) {
        adminRepository.save(modelMapper.map(registerRequest , Admin.class));
        return "Admin Successfully Registered";
    }

    @Override
    public Admin findAnAdmin(String adminEmail)  {
        return adminRepository.findAdminByEmail(adminEmail);
    }

    @Override
    public List<Admin> viewAllRegisteredAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public String saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        return apartmentService.saveNewApartment(apartmentRegisterRequest);
    }

    @Override
    public List<Booking> viewAllBookings() {
        return bookingService.viewAllBookings();
    }

    @Override
    public List<Apartment> viewAllApartments() {
        return apartmentService.viewAllApartments();
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseType(FindApartmentByTypeRequest apartmentType) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByHouseType(apartmentType);
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseGeoLocation(FindApartmentByLocationRequest location) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByHouseGeoLocation(location);
    }

    @Override
    public List<Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException {
        return apartmentService.viewAllApartmentByApartmentStatus(apartmentStatus);
    }

    @Override
    public SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest, ApartmentStatus apartmentStatus) {
        return apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(selectLocationAndTypeRequest,apartmentStatus);
    }

    @Override
    public Apartment findApartmentByName(String apartmentName) {
        return apartmentService.findApartmentByName(apartmentName);
    }

    @Override
    public String deleteApartment(Long id) {
        return apartmentService.deleteApartment(id);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingService.save(booking);
    }

    @Override
    public Booking findByBookingSerialNumber(String bookingSerialNumber) {
        return bookingService.viewBookingByBookingSerialNumber(bookingSerialNumber);
    }

}
