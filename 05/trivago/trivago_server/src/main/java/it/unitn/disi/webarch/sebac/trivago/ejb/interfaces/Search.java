package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;

import java.sql.Date;
import java.util.List;

public interface Search {
    void searchAll(int places, Date startDate, Date endDate);
    List<ApartmentEntity> searchApartments(Integer places, Date startDate, Date endDate);
    void searchHotels(int places, Date startDate, Date endDate);
}
