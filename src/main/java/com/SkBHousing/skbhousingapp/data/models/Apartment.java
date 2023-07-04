package com.SkBHousing.skbhousingapp.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apartmentName;
    private HouseGeoLocation location;
    private HouseType apartmentType;
    private String address;
    private boolean isBooked = false;
    private BigDecimal price;
    private String userFullName;
    private String userPhoneNumber;
    private LocalDate checkInTime;
    private LocalDate checkOutTime;
}
