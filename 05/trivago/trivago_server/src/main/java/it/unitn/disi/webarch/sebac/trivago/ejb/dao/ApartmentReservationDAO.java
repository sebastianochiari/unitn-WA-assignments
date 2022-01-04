package it.unitn.disi.webarch.sebac.trivago.ejb.dao;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentReservationEntity;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Singleton
public class ApartmentReservationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ApartmentReservationEntity apartmentReservation) {
        entityManager.persist(apartmentReservation);
    }

    // TODO: is needed?
    public void deleteAll() {
        entityManager.createQuery("Delete from ApartmentReservationEntity WHERE reservationId is not null").executeUpdate();
    }

    public List<ApartmentReservationEntity> getApartmentReservationsByGuestName(String firstname, String lastname) {
        return entityManager.createQuery("select are From ApartmentReservationEntity are where are.guestFirstname = :firstname and are.guestLastname = :lastname")
                .setParameter("firstname", firstname).setParameter("lastname", lastname).getResultList();
    }

    public List<ApartmentReservationEntity> getApartmentReservationByIDAndDates(ApartmentEntity apartment, Date startDate, Date endDate) {
        return entityManager.createQuery("select are from ApartmentReservationEntity are where are.apartmentId = :apartment and are.startDate >= :startDate and are.endDate <= :endDate")
                .setParameter("apartment", apartment).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
    }

}
