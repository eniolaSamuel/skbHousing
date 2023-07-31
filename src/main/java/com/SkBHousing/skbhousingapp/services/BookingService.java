package com.SkBHousing.skbhousingapp.services;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;

import java.util.List;

public interface BookingService {
    Booking save(Booking booking);
    String cancelBooking(String bookingSerialNumber);
    List<Booking> viewAllBookings();
    Booking viewBookingByBookingSerialNumber(String bookingSerialNumber);
    BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest);
    String generateBookingSerialNumber();

    Booking findBookingByCustomerPhoneNumberAndApartmentStatus (String customerPhoneNumber, ApartmentStatus isBooked);

    void deleteAll();
}
