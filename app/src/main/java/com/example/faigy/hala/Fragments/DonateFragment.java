package com.example.faigy.hala.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.CustomJsonRequest;
import com.example.faigy.hala.Utilities.DatabaseOperations2;
import com.example.faigy.hala.Utilities.DatabaseOperations3;
import com.example.faigy.hala.Utilities.PostJsonArrayRequest;
import com.example.faigy.hala.Utilities.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 2/7/2016.
 */
public class DonateFragment extends Fragment {
    MainActivity mainActivity;
    RelativeLayout donateRelativeLayout;
    ImageView donateImageView;
    DatabaseOperations3 databaseOperations3;


    private static final String REGISTER_URL = "http://162.243.100.186/faq_array2.php";

    public static final String KEY_ID = "id";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_ANSWER = "answer";


    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_donate_now, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_donate, mainActivity.toolbar);
        // set navigation drawer to toggle
        mainActivity.openNavigationDrawer();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_donate);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference controls
        donateRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.donateRelativeLayout);
        donateRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=SCKZLDNUTW3DW&lc=US&item_name=Hala&currency_code=AUD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";
                // Util.createDialog("Donate Now", "Donate through paypal", "DONATE NOW", "CANCEL", "url", url);
                //databaseOperations3.makeJsonArrayRequest();
                //registerUser();
                //PostJsonArrayRequest postJsonArrayRequest;
                getData();

            }
        });

        donateImageView = (ImageView) rootView.findViewById(R.id.donateImageView);
        donateImageView.setImageBitmap(
                Util.decodeSampledBitmapFromResource(getResources(), R.drawable.hala_capture_building, 180, 180));

    }

public void getData(){
//    ProgressDialog pDialog = new ProgressDialog(Util.getContext());
//
//    pDialog.setMessage("Loading...");
//    pDialog.show();
    String url="http://162.243.100.186/faqs_test3.php";
    Volley.newRequestQueue(Util.getActivity()).add(
            new CustomJsonRequest(Request.Method.POST, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Toast.makeText(Util.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Error: " + error.getMessage());
                    //hidePDialog();
                    Toast.makeText(Util.getContext(), error.toString(),Toast.LENGTH_LONG).show();
                }
            }) {


                @Override
                protected Response<JSONArray> parseNetworkResponse(
                        NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data,
                                HttpHeaderParser
                                        .parseCharset(response.headers));
                        return Response.success(new JSONArray(jsonString),
                                HttpHeaderParser
                                        .parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }
                }
            });



}
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //hidePDialog();
//    }

//    private void hidePDialog() {
//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
//    }






























    private void registerUser(){
        final String id = "12";
        final String question = "question2";
        final String answer = "answer2";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Util.getContext(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Util.getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ID, id);
                params.put(KEY_QUESTION, question);
                params.put(KEY_ANSWER, answer);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Util.getContext());
        requestQueue.add(stringRequest);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}




