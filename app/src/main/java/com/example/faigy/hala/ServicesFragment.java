package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    LinearLayout servicesLinearLayout1, servicesLinearLayout2, servicesLinearLayout3, servicesLinearLayout4,
            servicesLinearLayout5, servicesLinearLayout6, servicesLinearLayout7;
    public ServicesFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services3, container, false);
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
        servicesLinearLayout1 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout1);
        servicesLinearLayout2 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout2);
        servicesLinearLayout3 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout3);
        servicesLinearLayout4 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout4);
        servicesLinearLayout5 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout5);
        servicesLinearLayout6 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout6);
        servicesLinearLayout7 = (LinearLayout) rootView.findViewById(R.id.servicesLinearLayout7);

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
        servicesLinearLayout1.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout2.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout3.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout4.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout5.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout6.setOnClickListener(servicesLinearLayoutListener);
        servicesLinearLayout7.setOnClickListener(servicesLinearLayoutListener);
    }

    /**
     * OnClickListener for servicesRelativeLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

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
