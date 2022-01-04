package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import java.util.Objects;

public class HotelEntity {
    private Integer id;
    private String hotelName;
    private Integer price;
    private Integer extraHalfboard;
    private Integer stars;
    private Integer places;

    public HotelEntity() {}

    public HotelEntity(String hotelName, Integer price, Integer extraHalfboard, Integer stars, Integer places) {
        this.hotelName = hotelName;
        this.price = price;
        this.extraHalfboard = extraHalfboard;
        this.stars = stars;
        this.places = places;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getExtraHalfboard() {
        return extraHalfboard;
    }

    public void setExtraHalfboard(Integer extraHalfboard) {
        this.extraHalfboard = extraHalfboard;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getPlaces() { return places; }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(hotelName, that.hotelName) && Objects.equals(price, that.price) && Objects.equals(extraHalfboard, that.extraHalfboard) && Objects.equals(stars, that.stars) && Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotelName, price, extraHalfboard, stars, places);
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", price=" + price +
                ", extraHalfboard=" + extraHalfboard +
                ", stars=" + stars +
                ", places=" + places +
                '}';
    }
}
