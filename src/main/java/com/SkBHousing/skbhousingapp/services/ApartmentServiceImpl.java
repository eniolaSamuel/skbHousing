package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Apartment;
import com.SkBHousing.skbhousingapp.data.models.ApartmentStatus;
import com.SkBHousing.skbhousingapp.data.models.HouseGeoLocation;
import com.SkBHousing.skbhousingapp.data.models.HouseType;
import com.SkBHousing.skbhousingapp.data.repositories.ApartmentRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.ApartmentRegisterRequest;
import com.SkBHousing.skbhousingapp.dtos.requests.SelectLocationAndTypeRequest;
import com.SkBHousing.skbhousingapp.dtos.responses.SelectApartmentsResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public List<Apartment> viewAllApartmentByHouseType(HouseType apartmentType) throws IllegalAccessException {
        boolean validApartmentType = apartmentType.equals(HouseType.EXECUTIVE_STUDIO)
                || apartmentType.equals(HouseType.STANDARD_STUDIO)
                || apartmentType.equals(HouseType.ONE_BDR_FLAT)
                || apartmentType.equals(HouseType.TWO_BDR_FLAT)
                || apartmentType.equals(HouseType.THREE_BDR_FLAT)
                || apartmentType.equals(HouseType.FOUR_BDR_FLAT)
                || apartmentType.equals(HouseType.PARTY_HOUSE);
        if (!validApartmentType)
            throw new IllegalAccessException("Apartment type selected not available, input valid apartment type");
        return (apartmentRepository.findAllByApartmentType(apartmentType));
    }

    @Override
    public List<Apartment> viewAllApartmentByHouseGeoLocation(HouseGeoLocation location) throws IllegalAccessException {
        boolean validHouseGeoLocation = location.equals(HouseGeoLocation.LAGOS_ISLAND)
                || location.equals(HouseGeoLocation.LAGOS_MAINLAND);
        if (!validHouseGeoLocation)
            throw new IllegalAccessException("Geographic location not available, try again.");
        return (apartmentRepository.findAllByLocation(location));
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
    public void deleteAll() {
        apartmentRepository.deleteAll();
    }
}
