package com.SkBHousing.skbhousingapp.dtos.requests;

import lombok.Data;

@Data
public class RegisterAdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
