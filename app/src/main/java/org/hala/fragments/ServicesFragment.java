package org.hala.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.hala.adapters.ServicesAdapter;
import org.hala.classes.Services;
import org.hala.utilities.DatabaseOperations;
import org.hala.activities.MainActivity;
import org.hala.classes.MyApplication;
import org.hala.R;
import org.hala.utilities.Util;
import org.hala.utilities.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    private ServicesAdapter mAdapter;
    TextView errorTextView;
    ImageView serviceImageView;

    // Declare variables
    Services[] servicesData;
    ArrayList<Services> servicesList;
    static String TAG = "json_services_request";
    String url;
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

        // initialize and reference ImageView
        serviceImageView = (ImageView) rootView.findViewById(R.id.serviceImageView);
        // set image bitmap to ImageView
        serviceImageView.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.services_image2, 180, 180));

    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        errorTextView.setOnClickListener(errorTextViewListener);
        //recyclerView.addOnItemTouchListener(recyclerViewOnItemTouchListener);
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
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {
        // instantiate mAdapter
        mAdapter = new ServicesAdapter(getActivity());
        // initialize linearLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                getActivity().getBaseContext());
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
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
        // set url data to corresponding language of phone settings
        if (!Locale.getDefault().getLanguage().equals("en")) {

            url = "http://162.243.100.186/services_array_he.php";

        } else {

            url = "http://162.243.100.186/services_array.php";
        }
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
    }

}