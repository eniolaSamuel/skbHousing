package com.SkBHousing.skbhousingapp.controllers;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.dtos.requests.*;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import com.SkBHousing.skbhousingapp.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerApartment(@RequestBody ApartmentRegisterRequest request) {
        try {
            String response = apartmentService.saveNewApartment(request);
            return ResponseEntity.ok(response);
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Apartment>> viewAllApartments() {
        return ResponseEntity.ok(apartmentService.viewAllApartments());
    }

    @PostMapping("/filter/type")
    public ResponseEntity<?> viewApartmentsByType(@RequestBody FindApartmentByTypeRequest request) {
        try {
            return ResponseEntity.ok(apartmentService.viewAllApartmentByHouseType(request));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/filter/location")
    public ResponseEntity<?> viewApartmentsByLocation(@RequestBody FindApartmentByLocationRequest request) {
        try {
            return ResponseEntity.ok(apartmentService.viewAllApartmentByHouseGeoLocation(request));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/filter/status")
    public ResponseEntity<?> viewApartmentsByStatus(@RequestBody ApartmentStatus status) {
        try {
            return ResponseEntity.ok(apartmentService.viewAllApartmentByApartmentStatus(status));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/filter/combined")
    public ResponseEntity<?> viewApartmentsByCombinedFilters(@RequestBody SelectLocationAndTypeRequest request) {
        try {
            SelectApartmentsResponse response = apartmentService.viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(request, ApartmentStatus.IS_AVAILABLE);
            return ResponseEntity.ok(response);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/name/{apartmentName}")
    public ResponseEntity<Apartment> findApartmentByName(@PathVariable String apartmentName) {
        return ResponseEntity.ok(apartmentService.findApartmentByName(apartmentName));
    }

    @GetMapping("/serial/{apartmentSerialNumber}")
    public ResponseEntity<Apartment> viewApartmentBySerialNumber(@PathVariable String apartmentSerialNumber) {
        return ResponseEntity.ok(apartmentService.viewApartmentByApartmentSerialNumber(apartmentSerialNumber));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable Long id) {
        String response = apartmentService.deleteApartment(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllApartments() {
        apartmentService.deleteAll();
        return ResponseEntity.ok("All apartments have been deleted.");
    }
}
