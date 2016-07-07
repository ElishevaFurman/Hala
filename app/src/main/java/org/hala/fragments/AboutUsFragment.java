package org.hala.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.hala.activities.MainActivity;
import org.hala.R;
import org.hala.utilities.Util;


public class AboutUsFragment extends Fragment {

    // Declare activities
    MainActivity mainActivity;

    // Declare controls
    ImageView aboutUsImageView;

    public AboutUsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // set toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_about));

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_about);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference controls
        aboutUsImageView = (ImageView) rootView.findViewById(R.id.aboutUsImageView);

        // set imageBitmap
        aboutUsImageView.setImageBitmap(
                Util.decodeSampledBitmapFromResource(getResources(), R.drawable.about_us_image, 180, 180));
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