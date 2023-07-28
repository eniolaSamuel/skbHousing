package com.SkBHousing.skbhousingapp.dtos.requests;

import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApartmentRegisterRequest {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private HouseGeoLocation location;
    @Enumerated(value = EnumType.STRING)
    private HouseType ApartmentType;
    private String address;
    @Enumerated(value = EnumType.STRING)
    private ApartmentStatus apartmentStatus;
    private BigDecimal price;
}
