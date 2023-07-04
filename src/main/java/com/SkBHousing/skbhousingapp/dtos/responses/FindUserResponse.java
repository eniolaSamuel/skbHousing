package com.SkBHousing.skbhousingapp.dtos.responses;

import lombok.Data;

@Data
public class FindUserResponse {
    private Long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;
}
