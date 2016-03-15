package com.example.faigy.hala;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Toast;

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
    private static String TAG = MainActivity.class.getSimpleName();
    FragmentManager.BackStackEntry backStackEntry;

    // Declare class
    private VolleySingleton volleySingleton;

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

        // initialize variables
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
        // initialize and reference controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAdapter = new ServicesAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // set adapter
        recyclerView.setAdapter(mAdapter);
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
        // set on click listener to refresh the data in the recycler view
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send json request to retrieve data
                makeJsonArrayRequest("http://162.243.100.186/news_array.php");
            }
        });


        backStackEntry = getFragmentManager().getBackStackEntryAt(getActivity().getFragmentManager().getBackStackEntryCount()-1);

        // check if the latest fragment in back stack is not service fragment
        if (!backStackEntry.getName().equals(R.string.fragment_services + "") ) {
            // download data into service array
            makeJsonArrayRequest("http://162.243.100.186/services_array.php");
        } else {
            // set adapter to service list that was previously downloaded
            mAdapter.setServicesList(servicesList);
        }

        // set item click listener for the recycler view
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                // set and store the clicked position in the recycler view
                MySingleton.getInstance().setPostion(position);
                // switch to service detail fragment
                Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
            }
        }));
    }

    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPause() {
        super.onPause();
        // display the toolbar for the next fragment
        mainActivity.getSupportActionBar().show();
    }

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArray) {

        // initialize progress dialog
        pDialog = new ProgressDialog(Util.getContext());
        // set message for dialog
        pDialog.setMessage("Loading...");
        // display the dialog
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // hide the text view from displaying error message
                        errorTextView.setVisibility(View.GONE);
                        Log.d(TAG, response.toString());
                        // hide progress dialog
                        pDialog.hide();
                        // initialize Gson object
                        Gson gson = new Gson();
                        // initialize string to store json result
                        String jsonOutput = response.toString();
                        try {
                            // convert json array into array of class type
                            servicesData = gson.fromJson(jsonOutput, Services[].class);
                            // convert array to arrayList
                            servicesList = new ArrayList<>(Arrays.asList(servicesData));
                            // set list to adapter
                            mAdapter.setServicesList(servicesList);
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
                // hide dialog after successfully retrieving data
                pDialog.hide();

            }
        });
        requestQueue.add(req);

    }
}
