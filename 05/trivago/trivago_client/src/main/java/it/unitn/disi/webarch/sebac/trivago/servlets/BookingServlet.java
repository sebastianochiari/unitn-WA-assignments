package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("transactionStatus", null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodationType");
        String accommodationID = request.getParameter("accommodationID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String places = request.getParameter("guests");
        String halfBoard = request.getParameter("halfBoard");

        String creditCard = request.getParameter("creditCard");

        // update transaction status session variable
        HttpSession session = request.getSession();

        // call business delegate passing parameters
        boolean transactionStatus = (boolean) BusinessDelegate.getInstance().bookAccommodation(
                accommodationType, accommodationID, firstName, lastName, startDate, endDate, places, halfBoard
        );

        if(transactionStatus) {
            session.setAttribute("transactionStatus", "succeeded");
        } else {
            session.setAttribute("transactionStatus", "failed");
        }

        // redirect to index
        response.sendRedirect("/IndexServlet");
    }
}
