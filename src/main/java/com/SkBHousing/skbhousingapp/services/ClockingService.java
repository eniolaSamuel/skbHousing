package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.utils.ApiResponse;

public interface ClockingService {

    ApiResponse saveClockHistory (String userPhoneNumber);
    ApiResponse clockIn (String userPhoneNumber) throws IllegalAccessException;

    ApiResponse clockOut (String  userPhoneNumber) throws IllegalAccessException;

}
