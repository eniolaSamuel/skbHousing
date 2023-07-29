package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SearchBySerialNumberRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.ListOfBookingsResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import com.SkBHousing.skbhousingapp.services.ApartmentService;
import com.SkBHousing.skbhousingapp.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(origins = "*")
@AllArgsConstructor
@Slf4j
public class AdminController {

    private final BookingService bookingService;
    private final ApartmentService apartmentService;

    @GetMapping("listOfBookings")
    public ResponseEntity <List<Booking>> listOfBookings () {
        List<Booking> allBookings = bookingService.viewAllBookings();
        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("oneApartment")
    public ResponseEntity <Booking> foundApartmentBySerialNumber(@RequestBody SearchBySerialNumberRequest searchBySerialNumberRequest) {
        Booking foundBooking = bookingService.viewBookingByBookingSerialNumber(searchBySerialNumberRequest.getSerialNumber());
        return ResponseEntity.ok(foundBooking);
    }

    @PostMapping("saveAnApartment")
    public ResponseEntity <String> saveNewApartment(@RequestBody ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        String savedApartment = apartmentService.saveNewApartment(apartmentRegisterRequest);
        return ResponseEntity.ok(savedApartment);
    }

    @DeleteMapping("deleteAnApartment")
    public ResponseEntity <String> deleteApartment(Long id){
        String toBeDeleted = apartmentService.deleteApartment(id);
        return ResponseEntity.ok(toBeDeleted);
    }

    @GetMapping("listOfAllApartments")
    public ResponseEntity <List<Apartment>> listOfAllApartments() {
        List<Apartment> allApartments = apartmentService.viewAllApartments();
        return ResponseEntity.ok(allApartments);
    }

    @GetMapping("findApartmentsByType")
    public ResponseEntity <List<Apartment>> findApartmentByTypes(@RequestBody HouseType houseType) throws IllegalAccessException {
        log.info("This is house type {}", houseType);
        List<Apartment> allApartmentByHouseType = apartmentService.viewAllApartmentByHouseType(houseType);
        return ResponseEntity.ok(allApartmentByHouseType);
    }

    @GetMapping("findApartmentsByLocation")
    public ResponseEntity <List<Apartment>> findApartmentByLocations(HouseGeoLocation houseGeoLocation) throws IllegalAccessException {
        List<Apartment> allApartmentByHouseGeoLocation = apartmentService.viewAllApartmentByHouseGeoLocation(houseGeoLocation);
        return ResponseEntity.ok(allApartmentByHouseGeoLocation);
    }

}
