package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelAvailabilityDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelAvailabilityEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.HotelAvailability;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Date;
import java.time.LocalDate;

@Stateless
@Remote(HotelAvailability.class)
public class HotelAvailabilityBean implements HotelAvailability {

    @EJB
    private HotelAvailabilityDAO hotelAvailabilityDAO;

    public HotelAvailabilityBean() {}

    @Override
    public boolean isHotelAvailable(int hotelID, Date startDate, Date endDate, int places) {
        boolean isHotelAvailable = true;

        for(LocalDate date = startDate.toLocalDate(); date.isBefore(endDate.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
            HotelAvailabilityEntity tmp = hotelAvailabilityDAO.getHotelAvailabilityByID(hotelID, Date.valueOf(date));
            System.out.println("\t\t" + tmp);
            if(tmp != null) {
                if(tmp.getPlacesAvailable() < places) {
                    isHotelAvailable = false;
                }
            }
        }

        return isHotelAvailable;
    }

}
