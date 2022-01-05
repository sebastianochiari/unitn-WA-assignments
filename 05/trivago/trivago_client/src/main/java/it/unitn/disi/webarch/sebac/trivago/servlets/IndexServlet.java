package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
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
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().setAttribute("searchPerformed", false);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodation-type");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String guests = request.getParameter("guests");

        // call business delegate passing parameters
        ArrayList<AccommodationDTO> accommodations = (ArrayList<AccommodationDTO>)
                BusinessDelegate.getInstance().retrieveAccommodations(accommodationType, startDate, endDate, guests);

        // set servlet context variables
        getServletContext().setAttribute("searchPerformed", true);
        getServletContext().setAttribute("accommodations", accommodations);

        getServletContext().setAttribute("guests", guests);
        getServletContext().setAttribute("startDate", startDate);
        getServletContext().setAttribute("endDate", endDate);

        // redirect to index
        response.sendRedirect("/index.jsp");
    }
}
