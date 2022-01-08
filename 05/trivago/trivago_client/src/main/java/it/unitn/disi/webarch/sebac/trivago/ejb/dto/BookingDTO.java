package it.unitn.disi.webarch.sebac.trivago.ejb.dto;

import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import java.io.Serializable;
import java.sql.Date;

public class BookingDTO implements Serializable {
    private AccommodationType accommodationType;
    private int accommodationID;
    private String firstname;
    private String lastname;
    private Date startDate;
    private Date endDate;
    private int guests;
    private boolean extra;

    public BookingDTO() {}

    public BookingDTO(AccommodationType accommodationType, int accommodationID, String firstname, String lastname, Date startDate, Date endDate, int guests, boolean extra) {
        this.accommodationType = accommodationType;
        this.accommodationID = accommodationID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guests = guests;
        this.extra = extra;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public int getAccommodationID() {
        return accommodationID;
    }

    public void setAccommodationID(int accommodationID) {
        this.accommodationID = accommodationID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
