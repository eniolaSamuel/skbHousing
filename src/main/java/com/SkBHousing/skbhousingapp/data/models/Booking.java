package com.SkBHousing.skbhousingapp.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long apartmentId;
    private String apartmentName;
    private String apartmentAddress;
    private HouseGeoLocation location;
    private HouseType apartmentType;
    private String userFullName;
    private String userPhoneNumber;
    private LocalDateTime startBookDate;
    private LocalDateTime endBookDate;

    private boolean isBooked = false;
}
