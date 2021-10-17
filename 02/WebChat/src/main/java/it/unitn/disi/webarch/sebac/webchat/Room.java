package it.unitn.disi.webarch.sebac.webchat;

import java.util.ArrayList;
import java.util.Collections;

public class Room {

    private String name;
    private String author;
    private ArrayList<Message> messages;

    public Room(String name, String author) {
        this.name = name;
        this.author = author;
        this.messages = new ArrayList<Message>();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public ArrayList<Message> getMessages() { return messages; }

    public void setMessages(ArrayList<Message> messages) { this.messages = messages; }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void orderNewest() {
        (this.messages).sort((o1,o2) -> o1.getTime().compareTo(o2.getTime()));
        Collections.reverse(this.messages);
    }
}
