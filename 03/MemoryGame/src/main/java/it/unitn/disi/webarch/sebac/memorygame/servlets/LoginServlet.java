package it.unitn.disi.webarch.sebac.memorygame.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    ArrayList<String> users;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameters
        String inputUsername = request.getParameter("username");

        // get users list
        users = (ArrayList<String>) getServletContext().getAttribute("users");

        // search if the given username corresponds to an existing user
        boolean found = false;
        for(int i = 0; i < users.size(); i++) {
            if((users.get(i)).equals(inputUsername)) {
                found = true;
            }
        }

        // user not found
        if(!found) {
            // add username to the users list
            synchronized (this) {
                users.add(inputUsername);
                getServletContext().setAttribute("users", users);
            }
        }

        // set session variable
        HttpSession session = request.getSession();
        session.setAttribute("user", inputUsername);
        session.setMaxInactiveInterval(30 * 60);

        // redirect to index.jsp
        response.sendRedirect("IndexServlet");
    }
}
