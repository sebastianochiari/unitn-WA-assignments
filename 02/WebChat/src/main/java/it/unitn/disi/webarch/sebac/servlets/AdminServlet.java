package it.unitn.disi.webarch.sebac.servlets;

import it.unitn.disi.webarch.sebac.webchat.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameters
        String inputUsername = request.getParameter("username");
        String inputPassword = request.getParameter("password");

        ArrayList<User> users;
        User user;

        synchronized (this) {
            // get users list
            users = (ArrayList<User>) getServletContext().getAttribute("users");

            // create the new user object
            user = new User(inputUsername, inputPassword, false);

            // add user to users arraylist
            users.add(user);
        }

        // update the users on the servlet context
        getServletContext().setAttribute("users", users);

        String fileName = (String) getServletContext().getAttribute("usersFile");
        String toWrite = "\n" + inputUsername + " " + inputPassword;

        // write new user in the file
        try {
            Files.write(Paths.get(fileName), toWrite.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        response.sendRedirect("/admin/users.jsp");
    }
}
