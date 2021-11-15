package it.unitn.disi.webarch.sebac.memorygame.servlets;

import org.javatuples.Pair;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {

    ArrayList<Pair<Integer, String>> results;

    @Override
    public void init() throws ServletException {
        results = new ArrayList<Pair<Integer, String>>();
        getServletContext().setAttribute("emptyScoreboard", true);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameters
        int score = Integer.parseInt(request.getParameter("score"));
        String user = (String) request.getSession().getAttribute("user");

        // build a Pair with username and score
        Pair<Integer, String> pair = Pair.with(score, user);

        synchronized (this) {
            // add the Pair to the results arraylist
            results.add(pair);
            // sort the arraylist
            Collections.sort(results);
            Collections.reverse(results);
        }

        if(results.size() == 1) {
            getServletContext().setAttribute("emptyScoreboard", false);
        }

        // create scoreboard arraylist
        ArrayList<Pair<Integer, String>> scoreboard;
        if(results.size() > 5) {
            scoreboard = new ArrayList<Pair<Integer, String>>();
            for(int i = 0; i < 5; i++) {
                scoreboard.add(results.get(i));
            }
        } else {
            scoreboard = results;
        }
        getServletContext().setAttribute("scoreboard", scoreboard);
    }
}
