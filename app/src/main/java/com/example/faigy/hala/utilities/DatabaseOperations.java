package com.example.faigy.hala.utilities;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.activities.MainActivity;
import com.example.faigy.hala.classes.MyApplication;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

/**
 * Created by Home on 3/16/2016.
 */
public class DatabaseOperations {
    ProgressDialog pDialog;
    MainActivity mainActivity;

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArry, final String TAG,
                                     final TextView errorTextView, final VolleyCallback callback) {
        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        // hide progress dialog
                        pDialog.hide();
                        // initialize string to store json result
                        String jsonOutput = response.toString();
                        try {
                            // pass jsonOutput to callback interface
                            callback.onSuccessResponse(jsonOutput);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error exceptions
                Util.handleVolleyError(error, errorTextView);
                pDialog.hide();
            }
        });
        //requestQueue.add(req);
        MyApplication.getInstance().addToRequestQueue(req, TAG);
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }


}

