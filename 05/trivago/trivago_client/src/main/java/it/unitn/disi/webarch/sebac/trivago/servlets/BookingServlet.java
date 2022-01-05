package it.unitn.disi.webarch.sebac.trivago.servlets;

import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    private String operationSuccessful;

    @Override
    public void init() throws ServletException {
        this.operationSuccessful = "nothing";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("INSIDE BOOKING SERVLET doGET method");
        // create JSON object
        JSONObject returnJSON = new JSONObject();
        returnJSON.put("transactionStatus", this.operationSuccessful);
        // update value
        this.operationSuccessful = "nothing";
        // set response content type to be JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(returnJSON);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accommodationType = request.getParameter("accommodationType");
        String accommodationID = request.getParameter("accommodationID");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String places = request.getParameter("guests");
        String halfBoard = request.getParameter("halfBoard");

        String creditCard = request.getParameter("creditCard");

        // call business delegate passing parameters
        this.operationSuccessful = "success";

        // redirect to index
        response.sendRedirect("/IndexServlet");
    }
}
