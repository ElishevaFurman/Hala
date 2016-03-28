package com.example.faigy.hala.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.faigy.hala.Classes.Services;
import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.Classes.MySingleton;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceDetailFragment extends Fragment {
    // Declare Activities
    MainActivity mainActivity;
    // Declare Variables
    ArrayList<Services> servicesArrayList;
    int position;
    // Declare Controls
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView image;
    ProgressBar imageProgressBar;

    public ServiceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        // hide mainActivity's toolbar
       // mainActivity.getSupportActionBar().hide();
        // get title of servicesListArray in that position
        String title = mainActivity.servicesFragment.servicesList.get(position).getTitle();
        // set toolbar title with the title of the service that was selected
        Util.setToolbarTitle(title, mainActivity.toolbar);
        // set back button on toolbar
        Util.enableBackButton(mainActivity.toolbar, mainActivity.drawer);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service_detail, container, false);
        // get servicesArrayList from MySingleton class
        servicesArrayList = MySingleton.getInstance().getServicesArrayList();
        // Initialize the views for this fragment
        initializeViews(rootView);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // get position of clicked item of servicesArrayList from MySingleton class
        position = MySingleton.getInstance().getPosition();
        // initialize and reference controls
        image = (ImageView) rootView.findViewById(R.id.imageView1);
        imageProgressBar = (ProgressBar)rootView.findViewById(R.id.imageProgressBar);
        //mainActivity.setSupportActionBar((Toolbar) rootView.findViewById(R.id.toolbar));
        collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Collapsing");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        WebView serviceDescriptionTextViews = (WebView)rootView.findViewById(R.id.serviceDescriptionTextViews);

        String content = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                        + mainActivity.servicesFragment.servicesList.get(position).getDescription()
                        .replace("\n", "<br />") + "</body>]]>"));
        serviceDescriptionTextViews.loadData(content, "text/html", "utf-8");
        Picasso.with(Util.getActivity()).load("http://" + mainActivity.servicesFragment.servicesList.get(position).getImage())
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        imageProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        // TODO Auto-generated method stub
                        imageProgressBar.setVisibility(View.GONE);
                        image.setBackgroundResource(R.mipmap.ic_launcher_hala);
                    }
                });
    }

    @Override
    public void onPause() {

        super.onPause();

        //mainActivity.getSupportActionBar().show();

        //mainActivity.openNavigationDrawer();
        //toolbar.hideOverflowMenu();

        //Util.setToolbarTitle(R.string.fragment_faq, mainActivity.toolbar);
        //MySingleton.getInstance().setServicesDetails(true);
    }

    /**
     * Function to set fragment to this main activity
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
