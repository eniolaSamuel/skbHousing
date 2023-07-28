package com.SkBHousing.skbhousingapp.dtos.responses;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookedApartmentResponse {
    private String bookingSerialNumber;
    private String apartmentName;
    private HouseType apartmentType;
    private HouseGeoLocation apartmentLocation;
    private String apartmentAddress;
    private BigDecimal price;
    private String userFullName;
    private String userPhoneNumber;
    private LocalDate expectedDateOfArrival;
    private LocalDate expectedCheckingOutDate;
}

