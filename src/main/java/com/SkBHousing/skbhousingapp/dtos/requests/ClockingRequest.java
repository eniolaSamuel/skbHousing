package com.SkBHousing.skbhousingapp.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonSerialize
public class ClockingRequest {
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
}
