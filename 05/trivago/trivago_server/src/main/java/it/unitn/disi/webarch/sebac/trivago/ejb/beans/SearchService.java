package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentReservationEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Apartment;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Search;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
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

    @Override
    public void searchAll(int places, Date startDate, Date endDate) {

    }

    @Override
    public List<ApartmentEntity> searchApartments(Integer places, Date startDate, Date endDate) {
        List<ApartmentEntity> availableApartments = new ArrayList<>();
        List<ApartmentEntity> apartments = apartmentDAO.getApartmentByPlaces(places);
        System.out.println("Retrieved apartments based on places: size " + apartments.size());
        // there are apartments satisfying number of guests
        if(!(apartments.isEmpty())) {
            for(ApartmentEntity apartment : apartments) {
                System.out.println("\tLooking for apartment " + apartment.getApartmentName());
                List<ApartmentReservationEntity> reservations = apartmentReservationDAO.getApartmentReservationByIDAndDates(apartment, startDate, endDate);
                if(reservations.isEmpty()) {
                    System.out.println("\t\tAdded to available apartments");
                    availableApartments.add(apartment);
                } else {
                    System.out.println("\t\tNot available");
                }
            }
        }
        return availableApartments;
    }

    @Override
    public void searchHotels(int places, Date startDate, Date endDate) {

    }
}
