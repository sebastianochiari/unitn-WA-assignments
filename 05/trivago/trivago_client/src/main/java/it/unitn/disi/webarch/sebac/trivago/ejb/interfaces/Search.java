package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;

import java.sql.Date;
import java.util.List;

public interface Search {
    List<AccommodationDTO> searchAll(int places, Date startDate, Date endDate);
    List<AccommodationDTO> searchApartments(int places, Date startDate, Date endDate);
    List<AccommodationDTO> searchHotels(int places, Date startDate, Date endDate);
}
