package com.example.faigy.hala;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

/**
 * Created by Home on 2/3/2016.
 */
public class News {
    // class members
    @SerializedName("Title")
    public String title;
    @SerializedName("Url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("Publication")
    public  String publication;
    @SerializedName("Date")
    String date;

    // constructor
    public News() {

    }

    public News(String title, String publication, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
