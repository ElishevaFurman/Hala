package org.hala.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.hala.activities.MainActivity;
import org.hala.R;
import org.hala.utilities.Util;

public class ContactFragment extends Fragment {
    // Declare activities
    MainActivity mainActivity;

    // Declare controls
    TextView titleTextView, locationTextView, phoneTextView, emailTextView, busesTextView;
    FloatingActionButton callFab;
    ImageView mapView;

    //Declare variables
    String url;

    public ContactFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // Register listeners
        registerListeners();

        return rootView;

    }

    /**
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
        busesTextView = (TextView) rootView.findViewById(R.id.busesTextView);
        callFab = (FloatingActionButton) rootView.findViewById(R.id.contactFab);
        mapView = (ImageView) rootView.findViewById(R.id.mapView);
    }


    /**
     * Function to register listeners
     */
    private void registerListeners() {
        // set onClickListeners
        mapView.setOnClickListener(addressListener);
        callFab.setOnClickListener(phoneListener);
        locationTextView.setOnClickListener(addressListener);
        phoneTextView.setOnClickListener(phoneListener);
        emailTextView.setOnClickListener(emailListener);
        busesTextView.setOnClickListener(busesListener);
    }

    /**
     * OnClickListener for addressListener
     */
    View.OnClickListener addressListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String neutralButton = getResources().getString(R.string.cancel);
            // open dialog for Google Maps
            Util.createDialog(R.string.google_map, R.string.google_map_message, R.string.google_map_button,
                    R.string.waze_map_button, neutralButton, "maps",
                    "5 nakhum khaftsadi st, Jerusalem, 95484, Israel");
        }
    };

    /**
     * OnClickListener for phoneListener
     */
    View.OnClickListener phoneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == callFab)
            animate();
            // open dialog to call Hala
            Util.createDialog(R.string.call_hala, R.string.call, R.string.call, R.string.cancel, null,
                    "call", "02-659-5533");
        }
    };

    /**
     * OnClickListener for emailListener
     */
    View.OnClickListener emailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // open dialog to email Hala
            Util.createDialog(R.string.email_hala, R.string.send_email, R.string.send_email,
                    R.string.cancel, null, "email", null);

        }
    };

    /**
     * OnClickListener for busesListener
     */
    View.OnClickListener busesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // set url to Egged's website
            url = "http://mslworld.egged.co.il/?language=he&state=2#/search";
            // open dialog to view Egged's bus schedule
            Util.createDialog(R.string.egged, R.string.egged_message, R.string.open,
                    R.string.cancel, null, "bus", url);
        }
    };

    public void animate(){
        callFab.setScaleX(0);
        callFab.setScaleY(0);
        callFab.animate().scaleX(1).scaleY(1).start();
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
