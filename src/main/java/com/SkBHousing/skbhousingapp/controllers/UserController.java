package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import com.SkBHousing.skbhousingapp.services.ApartmentService;
import com.SkBHousing.skbhousingapp.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookApartment")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class UserController {

    private final ApartmentService apartmentService;
    private final BookingService bookingService;

    @GetMapping("makeChoice")
    public ResponseEntity<SelectApartmentsResponse> makeChoice (@RequestBody SelectLocationAndTypeRequest selectLocationAndTypeRequest, ApartmentStatus apartmentStatus) {
           SelectApartmentsResponse foundApartments = apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(selectLocationAndTypeRequest, apartmentStatus);
           return ResponseEntity.ok(foundApartments);
    }

    @PostMapping("bookNow")
    public ResponseEntity<BookedApartmentResponse> bookNow(@RequestBody BookApartmentRequest bookApartmentRequest){
        return new ResponseEntity<>(bookingService.userBookApartment(bookApartmentRequest), HttpStatus.OK);
    }

}
