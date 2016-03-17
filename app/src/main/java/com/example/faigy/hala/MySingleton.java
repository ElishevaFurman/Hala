package com.example.faigy.hala;

import java.util.ArrayList;

/**
 * Created by Home on 2/25/2016.
 */
public class MySingleton {
    // Declare classes
    private static MySingleton instance;

    // Declare variables
    public int position;
    public Boolean success;
    private ArrayList<Services> servicesArrayList;
    public String lastFragment;

    public String getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(String lastFragment) {
        this.lastFragment = lastFragment;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Services> getServicesArrayList() {
        return servicesArrayList;
    }

    public void setServicesArrayList(ArrayList<Services> servicesArrayList) {
        this.servicesArrayList = servicesArrayList;
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