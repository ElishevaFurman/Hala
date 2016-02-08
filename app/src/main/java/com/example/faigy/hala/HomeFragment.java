package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Home on 1/21/2016.
 */
public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    // Declare Controls
    LinearLayout aboutLinearLayout, servicesLinearLayout, contactLinearLayout;

    public HomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);
        registerListeners();

        return rootView;
    }

    public void initializeViews(View rootView) {
       aboutLinearLayout = (LinearLayout)rootView.findViewById(R.id.aboutLinearLayout);
       servicesLinearLayout = (LinearLayout)rootView.findViewById(R.id.servicesLinearLayout);
       contactLinearLayout = (LinearLayout)rootView.findViewById(R.id.contactLinearLayout);
    }

    public void registerListeners() {
        aboutLinearLayout.setOnClickListener(aboutLinearLayoutListener);
        servicesLinearLayout.setOnClickListener(servicesLinearLayoutListener);
        contactLinearLayout.setOnClickListener(contactLinearLayoutListener);
    }

    /**
     * OnClickListener for aboutLinearLayoutListener
     */
    View.OnClickListener aboutLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
     Util.replaceFragment(mainActivity.aboutUsFragment, "About us");
        }
    };

    /**
     * OnClickListener for servicesLinearLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.servicesFragment, "Services");
        }
    };

    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener contactLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.contactFragment, "Contact");
        }
    };
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
