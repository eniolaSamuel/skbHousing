package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.ApartmentRepository;
import com.SkBHousing.skbhousingapp.data.repositories.BookingRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.dtos.responses.AdminBookedResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SavedApartmentResponse;
import com.SkBHousing.skbhousingapp.utils.ApartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private ApartmentRepository apartmentRepository ;
    private BookingRepository bookingRepository;
    private UserService userService;

    public ApartmentServiceImpl(@Autowired ApartmentRepository repository){
        apartmentRepository = repository;
    }

    @Override
    public Apartment save(Apartment apartment) {

        return apartmentRepository.save(apartment);
    }

    @Override
    public String deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
        return "Apartment with id " + id + "has been deleted";
    }

    @Override
    public SavedApartmentResponse saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        //Apartment apartment = new Apartment();
        List<Apartment> apartments = viewAllApartments();
        for (Apartment anApartment : apartments) {
            if (apartmentRegisterRequest.getName().equals(anApartment.getApartmentName()))
                throw new IllegalAccessException("Apartment already exists.");
        }
        Apartment savedApartment = apartmentRepository.save(ApartmentMapper.mapApartmentRegister(apartmentRegisterRequest));
        SavedApartmentResponse apartmentResponse = new SavedApartmentResponse();
        ApartmentMapper.viewSavedApartment(savedApartment, apartmentResponse);
        return apartmentResponse;
    }
    @Override
    public BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest) throws IllegalAccessException {
        List<Booking> bookings = viewAllBookings();
        for (Booking booking: bookings) {
            if (booking.isBooked()) throw new IllegalAccessException("Apartment already booked");
        }
        Booking booking = ApartmentMapper.mapBookApartment(bookApartmentRequest);
        bookingRepository.save(booking);

        BookedApartmentResponse bookedApartmentResponse = new BookedApartmentResponse();
        ApartmentMapper.mapViewBookedApartment(booking, bookedApartmentResponse);
        return bookedApartmentResponse;
    }

    @Override
    public AdminBookedResponse adminBookApartment(AdminBookingRequest adminBookingRequest) throws IllegalAccessException {
        List<Apartment>apartments = viewAllApartments();
        for (Apartment apartment: apartments){
            if (apartment.isBooked()) throw new IllegalAccessException("Apartment already booked");
        }
        Apartment apartment = ApartmentMapper.mapAdminBooking(adminBookingRequest);
        apartmentRepository.save(apartment);

        AdminBookedResponse adminBookedResponse = new AdminBookedResponse();
        ApartmentMapper.mapViewAdminBookedApartment(apartment, adminBookedResponse);
        return adminBookedResponse;
    }

    @Override
    public List<Apartment> viewAllApartments() {
        return ApartmentMapper.listOfApartments(apartmentRepository.findAll());
    }

    @Override
    public List<Booking> viewAllBookings(){
        return ApartmentMapper.listOfBookings(bookingRepository.findAll());
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException {
        boolean validApartmentType = apartmentType.equals(HouseType.EXECUTIVE_STUDIO)
                || apartmentType.equals(HouseType.STANDARD_STUDIO)
                || apartmentType.equals(HouseType.ONE_BDR_FLAT)
                || apartmentType.equals(HouseType.TWO_BDR_FLAT)
                || apartmentType.equals(HouseType.THREE_BDR_FLAT)
                || apartmentType.equals(HouseType.FOUR_BDR_FLAT)
                || apartmentType.equals(HouseType.PARTY_HOUSE);
        if (!validApartmentType)
            throw new IllegalAccessException("Apartment type selected not available, input valid apartment type");
        return ApartmentMapper.listOfApartments(apartmentRepository.findAllByApartmentType(apartmentType));
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException {
        boolean validHouseGeoLocation = location.equals(HouseGeoLocation.LAGOS_ISLAND)
                || location.equals(HouseGeoLocation.LAGOS_MAINLAND);
        if (!validHouseGeoLocation)
            throw new IllegalAccessException("Geographic location not available, try again.");
        return ApartmentMapper.listOfApartments(apartmentRepository.findAllByLocation(location));
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseTypeAndHouseGeoLocation(HouseType apartmentType, HouseGeoLocation location) {
        if (apartmentRepository.findApartmentByLocationAndApartmentType(location, apartmentType) == null)
            throw new NullPointerException("No Apartment Matches Selection");
        return ApartmentMapper.listOfApartments(apartmentRepository.findApartmentByLocationAndApartmentType(location, apartmentType));
    }

    @Override
    public Apartment checkIn(Apartment apartment, CheckInRequest checkInRequest) {
        return null;
    }

    @Override
    public Apartment checkOut(Apartment apartment, CheckOutRequest checkOutRequest) {
        return null;
    }
}
