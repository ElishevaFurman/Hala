package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Home on 2/7/2016.
 */
public class DonateFragment extends Fragment {
    MainActivity mainActivity;
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
        return rootView;
    }

    public void initializeViews(final View rootView) {

    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}