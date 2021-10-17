package it.unitn.disi.webarch.sebac.servlets;

import it.unitn.disi.webarch.sebac.webchat.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", value = "/home")
public class IndexServlet extends HttpServlet {

    ArrayList<Room> rooms;

    @Override
    public void init() throws ServletException {
        rooms = new ArrayList<Room>();
        getServletContext().setAttribute("areThereRooms", false);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameters
        String roomName = request.getParameter("roomName");
        String user = (String) request.getSession().getAttribute("user");

        // create room
        Room room = new Room(roomName, user);
        synchronized (this) {
            rooms.add(room);
            if(rooms.size() == 1) {
                getServletContext().setAttribute("areThereRooms", true);
            }
            getServletContext().setAttribute("rooms", rooms);
        }

        // redirect to index.jsp
        response.sendRedirect("/index.jsp");

    }
}
