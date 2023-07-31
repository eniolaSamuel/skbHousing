package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.services.ApartmentService;
import com.SkBHousing.skbhousingapp.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class AdminController {

    private final BookingService bookingService;
    private final ApartmentService apartmentService;

    @GetMapping("listOfBookings")
    public ResponseEntity <List<Booking>> listOfBookings () {
        List<Booking> allBookings = bookingService.viewAllBookings();
        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("oneBooking")
    public ResponseEntity <Booking> foundBookingBySerialNumber(@RequestBody SearchBookingBySerialNumberRequest searchBookingBySerialNumberRequest) {
        Booking foundBooking = bookingService.viewBookingByBookingSerialNumber(searchBookingBySerialNumberRequest.getBookingSerialNumber());
        return ResponseEntity.ok(foundBooking);
    }
    @GetMapping("oneApartment")
    public ResponseEntity <Apartment> foundApartmentBySerialNumber(@RequestBody SearchApartmentBySerialNumberRequest searchApartmentBySerialNumberRequest){
        Apartment foundApartment = apartmentService.viewApartmentByApartmentSerialNumber(searchApartmentBySerialNumberRequest.getApartmentSerialNumber());
        return ResponseEntity.ok(foundApartment);
    }

    @PostMapping("saveAnApartment")
    public ResponseEntity <String> saveNewApartment(@RequestBody ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        String savedApartment = apartmentService.saveNewApartment(apartmentRegisterRequest);
        return ResponseEntity.ok(savedApartment);
    }

    @DeleteMapping("deleteAnApartment")
    public ResponseEntity <String> deleteApartment(@RequestBody Long id){
        String toBeDeleted = apartmentService.deleteApartment(id);
        return ResponseEntity.ok(toBeDeleted);
    }

    @GetMapping("listOfAllApartments")
    public ResponseEntity <List<Apartment>> listOfAllApartments() {
        List<Apartment> allApartments = apartmentService.viewAllApartments();
        return ResponseEntity.ok(allApartments);
    }

    @GetMapping("findApartmentsByType")
    public ResponseEntity <List<Apartment>> findApartmentByTypes(@RequestBody FindApartmentByTypeRequest houseType) throws IllegalAccessException {
        List<Apartment> allApartmentByHouseType = apartmentService.viewAllApartmentByHouseType(houseType);
        return ResponseEntity.ok(allApartmentByHouseType);
    }

    @GetMapping("findApartmentsByLocation")
    public ResponseEntity <List<Apartment>> findApartmentByLocations(@RequestBody FindApartmentByLocationRequest houseGeoLocation) throws IllegalAccessException {
        List<Apartment> allApartmentByHouseGeoLocation = apartmentService.viewAllApartmentByHouseGeoLocation(houseGeoLocation);
        return ResponseEntity.ok(allApartmentByHouseGeoLocation);
    }

    @GetMapping("findBookingBySerialNumber")
    public  ResponseEntity<Booking> findBookingBySerialNumber(String bookingSerialNumber){
        return new ResponseEntity<>(bookingService.viewBookingByBookingSerialNumber(bookingSerialNumber), HttpStatus.FOUND);
    }

}
