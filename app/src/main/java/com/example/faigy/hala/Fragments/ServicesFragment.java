package com.example.faigy.hala.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faigy.hala.Adapters.ServicesAdapter;
import com.example.faigy.hala.Classes.Services;
import com.example.faigy.hala.Utilities.DatabaseOperations;
import com.example.faigy.hala.Interfaces.ClickListenerInterface;
import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.Classes.MyApplication;
import com.example.faigy.hala.Classes.MySingleton;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Listeners.RecyclerTouchListener;
import com.example.faigy.hala.Utilities.Util;
import com.example.faigy.hala.Utilities.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;
    TextView errorTextView;

    // Declare variables
    Services[] servicesData;
    ArrayList<Services> servicesList;
    private static String TAG = "json_services_request";
    private String url = "http://162.243.100.186/services_array.php";
    FragmentManager.BackStackEntry backStackEntry;

    // Declare class
    DatabaseOperations databaseOperations;

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;

    public ServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate databaseOperations
        databaseOperations = new DatabaseOperations();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services_list, container, false);

        // Initialize the views for tis fragment
        initializeViews(rootView);

        // set up recyclerView
        setupRecyclerView();

        // register listeners for controls
        registerListeners();

        // check for last fragment in backStack
        checkBackStackEntry();

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
        // initialize and reference TextView
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);

        // initialize and reference RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        errorTextView.setOnClickListener(errorTextViewListener);
        recyclerView.addOnItemTouchListener(recyclerViewOnItemTouchListener);
    }

    /**
     * OnClickListener for errorTextView
     */
    View.OnClickListener errorTextViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            errorTextView.setText("");
            // download data from url
            downloadData();
        }
    };

    /**
     * OnItemTouchListener for recyclerView
     */
    RecyclerTouchListener recyclerViewOnItemTouchListener = new RecyclerTouchListener
            (getActivity(), recyclerView, new ClickListenerInterface() {
        @Override
        public void onClick(View view, int position) {
            // set and store the clicked position in the recycler view
            MySingleton.getInstance().setPosition(position);
            // switch to service detail fragment
            Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
        }
    });

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {
        // instantiate mAdapter
        mAdapter = new ServicesAdapter(getActivity());
        // initialize linearLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // attach the adapter to the recyclerView to populate items
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * Function to check backStackEntry
     */
    public void checkBackStackEntry() {
        // assign last fragment in backStack to backStackEntry
        backStackEntry = getFragmentManager().getBackStackEntryAt(getActivity()
                .getFragmentManager().getBackStackEntryCount() - 1);
        // check if the latest fragment in backStack is not service fragment
        // or if servicesList is null or empty then reload data
        if (!backStackEntry.getName().equals(R.string.fragment_services + "")
                || servicesList == null || servicesList.size() == 0) {
            // make jsonRequest
            downloadData();
        }
        // if backStackEntry is equal to servicesFragment
        else {
            // set adapter to service list that was previously downloaded
            mAdapter.setServicesList(servicesList);
        }
    }

    /**
     * Function to download data from url
     */
    public void downloadData() {
        // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
        databaseOperations.makeJsonArrayRequest(url, TAG, errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        // initialize gson object
                        Gson gson = new Gson();
                        try {
                            // convert json array into array of class type
                            servicesData = gson.fromJson(result, Services[].class);
                            // convert array to arrayList
                            servicesList = new ArrayList<>(Arrays.asList(servicesData));
                            // set list to adapter
                            mAdapter.setServicesList(servicesList);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
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

}