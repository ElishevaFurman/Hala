package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    //Declare Activities
    MainActivity mainActivity;

    //Declare Controls
    RelativeLayout servicesRelativeLayout1, servicesRelativeLayout2, servicesRelativeLayout3,
            servicesRelativeLayout4, servicesRelativeLayout5, servicesRelativeLayout6, servicesRelativeLayout7;

    public ServicesFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services, container, false);
        // Initialize the views for tis fragment
        initializeViews(rootView);
        registerListeners();
        //mainActivity.getSupportActionBar().hide();
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
        servicesRelativeLayout1 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout1);
        servicesRelativeLayout2 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout2);
        servicesRelativeLayout3 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout3);
        servicesRelativeLayout4 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout4);
        servicesRelativeLayout5 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout5);
        servicesRelativeLayout6 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout6);
        servicesRelativeLayout7 = (RelativeLayout) rootView.findViewById(R.id.servicesRelativeLayout7);

//        final RippleView rippleView = (RippleView) rootView.findViewById(R.id.rect);
//        rippleView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Log.e("Sample", "Click Rect !");
//            }
//        });
//        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
//            @Override
//            public void onComplete(RippleView rippleView) {
//                Log.d("Sample", "Ripple completed");
//            }
//        });
    }

    /**
     * Function to register Listeners
     */
    public void registerListeners() {
        servicesRelativeLayout1.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout2.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout3.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout4.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout5.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout6.setOnClickListener(servicesRelativeLayoutListener);
        servicesRelativeLayout7.setOnClickListener(servicesRelativeLayoutListener);
    }

    /**
     * OnClickListener for servicesRelativeLayoutListener
     */
    View.OnClickListener servicesRelativeLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
        }
    };

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPause() {

        super.onPause();
        mainActivity.getSupportActionBar().show();
    }
}
