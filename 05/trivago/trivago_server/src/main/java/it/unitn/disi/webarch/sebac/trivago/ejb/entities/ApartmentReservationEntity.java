package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "APARTMENT_RESERVATION", schema = "PUBLIC", catalog = "TRIVAGODB")
public class ApartmentReservationEntity {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reservationId;
    @ManyToOne
    @JoinColumn(name = "APARTMENT_ID", referencedColumnName = "ID")
    private ApartmentEntity apartmentId;
    @Basic
    @Column(name = "GUEST_FIRSTNAME")
    private String guestFirstname;
    @Basic
    @Column(name = "GUEST_LASTNAME")
    private String guestLastname;
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;
    @Basic
    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    public ApartmentReservationEntity() {}

    public ApartmentReservationEntity(ApartmentEntity apartmentId, String guestFirstname, String guestLastname, Date startDate, Date endDate, Integer totalPrice) {
        this.apartmentId = apartmentId;
        this.guestFirstname = guestFirstname;
        this.guestLastname = guestLastname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public ApartmentEntity getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentEntity apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Object getGuestFirstname() {
        return guestFirstname;
    }

    public void setGuestFirstname(String guestFirstname) {
        this.guestFirstname = guestFirstname;
    }

    public Object getGuestLastname() {
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
        ApartmentReservationEntity that = (ApartmentReservationEntity) o;
        return Objects.equals(reservationId, that.reservationId) && Objects.equals(apartmentId, that.apartmentId) && Objects.equals(guestFirstname, that.guestFirstname) && Objects.equals(guestLastname, that.guestLastname) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, apartmentId, guestFirstname, guestLastname, startDate, endDate, totalPrice);
    }
}
