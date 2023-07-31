package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.ApartmentRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByLocationRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.FindApartmentByTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository ;
    private final ModelMapper modelMapper;


//    public ApartmentServiceImpl(@Autowired ApartmentRepository repository){
//        apartmentRepository = repository;
//    }

    @Override
    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public String deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
        return "Apartment with id " + id + "has been deleted";
    }

    @Override
    public String saveNewApartment(ApartmentRegisterRequest apartmentRegisterRequest) throws IllegalAccessException {
        apartmentRegisterRequest.setApartmentSerialNumber(generateApartmentSerialNumber());
       Apartment apartment = modelMapper.map(apartmentRegisterRequest , Apartment.class);
       Apartment apartmentInView = apartmentRepository.findApartmentByLocationAndApartmentTypeAndApartmentName
               (apartmentRegisterRequest.getLocation(), apartmentRegisterRequest.getApartmentType(), apartmentRegisterRequest.getName());
        if (apartmentInView != null)
            throw new IllegalAccessException("Apartment already exists");
        apartmentRegisterRequest.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);
        apartment.setApartmentStatus(ApartmentStatus.IS_AVAILABLE);

        apartmentRepository.save(apartment);
        return "Apartment Successfully Registered";
    }

    @Override
    public List<Apartment> viewAllApartments() {
        return (apartmentRepository.findAll());
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseType(FindApartmentByTypeRequest houseType) throws IllegalAccessException {
        boolean validApartmentType = houseType.getHouseType().equals(HouseType.EXECUTIVE_STUDIO)
                || houseType.getHouseType().equals(HouseType.STANDARD_STUDIO)
                || houseType.getHouseType().equals(HouseType.ONE_BDR_FLAT)
                || houseType.getHouseType().equals(HouseType.TWO_BDR_FLAT)
                || houseType.getHouseType().equals(HouseType.THREE_BDR_FLAT)
                || houseType.getHouseType().equals(HouseType.FOUR_BDR_FLAT)
                || houseType.getHouseType().equals(HouseType.PARTY_HOUSE);
        if (!validApartmentType)
            throw new IllegalAccessException("Apartment type selected not available, input valid apartment type");
        return (apartmentRepository.findAllByApartmentType(houseType.getHouseType()));
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseGeoLocation(FindApartmentByLocationRequest location) throws IllegalAccessException {
        boolean validHouseGeoLocation = location.getHouseGeoLocation().equals(HouseGeoLocation.LAGOS_ISLAND)
                || location.getHouseGeoLocation().equals(HouseGeoLocation.LAGOS_MAINLAND);
        if (!validHouseGeoLocation)
            throw new IllegalAccessException("Geographic location not available, try again.");
        return (apartmentRepository.findAllByLocation(location.getHouseGeoLocation()));
    }

    @Override
    public List<Apartment> viewAllApartmentByApartmentStatus(ApartmentStatus apartmentStatus) throws IllegalAccessException {
        boolean availableApartments = apartmentStatus.equals(ApartmentStatus.IS_AVAILABLE);
        if (!availableApartments) throw new IllegalAccessException("Apartment is booked at the moment");
        return (apartmentRepository.findApartmentByApartmentStatus(apartmentStatus));
    }

    @Override
    public SelectApartmentsResponse viewAllApartmentByHouseTypeAndHouseGeoLocationAndApartmentStatus(SelectLocationAndTypeRequest selectLocationAndTypeRequest, ApartmentStatus apartmentStatus) {
     List<Apartment> foundApartment =   apartmentRepository.findApartmentByLocationAndApartmentTypeAndApartmentStatus(selectLocationAndTypeRequest.getLocation()
                , selectLocationAndTypeRequest.getApartmentType(), ApartmentStatus.IS_AVAILABLE) ;
           if (foundApartment == null) throw new NullPointerException("No Apartment Matches Selection");

        SelectApartmentsResponse selectApartmentsResponse = new SelectApartmentsResponse();
        selectApartmentsResponse.setAvailableApartment(foundApartment);
        return selectApartmentsResponse;
    }

    @Override
    public Apartment findApartmentByName(String apartmentName) {
        return apartmentRepository.findByApartmentName(apartmentName);
    }

    @Override
    public String generateApartmentSerialNumber() {
        SecureRandom random = new SecureRandom();
        int apartmentSerialNumber = random.nextInt(1000, 9000);

        return "/00" + apartmentSerialNumber + "#";
    }

    @Override
    public Apartment viewApartmentByApartmentSerialNumber(String apartmentSerialNumber) {
        return apartmentRepository.findByApartmentSerialNumber(apartmentSerialNumber);
    }

    @Override
    public void deleteAll() {
        apartmentRepository.deleteAll();
    }
}
