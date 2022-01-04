package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import java.sql.Date;
import java.util.Objects;

public class HotelReservationEntity {
    private Integer reservationId;
    private HotelEntity hotelId;
    private String guestFirstname;
    private String guestLastname;
    private Date startDate;
    private Date endDate;
    private Integer placesReserved;
    private Integer totalPrice;

    public HotelReservationEntity() {}

    public HotelReservationEntity(HotelEntity hotelId, String guestFirstname, String guestLastname, Date startDate, Date endDate, Integer placesReserved, Integer totalPrice) {
        this.hotelId = hotelId;
        this.guestFirstname = guestFirstname;
        this.guestLastname = guestLastname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placesReserved = placesReserved;
        this.totalPrice = totalPrice;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public HotelEntity getHotelId() {
        return hotelId;
    }

    public void setHotelId(HotelEntity hotelId) {
        this.hotelId = hotelId;
    }

    public String getGuestFirstname() {
        return guestFirstname;
    }

    public void setGuestFirstname(String guestFirstname) {
        this.guestFirstname = guestFirstname;
    }

    public String getGuestLastname() {
        return guestLastname;
    }

    public void setGuestLastname(String guestLastname) {
        this.guestLastname = guestLastname;
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

    public Integer getPlacesReserved() {
        return placesReserved;
    }

    public void setPlacesReserved(Integer placesReserved) {
        this.placesReserved = placesReserved;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelReservationEntity that = (HotelReservationEntity) o;
        return hotelId == that.hotelId && Objects.equals(reservationId, that.reservationId) && Objects.equals(guestFirstname, that.guestFirstname) && Objects.equals(guestLastname, that.guestLastname) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(placesReserved, that.placesReserved) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, hotelId, guestFirstname, guestLastname, startDate, endDate, placesReserved, totalPrice);
    }
}
