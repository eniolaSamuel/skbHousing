package com.SkBHousing.skbhousingapp.data.repositories;

import com.SkBHousing.skbhousingapp.data.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByLocation(HouseGeoLocation location);

    List<Apartment> findAllByApartmentType(HouseType apartmentType);

    List<Apartment> findApartmentByLocationAndApartmentTypeAndApartmentStatus(HouseGeoLocation location, HouseType houseType, ApartmentStatus apartmentStatus);
    Apartment findApartmentByLocationAndApartmentTypeAndApartmentName(HouseGeoLocation location, HouseType houseType, String apartmentName);
    List<Apartment> findApartmentByApartmentStatus(ApartmentStatus apartmentStatus);
    List<Apartment> findApartmentByLocationAndApartmentType(HouseGeoLocation location, HouseType houseType);
    Apartment findByApartmentName(String apartmentName);
    Apartment findByApartmentSerialNumber(String apartmentSerialNumber);

}

