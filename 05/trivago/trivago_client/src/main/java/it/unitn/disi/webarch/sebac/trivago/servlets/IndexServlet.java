package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.entities.ApartmentEntity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodation-type");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String guests = request.getParameter("guests");

        // call business delegate passing parameters
        ArrayList<ApartmentEntity> accommodations = (ArrayList<ApartmentEntity>)
                BusinessDelegate.getInstance().retrieveAccommodations(accommodationType, startDate, endDate, guests);

        if(accommodations.isEmpty()) {
            System.out.println("Non ci sono appartamenti");
        }

        for(ApartmentEntity accommodation : accommodations) {
            System.out.println(accommodation.getApartmentName());
        }

        // set servlet context variables

        // redirect to index

    }
}
