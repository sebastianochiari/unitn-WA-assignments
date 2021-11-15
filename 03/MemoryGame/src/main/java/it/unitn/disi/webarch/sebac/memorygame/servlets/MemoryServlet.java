package it.unitn.disi.webarch.sebac.memorygame.servlets;

import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(
        name = "MemoryServlet",
        value = "/MemoryServlet",
        initParams = {
                @WebInitParam(name = "environment", value = "production")
        }
)
public class MemoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Integer> cards = new ArrayList<>();

        int numberOfCards = 8;
        for(int i = 0; i < numberOfCards * 2; i++) {
            cards.add((i % numberOfCards) + 1);
        }

        // get environment parameter
        String environment = getInitParameter("environment");
        if(environment.equals("test")) {
            Collections.sort(cards);
        } else if(environment.equals("production")) {
            Collections.shuffle(cards);
        }

        // save into session variable
        request.getSession().setAttribute("cards", cards);

        // redirect to the memory.html page
        getServletContext().getRequestDispatcher("/memory.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve session cards
        ArrayList<Integer> cards = (ArrayList<Integer>) request.getSession().getAttribute("cards");
        // retrieve the parameters
        int id = Integer.parseInt(request.getParameter("id"));
        // retrieve card value
        int cardValue = cards.get(id - 1);
        // build json object given the card value
        JSONObject card = new JSONObject();
        card.put("value", cardValue);
        // set response content type to be json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(card);
        out.flush();
    }
}
