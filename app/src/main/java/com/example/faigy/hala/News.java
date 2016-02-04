package com.example.faigy.hala;
import org.joda.time.LocalDate;
/**
 * Created by Home on 2/3/2016.
 */
public class News {
    // class members
    public String title, publication;
    LocalDate date;

    // constructor
    public News(){

    }
    public News(String title, String publication, LocalDate date) {
        this.title = title;
        this.publication = publication;
        this.date = date;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
