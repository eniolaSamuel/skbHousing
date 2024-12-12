package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.services.ClockingService;
import com.SkBHousing.skbhousingapp.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clocking")
@RequiredArgsConstructor

public class ClockingController {

    private final ClockingService clockingService;
    private static final Logger logger = LoggerFactory.getLogger(ClockingController.class);

/**
 * Endpoint for clocking in a user.
 *
 * @param userPhoneNumber Phone number of the user attempting to clock in.
 * @return ApiResponse indicating the result of the clock-in attempt.
 */

@PostMapping("/clock-in")
public ApiResponse clockIn(@RequestParam String userPhoneNumber) {
    logger.info("Received clock-in request for phone number: {}", userPhoneNumber);
    try {
        return clockingService.clockIn(userPhoneNumber);
    } catch (Exception e) {
        logger.error("Error during clock-in for phone number: {}", userPhoneNumber, e);
        return ApiResponse.errorResponse("Clock-in failed: " + e.getMessage());
    }
}