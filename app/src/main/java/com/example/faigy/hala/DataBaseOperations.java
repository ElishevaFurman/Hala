package com.example.faigy.hala;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.NameValuePair;
import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Home on 2/23/2016.
 */
public class DataBaseOperations {
    Context context;
    public DataBaseOperations(Context context){
    this.context = context;
    }

    // json array response url
    private String urlJsonArry = "http://162.243.100.186/news_array.php";
    JSONArray response;
    News[] data;
    ArrayList<News> newsList;
    MainActivity mainActivity;

    private static String TAG = MainActivity.class.getSimpleName();




    /**
     * Method to make json array request where response starts with [
     * */
    public void makeJsonArrayRequest() {
//        Context context = Util.getContext();
//        Application application = (Application)MyApplication.getContext(context);
//        MyApplication app = (MyApplication)application;

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            Gson gson = new Gson();
                            String jsonOutput = response.toString();
                            data = gson.fromJson(jsonOutput, News[].class);
                            newsList = new ArrayList<>(Arrays.asList(data));
//                            MyApplication.getContext();
//                            MyApplication.setNewsArrayList(newsList);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Util.getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
