package it.unitn.disi.webarch.sebac.trivago.ejb.dao;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelAvailabilityEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelAvailabilityEntityPK;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

@Singleton
@LocalBean
public class HotelAvailabilityDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public HotelAvailabilityEntity getHotelAvailabilityByID(int hotelID, Date date) {
        HotelAvailabilityEntityPK hotelAvailabilityID = new HotelAvailabilityEntityPK(date, hotelID);
        return entityManager.find(HotelAvailabilityEntity.class, hotelAvailabilityID);
    }

    public void save(HotelAvailabilityEntity hotelAvailability) {
        entityManager.persist(hotelAvailability);
    }

    public void update(HotelAvailabilityEntityPK hotelAvailabilityID, int places) {
        entityManager.createQuery("UPDATE HotelAvailabilityEntity AS HAE SET HAE.placesAvailable = :places WHERE HAE.id = :id")
                .setParameter("places", places).setParameter("id", hotelAvailabilityID).executeUpdate();
    }

    public void deleteAll() {
        entityManager.createQuery("Delete from HotelAvailabilityEntity WHERE hotelId is not null").executeUpdate();
    }
}
