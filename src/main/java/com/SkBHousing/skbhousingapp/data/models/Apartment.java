package com.SkBHousing.skbhousingapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apartmentSerialNumber;
    private String apartmentName;
    @Enumerated(value = EnumType.STRING)
    private HouseGeoLocation location;
    @Enumerated(value = EnumType.STRING)
    private HouseType apartmentType;
    private String apartmentAddress;
    @Enumerated(value = EnumType.STRING)
    private ApartmentStatus apartmentStatus;
    private BigDecimal price;
}
