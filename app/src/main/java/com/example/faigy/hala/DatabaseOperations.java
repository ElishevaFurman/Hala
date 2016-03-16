package com.example.faigy.hala;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Home on 3/16/2016.
 */
public class DatabaseOperations{
    ProgressDialog pDialog;
    public News[] newsData;
    public ArrayList<News> newsList;
    MainActivity mainActivity;


    /**
     * Method to make json array request where response starts with
     * */
    public void makeJsonArrayRequest(String urlJsonArry, final String TAG, final TextView errorTextView, final VolleyCallback callback) {
        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();

                        String jsonOutput = response.toString();
                        try {
                          callback.onSuccessResponse(jsonOutput);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.handleVolleyError(error, errorTextView);
                pDialog.hide();
            }
        });
        //requestQueue.add(req);
        MyApplication.getInstance().addToRequestQueue(req,TAG);
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }




    }

