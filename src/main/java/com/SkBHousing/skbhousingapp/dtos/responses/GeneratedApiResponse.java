package com.SkBHousing.skbhousingapp.dtos.responses;

import org.springframework.http.HttpStatus;

public class GeneratedApiResponse {
    public static ApiResponse okResponse(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse badResponse(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }
}
