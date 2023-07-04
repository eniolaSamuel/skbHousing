package com.SkBHousing.skbhousingapp.dtos.requests;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckInRequest {
    private Long id;
    private Long userId;
    private Long apartmentId;
    private LocalDate dateIn;
    private LocalTime timeIn;
}
