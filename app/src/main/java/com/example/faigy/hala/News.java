package com.example.faigy.hala;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

/**
 * Created by Home on 2/3/2016.
 */
public class News {
    // class members
    public String title, publication, url, date, category;

    // constructor
    public News() {

    }

    public News(String title, String publication, String date, String url, String category) {
        this.title = title;
        this.publication = publication;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
