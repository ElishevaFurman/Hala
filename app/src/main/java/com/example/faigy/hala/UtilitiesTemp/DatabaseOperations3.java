package com.example.faigy.hala.UtilitiesTemp;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.ActivitiesTemp.MainActivity;
import com.example.faigy.hala.ClassesTemp.MyApplication;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 3/16/2016.
 */
public class DatabaseOperations3 {
    ProgressDialog pDialog;
    MainActivity mainActivity;
    final String id= "1";
    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest() {
        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        // Creating volley request obj
        final JsonArrayRequest matchReq = new JsonArrayRequest(Request.Method.POST, "http://162.243.100.186/faq_test3.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response.toString());
                        // hide progress dialog
                        pDialog.hide();
                        // initialize string to store json result
                        String jsonOutput = response.toString();
                        try {
                            // pass jsonOutput to callback interface
                            //callback.onSuccessResponse(jsonOutput);
                            Toast.makeText(Util.getContext(),"success",Toast.LENGTH_LONG).show();
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hidePDialog();
                pDialog.hide();
                Toast.makeText(Util.getContext(),"not success", Toast.LENGTH_LONG).show();

            }
        })
        {

            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("id",id);
                return params;
            }

            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("id", "1"); //Add the data you'd like to send to the server.
                return MyData;
            }

        };

        MyApplication.getInstance().addToRequestQueue(matchReq, "json_url");
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }


}

