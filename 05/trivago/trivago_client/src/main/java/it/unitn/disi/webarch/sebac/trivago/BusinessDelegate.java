package it.unitn.disi.webarch.sebac.trivago;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Reservation;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Search;

import java.sql.Date;
import java.util.List;

public class BusinessDelegate {

    private static BusinessDelegate businessDelegate = null;
    private String JNDIlocation;
    private String packagePath;

    public static synchronized BusinessDelegate getInstance() {
        if(businessDelegate == null) {
            businessDelegate = new BusinessDelegate();
        }
        return businessDelegate;
    }

    private BusinessDelegate() {
        this.JNDIlocation = "ejb:/trivago_server-1.0-SNAPSHOT/";
        this.packagePath ="!it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.";
    }

    private String createHandleKey(String beanString, String interfaceString) {
        return JNDIlocation + beanString + packagePath + interfaceString;
    }

    public List<ReservationDTO> retrieveReservationList(String firstname, String lastname) {
        String handleKey = createHandleKey("ReservationService", "Reservation");
        Reservation r = (Reservation) ServiceLocator.getInstance().getHandle(handleKey);
        return r.viewReservations(firstname, lastname);
    }

    public List<ApartmentEntity> retrieveAccommodations(String accommodationType, String startDate, String endDate, String guests) {
        // formatting
        Date checkIn = Date.valueOf(startDate);
        Date checkOut = Date.valueOf(endDate);
        Integer places = Integer.valueOf(guests);
        // setup handle
        String handleKey = createHandleKey("SearchService", "Search");
        Search s = (Search) ServiceLocator.getInstance().getHandle(handleKey);

        return s.searchApartments(places, checkIn, checkOut);
    }
}
