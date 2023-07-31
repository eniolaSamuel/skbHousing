package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingSerialNumber(String bookingSerialNumber);

    Booking findByUserPhoneNumberAndApartmentStatus (String  userPhoneNumber, ApartmentStatus apartmentStatus);
}
