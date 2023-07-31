package com.SkBHousing.skbhousingapp.dtos.requests;

import com.SkBHousing.skbhousingapp.data.models.HouseType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindApartmentByTypeRequest {
        private HouseType houseType;
        }
