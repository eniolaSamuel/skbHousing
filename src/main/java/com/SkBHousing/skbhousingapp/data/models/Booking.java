package com.SkBHousing.skbhousingapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String apartmentName;
    private String apartmentAddress;
    private HouseGeoLocation location;
    private HouseType apartmentType;
    private String userFullName;
    private String userPhoneNumber;
    private LocalDate startBookDate;
    private LocalDate endBookDate;
    private String bookingSerialNumber;
    private String apartmentSerialNumber;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private ApartmentStatus apartmentStatus;
    @Enumerated(value = EnumType.STRING)
    private Payment_Status paymentStatus;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Clocking clocking;
}
