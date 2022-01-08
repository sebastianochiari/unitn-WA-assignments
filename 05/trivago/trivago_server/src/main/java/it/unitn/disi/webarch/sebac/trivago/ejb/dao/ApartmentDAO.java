package it.unitn.disi.webarch.sebac.trivago.ejb.dao;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class ApartmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void save(ApartmentEntity apartment) {
        entityManager.persist(apartment);
    }

    public ApartmentEntity getByID(int id) {
        return entityManager.find(ApartmentEntity.class, id);
    }

    public List<ApartmentEntity> getAll() {
        return entityManager.createQuery("From ApartmentEntity", ApartmentEntity.class).getResultList();
    }

    public List<ApartmentEntity> getApartmentByPlaces(int places) {
        return entityManager.createQuery("Select ae From ApartmentEntity ae where ae.maxPersons >= :places")
                .setParameter("places", places).getResultList();
    }
}
