package it.unitn.disi.webarch.sebac.trivago.ejb.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "APARTMENT", schema = "PUBLIC", catalog = "TRIVAGODB")
public class ApartmentEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "APARTMENT_NAME")
    private String apartmentName;
    @Basic
    @Column(name = "PRICE")
    private Integer price;
    @Basic
    @Column(name = "FINAL_CLEANING")
    private Integer finalCleaning;
    @Basic
    @Column(name = "MAX_PERSONS")
    private Integer maxPersons;

    public ApartmentEntity() {}

    public ApartmentEntity(String apartmentName, Integer price, Integer finalCleaning, Integer maxPersons) {
        this.apartmentName = apartmentName;
        this.price = price;
        this.finalCleaning = finalCleaning;
        this.maxPersons = maxPersons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFinalCleaning() {
        return finalCleaning;
    }

    public void setFinalCleaning(Integer finalCleaning) {
        this.finalCleaning = finalCleaning;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentEntity that = (ApartmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(apartmentName, that.apartmentName) && Objects.equals(price, that.price) && Objects.equals(finalCleaning, that.finalCleaning) && Objects.equals(maxPersons, that.maxPersons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apartmentName, price, finalCleaning, maxPersons);
    }
}
