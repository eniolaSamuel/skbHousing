package com.SkBHousing.skbhousingapp.dtos.requests;

import com.SkBHousing.skbhousingapp.data.models.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookApartmentRequest {
    private String bookingSerialNumber;
    private Apartment selectedApartment;
    private String userFullName;
    private String userPhoneNumber;
    private LocalDate startBookDate;
    private LocalDate endBookDate;
    private ApartmentStatus apartmentStatus;
    private Payment_Status paymentStatus;
}
