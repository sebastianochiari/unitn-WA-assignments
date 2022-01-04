package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.SimpleStateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Remote(SimpleStateless.class)
public class SimpleStatelessBean implements SimpleStateless {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public String getValue(int i) {
        Query q = entityManager.createQuery("From ApartmentEntity where id = " + i);
        ApartmentEntity ae = (ApartmentEntity) q.getSingleResult();
        return ae.getApartmentName();
    }
}
