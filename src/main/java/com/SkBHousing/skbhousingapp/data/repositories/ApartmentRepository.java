package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByLocation(HouseGeoLocation location);

    List<Apartment> findAllByApartmentType(HouseType apartmentType);

    List<Apartment> findApartmentByLocationAndApartmentType(HouseGeoLocation location, HouseType houseType);
}

