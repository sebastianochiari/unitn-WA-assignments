package it.unitn.disi.webarch.sebac.trivago.ejb.dao;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelEntity;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@LocalBean
public class HotelDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(HotelEntity hotel) {
        entityManager.persist(hotel);
    }

    public HotelEntity getByID(int id) {
        return entityManager.find(HotelEntity.class, id);
    }

    public List<HotelEntity> getAll() {
        return entityManager.createQuery("From HotelEntity", HotelEntity.class).getResultList();
    }

}
