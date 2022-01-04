package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentReservationEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelReservationEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Reservation;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(Reservation.class)
public class ReservationService implements Reservation {

    @EJB
    private ApartmentReservationDAO apartmentReservationDAO;
    @EJB
    private HotelReservationDAO hotelReservationDAO;

    @Override
    public List<ReservationDTO> viewReservations(String firstname, String lastname) {
        List<ReservationDTO> reservations = new ArrayList<>();
        // get list of reservations from apartments
        List<ApartmentReservationEntity> apartmentReservations = apartmentReservationDAO.getApartmentReservationsByGuestName(firstname, lastname);
        for(ApartmentReservationEntity reservation : apartmentReservations) {
            ReservationDTO reservationDTO = new ReservationDTO(
                    AccommodationType.APARTMENT,
                    reservation.getApartmentId().getApartmentName(),
                    0,
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getTotalPrice()
                    );
            reservations.add(reservationDTO);
        }
        // get list of reservations from hotels
        List<HotelReservationEntity> hotelReservations = hotelReservationDAO.getHotelReservationsByGuestName(firstname, lastname);
        for(HotelReservationEntity reservation : hotelReservations) {
            ReservationDTO reservationDTO = new ReservationDTO(
                    AccommodationType.HOTEL,
                    reservation.getHotelId().getHotelName(),
                    reservation.getPlacesReserved(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getTotalPrice()
            );
            reservations.add(reservationDTO);
        }

        return reservations;
    }
}
