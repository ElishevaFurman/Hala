package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Home on 2/11/2016.
 */
public class AppointmentFragment extends Fragment {
    // Declare controls
    MainActivity mainActivity;


    public AppointmentFragment() {
        // Required empty public constructor
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
        // set back icon on toolbar
        //Util.enableBackButton(R.drawable.ic_arrow_back_24dp, mainActivity.toolbar, mainActivity.drawer);

        return rootView;

    }

    public void initializeViews(View rootView) {


    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}