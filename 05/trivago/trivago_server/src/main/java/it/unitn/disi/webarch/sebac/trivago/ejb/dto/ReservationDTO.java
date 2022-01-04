package it.unitn.disi.webarch.sebac.trivago.ejb.dto;

import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import java.io.Serializable;
import java.sql.Date;

public class ReservationDTO implements Serializable {
    private AccommodationType accommodationType;
    private String accommodationName;
    private int places_reserved;
    private Date startDate;
    private Date endDate;
    private int total_price;

    public ReservationDTO() {}

    public ReservationDTO(AccommodationType accommodationType, String accommodationName, int places_reserved, Date startDate, Date endDate, int total_price) {
        this.accommodationType = accommodationType;
        this.accommodationName = accommodationName;
        this.places_reserved = places_reserved;
        this.startDate = startDate;
        this.endDate = endDate;
        this.total_price = total_price;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accomodationType) {
        this.accommodationType = accomodationType;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accomodationName) {
        this.accommodationName = accomodationName;
    }

    public int getPlaces_reserved() {
        return places_reserved;
    }

    public void setPlaces_reserved(int places_reserved) {
        this.places_reserved = places_reserved;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
