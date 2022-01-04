package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;

import java.util.List;

public interface Reservation {
    List<ReservationDTO> viewReservations(String firstname, String lastname);
}
