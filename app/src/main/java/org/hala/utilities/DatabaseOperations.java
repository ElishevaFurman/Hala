package org.hala.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import org.hala.activities.MainActivity;
import org.hala.classes.MyApplication;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DatabaseOperations {
    // Declare varaibles
    ProgressDialog pDialog;
    public static final String KEY_ID = "id";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_ANSWER = "answer";

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

    /**
     * Method to make post a string request to the server
     */
    public void post(String url, final String TAG,
                              final TextView errorTextView, final VolleyCallback callback){

        // declaring varaibles
        final String question = "test question";
        final String answer = "test answer";
        final String id = "9";

        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Util.getActivity(),response,Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Util.getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                }){
            // build parameters for post request
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ID, id);
                params.put(KEY_QUESTION, question);
                params.put(KEY_ANSWER, answer);
                return params;
            }

        };
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest, "tag");
    }


}

