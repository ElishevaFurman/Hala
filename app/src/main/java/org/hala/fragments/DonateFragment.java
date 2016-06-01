package org.hala.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import org.hala.activities.MainActivity;
import org.hala.R;
import org.hala.utilities.Util;

public class DonateFragment extends Fragment {

    // Declare activities
    MainActivity mainActivity;

    // Declare controls
    RelativeLayout donateRelativeLayout;
    ImageView donateImageView;

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
        View rootView = inflater.inflate(R.layout.fragment_donate_now, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // Register listeners for controls
        registerListeners();

        // set toolbar title
        //Util.setToolbarTitle(R.string.fragment_donate, mainActivity.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_donate));

        // set navigation drawer to toggle
        mainActivity.openNavigationDrawer();

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_donate);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference controls
        donateRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.donateRelativeLayout);
        donateImageView = (ImageView) rootView.findViewById(R.id.donateImageView);

        // set bitmap of donateImageView
        donateImageView.setImageBitmap(
                Util.decodeSampledBitmapFromResource(getResources(),
                        R.drawable.hala_capture_building, 180, 180));

    }

    /**
     * Function to register Listener
     */
    public void registerListeners() {
        // set on click listener for donate now layout
        donateRelativeLayout.setOnClickListener(donateNowListener);

    }

    // Listener for donateRelativeLayout
    View.OnClickListener donateNowListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Declare variables
            String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=SCKZLDNUTW3DW&lc=US" +
                    "&item_name=Hala&currency_code=AUD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";

            Util.createDialog(R.string.donate, R.string.donate_message, R.string.donate_now,
                    R.string.cancel, null, "url", url);

        }
    };

    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}