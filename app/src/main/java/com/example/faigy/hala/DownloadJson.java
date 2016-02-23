package com.example.faigy.hala;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by faigy on 2/23/2016.
 */
public class DownloadJson extends AsyncTask<Void, Void, Void>{

    MainActivity mainActivity;
    JSONArray newsArray;
    News[] data;
    ArrayList<News> newsList;
    protected MyApplication app;

    @Override
    public void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Void doInBackground(Void... params) {

        //newsArray = new JSONArray();
        try {
            newsArray =  Util.getJsonArray("http://162.243.100.186/news.php", "newsArray", new ArrayList<NameValuePair>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonOutput = newsArray.toString();
        data = gson.fromJson(jsonOutput, News[].class);
        newsList = new ArrayList<>(Arrays.asList(data));
        mainActivity.app.setNewsArrayList(newsList);



        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);





    }


}