package it.unitn.disi.webarch.sebac.trivago.ejb.beans;

import it.unitn.disi.webarch.sebac.trivago.ejb.dao.*;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.BookingDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.*;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Booking;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.HotelAvailability;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.DateUtil;

import javax.annotation.Resource;
import javax.ejb.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Stateless
@Remote(Booking.class)
public class BookingService implements Booking {

    @Resource
    private SessionContext context;

    @EJB
    private ApartmentReservationDAO apartmentReservationDAO;
    @EJB
    private HotelReservationDAO hotelReservationDAO;
    @EJB
    private HotelAvailabilityDAO hotelAvailabilityDAO;
    @EJB
    private HotelAvailability hotelAvailabilityBean;
    @EJB
    private ApartmentDAO apartmentDAO;
    @EJB
    private HotelDAO hotelDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void bookAccommodation(BookingDTO bookingDTO) {
        int days = (new DateUtil()).getDays(bookingDTO.getStartDate(), bookingDTO.getEndDate());
        // APARTMENT case
        if(bookingDTO.getAccommodationType() == AccommodationType.APARTMENT) {
            // check apartment availability
            if(checkApartmentAvailability(bookingDTO.getAccommodationID(), bookingDTO.getStartDate(), bookingDTO.getEndDate())) {
                ApartmentEntity apartment = apartmentDAO.getByID(bookingDTO.getAccommodationID());
                // update DB apartment_reservation
                ApartmentReservationEntity are = new ApartmentReservationEntity(
                        apartment,
                        bookingDTO.getFirstname(),
                        bookingDTO.getLastname(),
                        bookingDTO.getStartDate(),
                        bookingDTO.getEndDate(),
                        (days * apartment.getPrice()) + apartment.getFinalCleaning()
                );
                apartmentReservationDAO.save(are);
            } else {
                // set rollback
                context.setRollbackOnly();
            }
        } else if(bookingDTO.getAccommodationType() == AccommodationType.HOTEL) { // HOTEL CASE
            // check hotel availability
            if(hotelAvailabilityBean.isHotelAvailable(bookingDTO.getAccommodationID(), bookingDTO.getStartDate(), bookingDTO.getEndDate(), bookingDTO.getGuests())) {
                HotelEntity hotel = hotelDAO.getByID(bookingDTO.getAccommodationID());
                // update DB hotel_reservation
                int dailyPrice = hotel.getPrice();
                if(bookingDTO.isExtra()) {
                    dailyPrice = dailyPrice + hotel.getExtraHalfboard();
                }
                int totalPrice = dailyPrice * bookingDTO.getGuests() * days;
                HotelReservationEntity hre = new HotelReservationEntity(
                        hotel,
                        bookingDTO.getFirstname(),
                        bookingDTO.getLastname(),
                        bookingDTO.getStartDate(),
                        bookingDTO.getEndDate(),
                        bookingDTO.getGuests(),
                        totalPrice
                );
                hotelReservationDAO.save(hre);
                // update DB hotel_availability
                for(LocalDate date = bookingDTO.getStartDate().toLocalDate(); date.isBefore(bookingDTO.getEndDate().toLocalDate().plusDays(1)); date = date.plusDays(1)) {
                    HotelAvailabilityEntity tmp = hotelAvailabilityDAO.getHotelAvailabilityByID(hotel.getId(), Date.valueOf(date));
                    if (tmp != null) {
                        int updatedPlaces = tmp.getPlacesAvailable() - bookingDTO.getGuests();
                        HotelAvailabilityEntityPK hotelAvailabilityID = new HotelAvailabilityEntityPK(Date.valueOf(date), hotel.getId());
                        hotelAvailabilityDAO.update(hotelAvailabilityID, updatedPlaces);
                    } else {
                        HotelAvailabilityEntity hae = new HotelAvailabilityEntity(Date.valueOf(date), hotel.getId(), hotel.getPlaces() - bookingDTO.getGuests());
                        hotelAvailabilityDAO.save(hae);
                    }
                }
            } else {
                // set rollback
                context.setRollbackOnly();
            }
        }
    }

    private boolean checkApartmentAvailability(int apartmentID, Date startDate, Date endDate) {
        boolean isAvailable = true;
        List<ApartmentReservationEntity> reservations = apartmentReservationDAO.getApartmentReservationByIDAndDates(apartmentDAO.getByID(apartmentID), startDate, endDate);
        if(!reservations.isEmpty()) {
            isAvailable = false;
        }
        return isAvailable;
    }

}
