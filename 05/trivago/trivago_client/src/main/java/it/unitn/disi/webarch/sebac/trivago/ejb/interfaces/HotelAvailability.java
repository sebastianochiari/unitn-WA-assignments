package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import java.sql.Date;

public interface HotelAvailability {
    boolean isHotelAvailable(int hotelID, Date startDate, Date endDate, int places);
}
