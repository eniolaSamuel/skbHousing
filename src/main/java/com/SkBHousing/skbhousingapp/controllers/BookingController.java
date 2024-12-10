package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookedApartmentResponse> bookApartment(@RequestBody BookApartmentRequest bookApartmentRequest) {
        BookedApartmentResponse response = bookingService.userBookApartment(bookApartmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.viewAllBookings();
        return ResponseEntity.ok(bookings);
    }

    //booking by serial number
    @GetMapping("/{bookingSerialNumber}")
    public ResponseEntity<Booking> getBookingBySerialNumber(@PathVariable String bookingSerialNumber) {
        Booking booking = bookingService.viewBookingByBookingSerialNumber(bookingSerialNumber);
        return ResponseEntity.ok(booking);
    }
