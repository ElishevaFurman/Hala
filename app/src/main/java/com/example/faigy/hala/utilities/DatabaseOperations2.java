package com.example.faigy.hala.utilities;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faigy.hala.activities.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 3/16/2016.
 */
public class DatabaseOperations2 {
    ProgressDialog pDialog;
    MainActivity mainActivity;
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";

    String url = "http://162.243.100.186/faq_test3.php";

    public static final String KEY_ID= "id";


    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest() {
        if (pDialog == null) {
            pDialog = Util.createProgressDialog(Util.getActivity());
        }
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Util.getContext(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Util.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_ID, "1");

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Util.getContext());
        requestQueue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccessResponse(final String result);

    }


}


