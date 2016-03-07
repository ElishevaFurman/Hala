package com.example.faigy.hala;

/**
 * Created by Home on 3/7/2016.
 */
public class Services {
    int id;
    String title, description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Services(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
