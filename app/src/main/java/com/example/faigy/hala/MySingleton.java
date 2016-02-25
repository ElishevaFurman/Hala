package com.example.faigy.hala;

import java.util.ArrayList;

/**
 * Created by Home on 2/25/2016.
 */
public class MySingleton {
    private static MySingleton instance;

    private ArrayList<News> newsArrayList;
    private ArrayList<TeamMember> teamMembersArrayList;

    public ArrayList<News> getNewsArrayList() {
        return newsArrayList;
    }

    public ArrayList<TeamMember> getTeamMembersArrayList() {
        return teamMembersArrayList;
    }

    public void setTeamMembersArrayList(ArrayList<TeamMember> teamMembersArrayList) {
        this.teamMembersArrayList = teamMembersArrayList;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    public static void initInstance()
    {
        if (instance == null)
        {
            // Create the instance
            instance = new MySingleton();
        }
    }

    public static MySingleton getInstance()
    {
        // Return the instance
        return instance;
    }

    private MySingleton()
    {
        // Constructor hidden because this is a singleton
    }

}