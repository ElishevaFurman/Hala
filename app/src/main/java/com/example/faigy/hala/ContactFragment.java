package com.example.faigy.hala;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class ContactFragment extends Fragment {
    // Declare controls
    MainActivity mainActivity;
    TextView titleTextView, locationTextView, phoneTextView, emailTextView;
    FloatingActionButton contactFab;



    public ContactFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);

        return rootView;

    }
    public void initializeViews(View rootView) {
        titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
        titleTextView.setText(Html.fromHtml(getString(R.string.contact_title)));

        locationTextView = (TextView) rootView.findViewById(R.id.locationTextView);
        locationTextView.setText(Html.fromHtml(getString(R.string.contact_location)));

        phoneTextView = (TextView) rootView.findViewById(R.id.phoneTextView);
        phoneTextView.setText(Html.fromHtml(getString(R.string.contact_phone)));

        emailTextView = (TextView) rootView.findViewById(R.id.emailTextView);
        emailTextView.setText(Html.fromHtml(getString(R.string.contact_email)));

        contactFab = (FloatingActionButton) rootView.findViewById(R.id.contactFab);
        contactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("TestimonialView")
                        .replace(R.id.container, mainActivity.testimonialViewFragment).commit();
            }
        });


    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


        @Override
    public void onResume() {
        super.onResume();


    }
}
