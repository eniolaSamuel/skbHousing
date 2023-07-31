package com.SkBHousing.skbhousingapp.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Clocking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clockingId;
    private String phoneNumber;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    @Enumerated(EnumType.STRING)
    private Clocking_Status clockingStatus;
}
