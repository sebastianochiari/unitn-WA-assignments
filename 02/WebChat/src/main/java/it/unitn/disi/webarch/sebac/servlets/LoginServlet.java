package it.unitn.disi.webarch.sebac.servlets;

import it.unitn.disi.webarch.sebac.webchat.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    ArrayList<User> users;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameters
        String inputUsername = request.getParameter("username");
        String inputPassword = request.getParameter("password");

        // get users list
        users = (ArrayList<User>) getServletContext().getAttribute("users");

        // search if the given username corresponds to an existing user
        int index = -1;
        for(int i = 0; i < users.size(); i++) {
            if((users.get(i)).getUsername().equals(inputUsername)) {
                index = i;
            }
        }

        // user not found
        if(index == -1) {
            request.setAttribute("login", "error");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else { // user found
            User user = users.get(index);
            // check password consistency
            if(user.getPassword().equals(inputPassword)) {
                // set session
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getUsername());
                session.setAttribute("isAdmin", user.getIsAdmin());
                session.setMaxInactiveInterval(30 * 60);
                // redirect to index.jsp
                response.sendRedirect("/home");
            } else {
                request.setAttribute("login", "error");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
}
