package it.unitn.disi.webarch.sebac.trivago.ejb.interfaces;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;

public interface Apartment {
    ApartmentEntity getApartment(int i);
    int getTotalPrice(int i, int n_days);
}
