package com.SkBHousing.skbhousingapp.dtos.requests;

import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindApartmentByLocationRequest {
    private HouseGeoLocation houseGeoLocation;
}
