package com.example.faigy.hala;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;


public class ContactFragment extends Fragment {
    // Declare activities
    MainActivity mainActivity;

    // Declare controls
    TextView titleTextView, locationTextView, phoneTextView, emailTextView;
    FloatingActionButton mapFab;
    LinearLayout addressLinearLayout, phoneLinearLayout, faxLinearLayout, emailLinearLayout,
    busesLinearLayout;

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
        registerListeners();
        // set toolbar title
        Util.setToolbarTitle("Contact us", mainActivity.toolbar);
        return rootView;

    }  /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference controls
        titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
        titleTextView.setText(Html.fromHtml(getString(R.string.contact_title)));

        locationTextView = (TextView) rootView.findViewById(R.id.locationTextView);
        locationTextView.setText(Html.fromHtml(getString(R.string.contact_location)));

        phoneTextView = (TextView) rootView.findViewById(R.id.phoneTextView);
        phoneTextView.setText(Html.fromHtml(getString(R.string.contact_phone)));

        emailTextView = (TextView) rootView.findViewById(R.id.emailTextView);
        emailTextView.setText(Html.fromHtml(getString(R.string.contact_email)));

        mapFab = (FloatingActionButton) rootView.findViewById(R.id.contactFab);

        addressLinearLayout = (LinearLayout)rootView.findViewById(R.id.addressLinearLayout);
        phoneLinearLayout = (LinearLayout)rootView.findViewById(R.id.phoneLinearLayout);
        faxLinearLayout = (LinearLayout)rootView.findViewById(R.id.faxLinearLayout);
        emailLinearLayout = (LinearLayout)rootView.findViewById(R.id.emailLinearLayout);
        busesLinearLayout = (LinearLayout)rootView.findViewById(R.id.busesLinearLayout);

    }


    /**
     * Function to register listeners
     */
    private void registerListeners() {
        // set onClickListeners
        mapFab.setOnClickListener(addressListener);
        addressLinearLayout.setOnClickListener(addressListener);
        phoneLinearLayout.setOnClickListener(phoneListener);
        emailLinearLayout.setOnClickListener(emailListener);
        busesLinearLayout.setOnClickListener(busesListener);
    }

    /**
     * OnClickListener for addressListener
     */
    View.OnClickListener addressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // open Google Maps and populate it with defaultLocation
            Util.navigationIntent("5 nakhum khaftsadi st, Jerusalem, 95484, Israel");
        }
    };

    /**
     * OnClickListener for phoneListener
     */
    View.OnClickListener phoneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Util.callIntent(phoneTextView.getText().toString());
        }
    };

    /**
     * OnClickListener for emailListener
     */
    View.OnClickListener emailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // get list of email addresses from xml array
            String[] addresses =
                    Util.getActivity().getResources().getStringArray(R.array.addresses);

            // subject line
            String subject = "Contact Hala";
            // compose email using email address and subject line
            Util.composeEmail(addresses, subject);
        }
    };

    /**
     * OnClickListener for busesListener
     */
    View.OnClickListener busesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://mslworld.egged.co.il/?language=he&state=2#/search")));
        }
    };

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}
