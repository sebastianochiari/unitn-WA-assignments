package it.unitn.disi.webarch.sebac.trivago.ejb.dto;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.sql.Date;

@Singleton
@Startup
public class DTOAssembler {

    private static DTOAssembler dtoAssembler = null;

    public static DTOAssembler getInstance() {
        if(dtoAssembler == null) {
            dtoAssembler = new DTOAssembler();
        }
        return dtoAssembler;
    }

    private DTOAssembler() {}

    public AccommodationDTO createAccommodationDTO(Object accommodation, int places) {
        AccommodationDTO accommodationDTO = new AccommodationDTO();
        if(accommodation instanceof ApartmentEntity) {
            accommodationDTO = createAccommodationFromApartment((ApartmentEntity) accommodation);
        } else if (accommodation instanceof HotelEntity) {
            accommodationDTO = createAccommodationFromHotel((HotelEntity) accommodation, places);
        }
        return accommodationDTO;
    }

    public BookingDTO createBookingDTO(AccommodationType accommodationType, int accommodationID, String firstname, String lastname, Date startDate, Date endDate, int guests, boolean extra) {
        return new BookingDTO(
                accommodationType,
                accommodationID,
                firstname,
                lastname,
                startDate,
                endDate,
                guests,
                extra
        );
    }

    public AccommodationDTO createAccommodationFromHotel(HotelEntity accommodation, int places) {
        return new AccommodationDTO(
                AccommodationType.HOTEL,
                accommodation.getId(),
                accommodation.getHotelName(),
                accommodation.getPrice(),
                accommodation.getExtraHalfboard(),
                accommodation.getStars(),
                places
        );
    }

    public AccommodationDTO createAccommodationFromApartment(ApartmentEntity accommodation) {
        return new AccommodationDTO(
                AccommodationType.APARTMENT,
                accommodation.getId(),
                accommodation.getApartmentName(),
                accommodation.getPrice(),
                accommodation.getFinalCleaning(),
                0,
                0
        );
    }

}
