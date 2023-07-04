package com.SkBHousing.skbhousingapp.dtos.requests;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckOutRequest {
    private Long id;
    private Long userId;
    private Long apartmentId;
    private LocalDate dateOut;
    private LocalTime timeOut;
}
