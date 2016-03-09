package com.example.faigy.hala;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.Adapters.ServicesAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;

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
    TextView errorTextView;

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
        View rootView = inflater.inflate(R.layout.fragment_services_list, container, false);
        // Initialize the views for tis fragment
        initializeViews(rootView);
        //registerListeners();
        //mainActivity.getSupportActionBar().hide();
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_services, mainActivity.toolbar);
        // set navigation drawer to toggle
        mainActivity.openNavigationDrawer();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_services);
        pDialog = new ProgressDialog(Util.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAdapter = new ServicesAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayRequest("http://162.243.100.186/news_array.php");
            }
        });
        makeJsonArrayRequest("http://162.243.100.186/services_array.php");


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
                 @Override
                      public void onClick(View view, int position) {

                     MySingleton.getInstance().setPostion(position);
                     Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
                 }
            @Override
            public void onLongClick(View view, int position) {
                MySingleton.getInstance().setPostion(position);
                Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
            }
        }));}

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
                           // MySingleton.getInstance().setServicesArrayList(servicesList);
                            mAdapter.setServicesList(servicesList);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.handleVolleyError(error,errorTextView);
                pDialog.hide();

            }
        });
        requestQueue.add(req);

    }
}
