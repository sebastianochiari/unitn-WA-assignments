package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.AccommodationType;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet",
        value = {"/CheckoutServlet", "/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodationType");
        String accommodationID = request.getParameter("accommodationID");

        // call business delegate passing parameters
        AccommodationDTO accommodation = BusinessDelegate.getInstance().retrieveCheckout(accommodationType, accommodationID);

        // set ServletContext variables
        getServletContext().setAttribute("accommodation", accommodation);

        // redirect with URL rewriting
        response.sendRedirect("/checkout");
    }
}
