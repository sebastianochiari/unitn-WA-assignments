package it.unitn.disi.webarch.sebac.servlets;

import it.unitn.disi.webarch.sebac.webchat.Message;
import it.unitn.disi.webarch.sebac.webchat.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@WebServlet(
        name = "RoomServlet",
        value = {"/RoomServlet", "/room"}
)
public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameter
        String roomName = request.getParameter("name");

        // get all the rooms
        ArrayList<Room> rooms = (ArrayList<Room>) getServletContext().getAttribute("rooms");

        // search if the given room name corresponds to an existing room
        int roomID = -1;
        for(int i = 0; i < rooms.size(); i++) {
            if((rooms.get(i)).getName().equals(roomName)) {
                roomID = i;
            }
        }

        if(roomID == -1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            // save room object
            Room room = rooms.get(roomID);
            // update room object
            request.setAttribute("room", room);
            request.setAttribute("roomID", roomID);
            getServletContext().getRequestDispatcher("/room.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer roomID = Integer.valueOf(request.getParameter("roomID"));
        String corpus = request.getParameter("message");

        // build message
        Message message = new Message(
                (String) (request.getSession()).getAttribute("user"),
                corpus,
                new Timestamp(System.currentTimeMillis())
        );

        ArrayList<Room> rooms = new ArrayList<Room>();
        Room room;

        synchronized(this) {
            // get all the rooms
            rooms = (ArrayList<Room>) getServletContext().getAttribute("rooms");
            // get the room and add the message
            room = rooms.get(roomID);
            room.addMessage(message);

            room.orderNewest();

            // update rooms locally
            rooms.set(roomID, room);
        }

        // update rooms in the servlet context
        getServletContext().setAttribute("rooms", rooms);

        // redirect with URL rewriting
        response.sendRedirect("/room?name=" + room.getName());
    }
}
