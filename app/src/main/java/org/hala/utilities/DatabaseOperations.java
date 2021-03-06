package org.hala.utilities;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.hala.R;
import org.hala.classes.MyApplication;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.hala.fragments.InTheNewsFragment;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class DatabaseOperations {

    // Declare variables
    public static final String KEY_ID = "id";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_ANSWER = "answer";
    public static final String KEY_SEARCH = "param";

    // Declare controls
    ProgressDialog pDialog;

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArry, final String TAG,
                                     final TextView errorTextView, final FloatingActionButton fab, final VolleyCallback callback) {
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
                        if (fab != null)
                        fab.setVisibility(View.VISIBLE);
                        //InTheNewsFragment.fab.show();
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
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req, TAG);
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }

    /**
     * Method to make post a string request to the server
     */
    public void post(String url, final String TAG,
                     final TextView errorTextView, final VolleyCallback callback) {

        // declaring variables
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
                        Toast.makeText(Util.getActivity(), response, Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Util.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                }) {
            // build parameters for post request
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_ID, id);
                params.put(KEY_QUESTION, question);
                params.put(KEY_ANSWER, answer);
                return params;
            }

        };
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest, "tag");
    }


    /**
     * Method to make post a string request to the server
     */
    public void postSearch(String url, final String searchValue,
                           final TextView errorTextView, final VolleyCallback callback) {

        final String search = searchValue;

        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        try {
                            // pass jsonOutput to callback interface
                            callback.onSuccessResponse(response);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Util.getContext(), Util.getContext().getResources()
                                .getString(R.string.volley_error_no_connection),Toast.LENGTH_LONG).show();
                        pDialog.hide();
                    }
                }) {
            // build parameters for post request
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_SEARCH, search);
                return params;
            }

        };
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest, "tag");
    }


}

