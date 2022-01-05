package it.unitn.disi.webarch.sebac.trivago.ejb.dto;

import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import java.io.Serializable;

public class AccommodationDTO implements Serializable {
    private AccommodationType accommodationType;
    private int accommodationID;
    private String accommodationName;
    private int price;
    private int extra;
    private int stars;
    private int places_reserved;

    public AccommodationDTO() {}

    public AccommodationDTO(AccommodationType accommodationType, int accommodationID, String accommodationName, int price, int extra, int stars, int places_reserved) {
        this.accommodationType = accommodationType;
        this.accommodationID = accommodationID;
        this.accommodationName = accommodationName;
        this.price = price;
        this.extra = extra;
        this.stars = stars;
        this.places_reserved = places_reserved;
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

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getPlaces_reserved() {
        return places_reserved;
    }

    public void setPlaces_reserved(int places_reserved) {
        this.places_reserved = places_reserved;
    }
}
