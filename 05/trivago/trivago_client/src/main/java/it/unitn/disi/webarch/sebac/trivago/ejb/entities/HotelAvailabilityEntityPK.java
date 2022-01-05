package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class HotelAvailabilityEntityPK implements Serializable {

    private Date date;
    private int hotelId;

    public HotelAvailabilityEntityPK() {}

    public HotelAvailabilityEntityPK(Date date, int hotelId) {
        this.date = date;
        this.hotelId = hotelId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelAvailabilityEntityPK that = (HotelAvailabilityEntityPK) o;
        return hotelId == that.hotelId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, hotelId);
    }
}
