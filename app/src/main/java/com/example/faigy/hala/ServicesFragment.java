package com.example.faigy.hala;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    // Declare variables
    Services[] servicesData;
    ArrayList<Services> servicesList;
    private RequestQueue requestQueue;

    // Declare class
    private VolleySingleton volleySingleton;

    private static String TAG = MainActivity.class.getSimpleName();

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;
    ProgressDialog pDialog;


    //Declare Controls
    LinearLayout servicesLinearLayout1, servicesLinearLayout2, servicesLinearLayout3, servicesLinearLayout4,
            servicesLinearLayout5, servicesLinearLayout6, servicesLinearLayout7;
    public ServicesFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services3, container, false);
        // Initialize the views for tis fragment
        initializeViews(rootView);
        registerListeners();
        //mainActivity.getSupportActionBar().hide();
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_services, mainActivity.toolbar);
        // set navigation drawer to toggle
        mainActivity.openNavigationDrawer();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_services);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        servicesLinearLayout1 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout1);
        servicesLinearLayout2 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout2);
        servicesLinearLayout3 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout3);
        servicesLinearLayout4 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout4);
        servicesLinearLayout5 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout5);
        servicesLinearLayout6 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout6);
        servicesLinearLayout7 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout7);
        pDialog = new ProgressDialog(Util.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        makeJsonArrayRequest("http://162.243.100.186/services_array.php");
        //Toast.makeText(Util.getContext(),servicesList.size()+"",Toast.LENGTH_LONG).show();
//        final RippleView rippleView = (RippleView) rootView.findViewById(R.id.rect);
//        rippleView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Log.e("Sample", "Click Rect !");
//            }
//        });
//        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Log.d("Sample", "Ripple completed");
//            }
//        });
    }

    /**
     * Function to register Listeners
     */
    public void registerListeners() {
        servicesLinearLayout1.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout2.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout3.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout4.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout5.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout6.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout7.setOnClickListener(servicesLinearLayoutListener);
    }

    /**
     * OnClickListener for servicesRelativeLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);

        }
    };

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPause() {

        super.onPause();
        mainActivity.getSupportActionBar().show();
    }

    /**
     * Method to make json array request where response starts with
     * */
    public void makeJsonArrayRequest(String urlJsonArry) {

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        Gson gson = new Gson();
                        String jsonOutput = response.toString();
                        try {
                            servicesData = gson.fromJson(jsonOutput, Services[].class);
                            servicesList = new ArrayList<>(Arrays.asList(servicesData));
                            MySingleton.getInstance().setServicesArrayList(servicesList);
                           // mAdapter.setNewsList(sortList(newsList, p));
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Util.getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(req);

    }
}
