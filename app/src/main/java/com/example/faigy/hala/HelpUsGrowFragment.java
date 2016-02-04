package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Home on 2/4/2016.
 */
public class HelpUsGrowFragment extends Fragment {
MainActivity mainActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_help_us_grow, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        return rootView;
    }

    public void initializeViews(final View rootView) {

    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
