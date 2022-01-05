package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.DTOAssembler;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Checkout;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Checkout.class)
public class CheckoutBean implements Checkout {

    @EJB
    private ApartmentDAO apartmentDAO;
    @EJB
    private HotelDAO hotelDAO;

    @Override
    public AccommodationDTO getCheckout(AccommodationType accommodationType, int accommodationID) {
        AccommodationDTO accommodation = new AccommodationDTO();
        if(accommodationType == AccommodationType.APARTMENT) {
            ApartmentEntity apartment = apartmentDAO.getByID(accommodationID);
            accommodation = DTOAssembler.getInstance().createFromApartment(apartment);
        } else if(accommodationType == AccommodationType.HOTEL) {
            HotelEntity hotel = hotelDAO.getByID(accommodationID);
            accommodation = DTOAssembler.getInstance().createFromHotel(hotel, 0);
        }
        return accommodation;
    }

}
