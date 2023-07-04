package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookingId(Long bookingId);
    List<Booking> findAllByBookingIdAndUserFullName(Long bookingId, String userId);
}
