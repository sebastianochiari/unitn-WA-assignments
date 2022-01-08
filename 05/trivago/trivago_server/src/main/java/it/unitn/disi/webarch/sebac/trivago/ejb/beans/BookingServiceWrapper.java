package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.BookingDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Booking;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.BookingWrapper;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
@Remote(BookingWrapper.class)
public class BookingServiceWrapper implements BookingWrapper {

    @EJB
    private Booking bookingService;

    @Override
    @Transactional
    public boolean bookingServiceWrapper(BookingDTO bookingDTO) {
        boolean transactionSucceeded = true;
        try {
            bookingService.bookAccommodation(bookingDTO);
        } catch (RuntimeException e) {
            transactionSucceeded = false;
        }
        return transactionSucceeded;
    }

}
