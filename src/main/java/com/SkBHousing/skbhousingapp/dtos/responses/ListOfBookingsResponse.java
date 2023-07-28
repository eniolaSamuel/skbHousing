package com.SkBHousing.skbhousingapp.dtos.responses;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import lombok.Data;

import java.util.List;
@Data
public class ListOfBookingsResponse {
        private List<Booking> allBookings;
}
