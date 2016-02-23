package com.example.faigy.hala;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by Home on 2/22/2016.
 */
public class MyApplication extends Application {

    private ArrayList<News> newsArrayList;

    public ArrayList<News> getNewsArrayList() {
        return newsArrayList;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }



}
