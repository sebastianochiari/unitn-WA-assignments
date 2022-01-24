package it.unitn.disi.webarch.sebac.trivago;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.BookingDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.DTOAssembler;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.BookingWrapper;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Checkout;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Reservation;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Search;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import java.sql.Date;
import java.util.ArrayList;
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

    public List<AccommodationDTO> retrieveAccommodations(String accommodationType, String startDate, String endDate, String guests) {
        // formatting
        Date checkIn = Date.valueOf(startDate);
        Date checkOut = Date.valueOf(endDate);
        Integer places = Integer.valueOf(guests);

        // setup handle
        String handleKey = createHandleKey("SearchService", "Search");
        Search s = (Search) ServiceLocator.getInstance().getHandle(handleKey);

        // retrieve accommodations
        List<AccommodationDTO> accommodations = new ArrayList<>();
        if(accommodationType.equals("All stays")) {
            accommodations = s.searchAll(places, checkIn, checkOut);
        } else if (accommodationType.equals("Hotel")) {
            accommodations = s.searchHotels(places, checkIn, checkOut);
        } else if (accommodationType.equals("Apartment")) {
            accommodations = s.searchApartments(places, checkIn, checkOut);
        }

        return accommodations;
    }

    public AccommodationDTO retrieveCheckout(String accommodationType, String accommodationID) {
        // formatting
        AccommodationType type = null;
        if(accommodationType.equals("APARTMENT")) {
            type = AccommodationType.APARTMENT;
        } else if(accommodationType.equals("HOTEL")) {
            type = AccommodationType.HOTEL;
        }
        Integer id = Integer.parseInt(accommodationID);

        // setup handle
        String handleKey = createHandleKey("CheckoutBean", "Checkout");
        Checkout c = (Checkout) ServiceLocator.getInstance().getHandle(handleKey);

        return c.getCheckout(type, id);
    }

    public boolean bookAccommodation(String accommodationType, String accommodationID, String firstname, String lastname, String startDate, String endDate, String guests, String extra) {
        // formatting
        AccommodationType type = null;
        boolean extraHalfBoard = false;
        if(accommodationType.equals("APARTMENT")) {
            type = AccommodationType.APARTMENT;
        } else if(accommodationType.equals("HOTEL")) {
            type = AccommodationType.HOTEL;
            if(extra.equals("yes")) {
                extraHalfBoard = true;
            }
        }

        // generate bookingDTO
        BookingDTO bookingDTO = DTOAssembler.getInstance().createBookingDTO(
                type,
                Integer.parseInt(accommodationID),
                firstname,
                lastname,
                Date.valueOf(startDate),
                Date.valueOf(endDate),
                Integer.parseInt(guests),
                extraHalfBoard
        );

        // setup handle
        String handleKey = createHandleKey("BookingServiceWrapper", "BookingWrapper");
        BookingWrapper c = (BookingWrapper) ServiceLocator.getInstance().getHandle(handleKey);

        return (boolean) c.bookingServiceWrapper(bookingDTO);
    }
}
