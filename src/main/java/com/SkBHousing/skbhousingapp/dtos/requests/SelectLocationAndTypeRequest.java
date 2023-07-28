package com.SkBHousing.skbhousingapp.dtos.requests;

import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import lombok.Data;

@Data
public class SelectLocationAndTypeRequest {
    private HouseGeoLocation location;
    private HouseType apartmentType;
}
