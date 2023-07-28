package com.SkBHousing.skbhousingapp.dtos.responses;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import lombok.Data;

import java.util.List;

@Data
public class SelectApartmentsResponse {
   private List<Apartment> availableApartment;
}
