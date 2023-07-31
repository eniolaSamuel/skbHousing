package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByLocationRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import com.SkBHousing.skbhousingapp.services.ApartmentService;
import com.SkBHousing.skbhousingapp.services.BookingService;
import com.SkBHousing.skbhousingapp.services.ClockingService;
import com.SkBHousing.skbhousingapp.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookApartment")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class UserController {

    private final ApartmentService apartmentService;
    private final BookingService bookingService;
    private final ClockingService clockingService;

    @GetMapping("makeChoiceByAvailability")
    public ResponseEntity<SelectApartmentsResponse> makeChoice (@RequestBody SelectLocationAndTypeRequest selectLocationAndTypeRequest, ApartmentStatus apartmentStatus) {
           SelectApartmentsResponse foundApartments = apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(selectLocationAndTypeRequest, apartmentStatus);
           return ResponseEntity.ok(foundApartments);
    }

    @PostMapping("bookNow")
    public ResponseEntity<BookedApartmentResponse> bookNow(@RequestBody BookApartmentRequest bookApartmentRequest){
        return new ResponseEntity<>(bookingService.userBookApartment(bookApartmentRequest), HttpStatus.OK);
    }

    @PostMapping("clockIn")
    public ResponseEntity<ApiResponse> clockIn (@RequestBody String userPhoneNumber) throws IllegalAccessException {
        return new ResponseEntity<>(clockingService.clockIn(userPhoneNumber), HttpStatus.OK);
    }

    @PostMapping("clockOut")
    public ResponseEntity<ApiResponse> clockOut (@RequestBody String userPhoneNumber) throws IllegalAccessException {
        return new ResponseEntity<>(clockingService.clockOut(userPhoneNumber), HttpStatus.OK);
    }

    @GetMapping("findBookingBySerialNumber")
    public  ResponseEntity<Booking> findBookingBySerialNumber(String bookingSerialNumber){
        return new ResponseEntity<>(bookingService.viewBookingByBookingSerialNumber(bookingSerialNumber), HttpStatus.FOUND);
    }

    @DeleteMapping("cancelBooking")
    public ResponseEntity<String> cancelBooking(@RequestBody String bookingSerialNumber) {
        String toBeCancelled = bookingService.cancelBooking(bookingSerialNumber);
        return ResponseEntity.ok(toBeCancelled);
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

}
