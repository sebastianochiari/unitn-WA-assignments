package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import it.unitn.disi.webarch.sebac.trivago.ServiceLocator;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.ReservationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.interfaces.Reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReservationServlet",
        value = {"/ReservationServlet", "/myreservations"})
public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/myreservations.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameter
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        ArrayList<ReservationDTO> reservations = (ArrayList<ReservationDTO>) BusinessDelegate.getInstance().retrieveReservationList(firstname, lastname);

        boolean thereAreReservations = true;
        if(reservations.isEmpty()) {
            thereAreReservations = false;
        }

        getServletContext().setAttribute("thereAreReservations", thereAreReservations);
        getServletContext().setAttribute("reservations", reservations);
        getServletContext().setAttribute("name", firstname + " " + lastname);

        // redirect with URL rewriting
        response.sendRedirect("/myreservations?firstname=" + firstname + "&lastname=" + lastname);
    }
}
