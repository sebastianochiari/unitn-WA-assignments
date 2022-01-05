package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import java.sql.Date;
import java.util.Objects;

public class HotelAvailabilityEntity {
    private Date date;
    private int hotelId;
    private Integer placesAvailable;

    public HotelAvailabilityEntity() {}

    public HotelAvailabilityEntity(Date date, int hotelId, Integer placesAvailable) {
        this.date = date;
        this.hotelId = hotelId;
        this.placesAvailable = placesAvailable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getPlacesAvailable() {
        return placesAvailable;
    }

    public void setPlacesAvailable(Integer placesAvailable) {
        this.placesAvailable = placesAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelAvailabilityEntity that = (HotelAvailabilityEntity) o;
        return hotelId == that.hotelId && Objects.equals(date, that.date) && Objects.equals(placesAvailable, that.placesAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hotelId, placesAvailable);
    }
}
