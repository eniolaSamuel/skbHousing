package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.Booking;
import com.SkBHousing.skbhousingapp.data.models.Payment_Status;
import com.SkBHousing.skbhousingapp.data.repositories.BookingRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.BookApartmentRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.BookedApartmentResponse;
import com.SkBHousing.skbhousingapp.utils.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    public final ApartmentService apartmentService;
    private final BookingRepository bookingRepository;



    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public String cancelBooking(String bookingSerialNumber) {
        Booking foundBooking = bookingRepository.findByBookingSerialNumber(bookingSerialNumber);
        bookingRepository.delete(foundBooking);
        return "Booking with BookingId " + bookingSerialNumber + "has been deleted";
    }

    @Override
    public List<Booking> viewAllBookings(){
        return BookingMapper.listOfBookings(bookingRepository.findAll());
    }

    @Override
    public Booking viewBookingByBookingSerialNumber(String bookingSerialNumber) {
        return bookingRepository.findByBookingSerialNumber(bookingSerialNumber);
    }

    @Override
    public BookedApartmentResponse userBookApartment(BookApartmentRequest bookApartmentRequest)  {

        bookApartmentRequest.setBookingSerialNumber(generateBookingSerialNumber());
        Booking booking = BookingMapper.mapBookApartment(bookApartmentRequest);
        booking.setBookingSerialNumber(bookApartmentRequest.getBookingSerialNumber());
        booking.setApartmentStatus(ApartmentStatus.IS_BOOKED);
        Apartment chosenApartment = bookApartmentRequest.getSelectedApartment();
        booking.setApartmentName(chosenApartment.getApartmentName());
        booking.setApartmentType(chosenApartment.getApartmentType());
        booking.setApartmentAddress(chosenApartment.getApartmentAddress());
        booking.setLocation(chosenApartment.getLocation());
        booking.setPrice(calculatedPayment(bookApartmentRequest));
        booking.setPaymentStatus(Payment_Status.PENDING);
        chosenApartment.setApartmentStatus(ApartmentStatus.IS_BOOKED);
        bookingRepository.save(booking);
        apartmentService.save(chosenApartment);
        BookedApartmentResponse bookedApartmentResponse = new BookedApartmentResponse();
        BookingMapper.mapViewBookedApartment(booking, bookedApartmentResponse);
        return buildBookedApartmentResponse(bookApartmentRequest);
    }

    private BigDecimal calculatedPayment(BookApartmentRequest bookApartmentRequest) {
        BigDecimal originalDailyPrice = bookApartmentRequest.getSelectedApartment().getPrice();

        long numberOfDaysToBeSpent = ChronoUnit.DAYS.between(bookApartmentRequest.getEndBookDate(), bookApartmentRequest.getStartBookDate());

        BigDecimal totalPrice = originalDailyPrice.multiply(BigDecimal.valueOf(numberOfDaysToBeSpent));

        BigDecimal finalPrice;
        if (numberOfDaysToBeSpent > 4 ) {
          finalPrice = totalPrice.multiply(BigDecimal.valueOf(0.90));
        }
        else finalPrice = totalPrice;


        return finalPrice ;
    }

    public String generateBookingSerialNumber(){
        SecureRandom random = new SecureRandom();
        int bookingSerialNumber = random.nextInt(100000000, 900000000);

        return "#" + bookingSerialNumber;
    }

    @Override
    public void deleteAll() {
        bookingRepository.deleteAll();
    }

    public BookedApartmentResponse buildBookedApartmentResponse (BookApartmentRequest bookApartmentRequest){
        BookedApartmentResponse bookedApartmentResponse = new BookedApartmentResponse();
        bookedApartmentResponse.setBookingSerialNumber(bookApartmentRequest.getBookingSerialNumber());
        bookedApartmentResponse.setUserFullName(bookApartmentRequest.getUserFullName());
        bookedApartmentResponse.setExpectedDateOfArrival(bookApartmentRequest.getStartBookDate());
        bookedApartmentResponse.setExpectedDateOfArrival(bookApartmentRequest.getEndBookDate());
        bookedApartmentResponse.setApartmentLocation(bookApartmentRequest.getSelectedApartment().getLocation());
        bookedApartmentResponse.setApartmentName(bookApartmentRequest.getSelectedApartment().getApartmentName());
        bookedApartmentResponse.setApartmentType(bookApartmentRequest.getSelectedApartment().getApartmentType());
        bookedApartmentResponse.setApartmentAddress(bookApartmentRequest.getSelectedApartment().getApartmentAddress());
        bookedApartmentResponse.setPrice(calculatedPayment(bookApartmentRequest));
        return bookedApartmentResponse;
    }
}
