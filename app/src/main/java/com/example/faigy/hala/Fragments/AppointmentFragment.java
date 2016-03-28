package com.example.faigy.hala.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;

/**
 * Created by Home on 2/11/2016.
 */
public class AppointmentFragment extends Fragment {

    // Declare Activity
    MainActivity mainActivity;

    public AppointmentFragment() {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appointment, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_appointment, mainActivity.toolbar);
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_appointments);
        return rootView;

    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}