package it.unitn.disi.webarch.sebac.trivago.servlets;

import it.unitn.disi.webarch.sebac.trivago.BusinessDelegate;
import it.unitn.disi.webarch.sebac.trivago.ejb.dto.AccommodationDTO;
import it.unitn.disi.webarch.sebac.trivago.ejb.util.DateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("searchPerformed", false);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodation-type");
        String startDate = request.getParameter("start-date");
        String endDate = request.getParameter("end-date");
        String guests = request.getParameter("guests");

        int days = (new DateUtil()).getDays(Date.valueOf(startDate), Date.valueOf(endDate));

        // call business delegate passing parameters
        ArrayList<AccommodationDTO> accommodations = (ArrayList<AccommodationDTO>)
                BusinessDelegate.getInstance().retrieveAccommodations(accommodationType, startDate, endDate, guests);

        HttpSession session = request.getSession();

        session.setAttribute("searchPerformed", true);
        session.setAttribute("accommodations", accommodations);
        session.setAttribute("guests", guests);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        session.setAttribute("days", days);

        // redirect to index
        response.sendRedirect("/index.jsp");
    }
}
