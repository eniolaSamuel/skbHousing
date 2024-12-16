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

    //book
    @PostMapping("/book")
    public ResponseEntity<BookedApartmentResponse> bookApartment(@RequestBody BookApartmentRequest bookApartmentRequest) {
        BookedApartmentResponse response = bookingService.userBookApartment(bookApartmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //list of all bookings
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

    //cancel a booking
    @DeleteMapping("/cancel/{bookingSerialNumber}")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingSerialNumber) {
        String response = bookingService.cancelBooking(bookingSerialNumber);
        return ResponseEntity.ok(response);
    }

    //find booking by
    @GetMapping("/find")
    public ResponseEntity<Booking> findBookingByPhoneNumberAndStatus(@RequestParam String customerPhoneNumber,
                                                                     @RequestParam ApartmentStatus status) {
        Booking booking = bookingService.findBookingByCustomerPhoneNumberAndApartmentStatus(customerPhoneNumber, status);
        return ResponseEntity.ok(booking);
    }

    //delete all bookings
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllBookings() {
        bookingService.deleteAll();
        return ResponseEntity.ok("All bookings have been deleted.");
    }
}
