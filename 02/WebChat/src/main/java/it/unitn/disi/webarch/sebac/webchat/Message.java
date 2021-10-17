package it.unitn.disi.webarch.sebac.webchat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {

    private String author;
    private String text;
    private Timestamp time;

    public Message(String author, String text, Timestamp time) {
        this.author = author;
        this.text = text;
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFormattedTime() {
        SimpleDateFormat formattedTime = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return formattedTime.format(this.time);
    }
}
