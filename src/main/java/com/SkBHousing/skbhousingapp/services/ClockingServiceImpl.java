package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.*;
import com.SkBHousing.skbhousingapp.data.repositories.ClockingRepository;
import com.SkBHousing.skbhousingapp.utils.ApiResponse;
import com.SkBHousing.skbhousingapp.utils.GeneratedApiResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ClockingServiceImpl implements ClockingService {

    private final BookingService bookingService;
    private final ClockingRepository clockingRepository;
    private final ApartmentService apartmentService;

    private static final Logger logger = LoggerFactory.getLogger(ClockingServiceImpl.class);

    @Override
    public ApiResponse saveClockHistory(String userPhoneNumber) {
        // Future implementation for fetching clocking history
        return GeneratedApiResponse.okResponse("Feature not implemented yet");
    }

    @Override
    public ApiResponse clockIn(String userPhoneNumber) throws IllegalAccessException {
        logger.info("Attempting to clock in user with phone number: {}", userPhoneNumber);

        Booking foundBookedSession = bookingService.findBookingByCustomerPhoneNumberAndApartmentStatus(
                userPhoneNumber, ApartmentStatus.IS_BOOKED);

        if (foundBookedSession == null) {
            logger.error("No booking session found for phone number: {}", userPhoneNumber);
            throw new InvalidBookingException("No booking session found");
        }

        if (foundBookedSession.getPaymentStatus().equals(Payment_Status.PENDING)) {
            logger.error("Payment pending for booking with phone number: {}", userPhoneNumber);
            throw new PaymentPendingException("Please make payment first");
        }

        Clocking savedClockRecord = buildClockInRecord(userPhoneNumber);
        foundBookedSession.setClocking(savedClockRecord);
        bookingService.save(foundBookedSession);

        logger.info("Successfully clocked in user with phone number: {}", userPhoneNumber);
        return GeneratedApiResponse.okResponse("Successfully clocked in");
    }

    @Override
    public ApiResponse clockOut(String userPhoneNumber) throws IllegalAccessException {
        logger.info("Attempting to clock out user with phone number: {}", userPhoneNumber);

        Booking foundBookedSession = bookingService.findBookingByCustomerPhoneNumberAndApartmentStatus(
                userPhoneNumber, ApartmentStatus.IS_BOOKED);

        if (foundBookedSession == null || foundBookedSession.getClocking() == null) {
            logger.error("Cannot proceed with clock out for phone number: {}", userPhoneNumber);
            throw new IllegalAccessException("No active clock-in record found for clock-out");
        }

        Clocking savedClockRecord = buildClockOutRecord(userPhoneNumber);
        foundBookedSession.setClocking(savedClockRecord);
        foundBookedSession.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);

        Apartment findBookedApartment = apartmentService.viewApartmentByApartmentSerialNumber(
                foundBookedSession.getApartmentSerialNumber());
        findBookedApartment.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);

        apartmentService.save(findBookedApartment);
        bookingService.save(foundBookedSession);

        logger.info("Successfully clocked out user with phone number: {}", userPhoneNumber);
        return GeneratedApiResponse.okResponse("Successfully clocked out");
    }

    private Clocking buildClockInRecord(String customerPhoneNumber) {
        Clocking clocking = new Clocking();
        clocking.setClockIn(LocalDateTime.now());
        clocking.setPhoneNumber(customerPhoneNumber);
        clocking.setClockingStatus(Clocking_Status.IN_PROGRESS);
        logger.info("Creating clock-in record for phone number: {}", customerPhoneNumber);
        return clockingRepository.save(clocking);
    }

    private Clocking buildClockOutRecord(String customerPhoneNumber) {
        Clocking clocking = clockingRepository.findByPhoneNumberAndClockingStatus(customerPhoneNumber, Clocking_Status.IN_PROGRESS);

        if (clocking == null) {
            logger.error("No active clock-in record found for phone number: {}", customerPhoneNumber);
            throw new IllegalStateException("No active clock-in record found for clock-out.");
        }

        clocking.setClockOut(LocalDateTime.now());
        clocking.setClockingStatus(Clocking_Status.COMPLETED);
        logger.info("Creating clock-out record for phone number: {}", customerPhoneNumber);
        return clockingRepository.save(clocking);
    }


    public static class InvalidBookingException extends RuntimeException {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    public static class PaymentPendingException extends RuntimeException {
        public PaymentPendingException(String message) {
            super(message);
        }
    }
}