package org.hala.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hala.activities.MainActivity;
import org.hala.R;
import org.hala.utilities.Util;

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

        // set toolbar title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_appointment));

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_appointments);

        return rootView;

    }

    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}