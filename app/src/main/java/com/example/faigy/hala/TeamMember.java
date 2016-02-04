package com.example.faigy.hala;

/**
 * Created by Home on 1/31/2016.
 */
public class TeamMember {
    // class members
    protected String name, title, bio;

    public TeamMember(String name, String title, String bio){
        this.name = name;
        this.title = title;
        this.bio = bio;
    }

    public TeamMember(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }




}
