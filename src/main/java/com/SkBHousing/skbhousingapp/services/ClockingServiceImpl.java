package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.data.repositories.ClockingRepository;
import com.SkBHousing.skbhousingapp.utils.ApiResponse;
import com.SkBHousing.skbhousingapp.utils.GeneratedApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class ClockingServiceImpl implements ClockingService{

    private final BookingService bookingService;
    private final ClockingRepository clockingRepository;
    private final ApartmentService apartmentService;
    @Override
    public ApiResponse saveClockHistory(String userPhoneNumber) {
        return null;
    }

    @Override
    public ApiResponse clockIn(String userPhoneNumber) throws IllegalAccessException {

        Booking foundBookedSession = bookingService.findBookingByCustomerPhoneNumberAndApartmentStatus(
                userPhoneNumber, ApartmentStatus.IS_BOOKED);

        if (foundBookedSession == null ){throw new NullPointerException("No booking session found");}
        if (foundBookedSession.getPaymentStatus().equals(Payment_Status.PENDING)) {
            throw new IllegalAccessException("Please make payment first");
        }
        Clocking savedClockRecord = buildClockInRecord(userPhoneNumber);
        foundBookedSession.setClocking(savedClockRecord);
        bookingService.save(foundBookedSession);

        return GeneratedApiResponse.okResponse("Successfully clocked In");
    }

    @Override
    public ApiResponse clockOut(String userPhoneNumber) throws IllegalAccessException {
        Booking foundBookedSession = bookingService.findBookingByCustomerPhoneNumberAndApartmentStatus(
                userPhoneNumber, ApartmentStatus.IS_BOOKED);
        if (foundBookedSession.getClocking()==null){
            throw new IllegalAccessException("Cannot proceed with clock out");
        }
        Clocking savedClockRecord = buildClockOutRecord(userPhoneNumber);
        foundBookedSession.setClocking(savedClockRecord);
        foundBookedSession.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        Apartment findBookedApartment = apartmentService.
                viewApartmentByApartmentSerialNumber(foundBookedSession.getApartmentSerialNumber());
        findBookedApartment.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        apartmentService.save(findBookedApartment);
        bookingService.save(foundBookedSession);

        return GeneratedApiResponse.okResponse("Successfully clocked out");
    }

    private Clocking buildClockInRecord(String customerPhoneNumber){
        Clocking clocking = new Clocking();
        clocking.setClockIn(LocalDateTime.now());
        clocking.setPhoneNumber(customerPhoneNumber);
        clocking.setClockingStatus(Clocking_Status.IN_PROGRESS);
        return clockingRepository.save(clocking);
    }

    private Clocking buildClockOutRecord(String customerPhoneNumber){
        Clocking clocking = new Clocking();
        clocking.setClockOut(LocalDateTime.now());
        clocking.setClockingStatus(Clocking_Status.COMPLETED);
        return clockingRepository.save(clocking);
    }
}
