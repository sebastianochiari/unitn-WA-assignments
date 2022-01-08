package it.unitn.disi.webarch.sebac.trivago.ejb.dao;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelReservationEntity;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class HotelReservationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void save(HotelReservationEntity hotelReservation) {
        entityManager.persist(hotelReservation);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void deleteAll() {
        entityManager.createQuery("Delete from HotelReservationEntity WHERE reservationId is not null").executeUpdate();
    }

    public List<HotelReservationEntity> getHotelReservationsByGuestName(String firstname, String lastname) {
        return entityManager.createQuery("select hre From HotelReservationEntity hre where hre.guestFirstname = :firstname and hre.guestLastname = :lastname")
                .setParameter("firstname", firstname).setParameter("lastname", lastname).getResultList();
    }
}
