package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

public interface Checkout {
    AccommodationDTO getCheckout(AccommodationType accommodationType, int accommodationID);
}
