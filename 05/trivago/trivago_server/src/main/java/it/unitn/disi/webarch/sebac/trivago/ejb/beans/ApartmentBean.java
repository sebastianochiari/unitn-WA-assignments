package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Apartment;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Apartment.class)
public class ApartmentBean implements Apartment {

    @EJB
    private ApartmentDAO apartmentDAO;

    @Override
    public ApartmentEntity getApartment(int i) {
        ApartmentEntity apartment = apartmentDAO.getByID(i);
        return apartment;
    }

   @Override
   public int getTotalPrice(int i, int n_days) {
        ApartmentEntity apartment = getApartment(i);
        int totalPrice = (n_days * apartment.getPrice()) + apartment.getFinalCleaning();
        return totalPrice;
   }
}
