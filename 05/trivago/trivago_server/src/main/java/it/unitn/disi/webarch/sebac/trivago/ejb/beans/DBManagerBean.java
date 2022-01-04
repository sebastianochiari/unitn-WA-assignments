package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.ApartmentReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.dao.HotelReservationDAO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentReservationEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelEntity;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.HotelReservationEntity;

import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

@Singleton
@Startup
public class DBManagerBean {

    public DBManagerBean() {}

    @EJB
    private ApartmentDAO apartmentDAO;
    @EJB
    private ApartmentReservationDAO apartmentReservationDAO;
    @EJB
    private HotelDAO hotelDAO;
    @EJB
    private HotelReservationDAO hotelReservationDAO;

    // @PostConstruct
    @Transactional
    void populate() {

        erase();

        // APARTMENTS SECTION

        List<ApartmentEntity> apartmentResults = apartmentDAO.getAll();
        HashMap<ApartmentEntity, Object> availability = new HashMap<>();
        // generate occupancy
        for(ApartmentEntity apartment : apartmentResults) {
            HashSet<Integer> days = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                int day;
                do {
                    day = (int) ((Math.random() * (29 - 1)) + 1);
                } while (!days.add(day));
            }
            availability.put(apartment, days);
        }
        // add reservations
        for(Map.Entry<ApartmentEntity, Object> entry : availability.entrySet()) {
            ApartmentEntity key = entry.getKey();
            HashSet<Integer> value = (HashSet<Integer>) entry.getValue();
            for(Integer element : value) {
                String day;
                if(element < 10) {
                    day = "0" + element;
                } else {
                    day = element.toString();
                }
                String stringDate = "2022-02-" + day;
                java.sql.Date date = Date.valueOf(stringDate);
                ApartmentReservationEntity are = new ApartmentReservationEntity(key, "Mock", "Guest", date, date, (key.getPrice() + key.getFinalCleaning()));
                apartmentReservationDAO.save(are);
            }
        }

        // HOTELS SECTION

        List<HotelEntity> hotelResults = hotelDAO.getAll();
        for(HotelEntity hotel : hotelResults) {
            int places  = hotel.getPlaces();
            for(int i = 1; i < 29; i++) {
                // date
                String day;
                if(i < 10) {
                    day = "0" + i;
                } else {
                    day = "" + i;
                }
                String stringDate = "2022-02-" + day;
                java.sql.Date date = Date.valueOf(stringDate);
                // occupancy
                int occupancy_percentage = (int) ((Math.random() * (101 - 90)) + 90);
                int reserved_places = (int) Math.ceil((double) (places * occupancy_percentage / 100));
                HotelReservationEntity hre = new HotelReservationEntity(hotel, "Mock", "Guest", date, date, reserved_places, 0);
                hotelReservationDAO.save(hre);
            }
        }

    }

    @Transactional
    void erase() {
        apartmentReservationDAO.deleteAll();
        hotelReservationDAO.deleteAll();
    }
}
