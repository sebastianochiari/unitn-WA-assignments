package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelAvailabilityDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.DTOAssembler;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentReservationEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelAvailabilityEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.HotelAvailability;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Search;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Stateless
@Remote(Search.class)
public class SearchService implements Search {

    @EJB
    private ApartmentDAO apartmentDAO;
    @EJB
    private ApartmentReservationDAO apartmentReservationDAO;
    @EJB
    private HotelDAO hotelDAO;
    @EJB
    private HotelAvailabilityDAO hotelAvailabilityDAO;
    @EJB
    private HotelAvailability hotelAvailabilityBean;

    @Override
    public List<AccommodationDTO> searchAll(int places, Date startDate, Date endDate) {
        List<AccommodationDTO> availableAccommodations = new ArrayList<>();

        List<AccommodationDTO> availableApartments = searchApartments(places, startDate, endDate);
        List<AccommodationDTO> availableHotels = searchHotels(places, startDate, endDate);

        availableAccommodations.addAll(availableApartments);
        availableAccommodations.addAll(availableHotels);

        return availableAccommodations;
    }

    @Override
    public List<AccommodationDTO> searchApartments(int places, Date startDate, Date endDate) {
        List<AccommodationDTO> availableApartments = new ArrayList<>();
        List<ApartmentEntity> apartments = apartmentDAO.getApartmentByPlaces(places);
        // there are apartments satisfying number of guests
        if(!(apartments.isEmpty())) {
            for(ApartmentEntity apartment : apartments) {
                List<ApartmentReservationEntity> reservations = apartmentReservationDAO.getApartmentReservationByIDAndDates(apartment, startDate, endDate);
                if(reservations.isEmpty()) {
                    AccommodationDTO accommodation = DTOAssembler.getInstance().createAccommodationFromApartment(apartment);
                    availableApartments.add(accommodation);
                }
            }
        }
        return availableApartments;
    }

    @Override
    public List<AccommodationDTO> searchHotels(int places, Date startDate, Date endDate) {
        List<AccommodationDTO> availableHotels = new ArrayList<>();

        List<HotelEntity> hotels = hotelDAO.getAll();
        for(HotelEntity hotel : hotels) {
            boolean isHotelAvailable = hotelAvailabilityBean.isHotelAvailable(hotel.getId(), startDate, endDate, places);
            if(isHotelAvailable) {
                AccommodationDTO accommodation = DTOAssembler.getInstance().createAccommodationFromHotel(hotel, places);
                availableHotels.add(accommodation);
            }
        }

        return availableHotels;
    }
}
