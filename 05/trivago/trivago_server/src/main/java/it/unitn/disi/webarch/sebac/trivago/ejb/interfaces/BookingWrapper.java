package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.BookingDTO;

public interface BookingWrapper {
    boolean bookingServiceWrapper(BookingDTO bookingDTO);
}
