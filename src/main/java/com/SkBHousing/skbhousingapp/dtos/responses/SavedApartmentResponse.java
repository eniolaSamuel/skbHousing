package com.SkBHousing.skbhousingapp.dtos.responses;

import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavedApartmentResponse {
    private Long id;
    private String name;
    private HouseGeoLocation location;
    private HouseType ApartmentType;
    private String address;
    private boolean isBooked = false;
    private BigDecimal price;
}