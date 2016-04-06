package com.example.faigy.hala.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.faigy.hala.activities.MainActivity;
import com.example.faigy.hala.R;
import com.example.faigy.hala.utilities.Util;

import java.lang.reflect.Field;

public class HomeFragment extends Fragment {

    // Declare Activities
    MainActivity mainActivity;

    // Declare Controls
    LinearLayout aboutLinearLayout, servicesLinearLayout, contactLinearLayout;
    ImageView homeImageView;

    public HomeFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // Register listeners for controls
        registerListeners();

        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_home, mainActivity.toolbar);

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_home);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference LinearLayouts
        aboutLinearLayout = (LinearLayout) rootView.findViewById(R.id.aboutLinearLayout);
        servicesLinearLayout = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout);
        contactLinearLayout = (LinearLayout) rootView.findViewById(R.id.contactLinearLayout);
        // initialize and reference ImageView
        homeImageView = (ImageView) rootView.findViewById(R.id.homeImageView);

        // set imageBitmap to homeImageView
        homeImageView.setImageBitmap(
                Util.decodeSampledBitmapFromResource(getResources(), R.drawable.flower, 180, 180));

    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
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
            Util.replaceFragment(mainActivity.aboutUsFragment, R.string.fragment_about);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_about);
        }
    };

    /**
     * OnClickListener for servicesLinearLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.servicesFragment, R.string.fragment_services);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_services);
        }
    };

    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener contactLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Util.replaceFragment(mainActivity.contactUsFragment, R.string.fragment_contact);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_contact);
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
