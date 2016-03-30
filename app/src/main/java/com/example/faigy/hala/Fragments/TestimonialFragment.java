package com.example.faigy.hala.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestimonialFragment extends Fragment {
    // Declare Activities
    MainActivity mainActivity;

    // Declare Controls
    TextView titleTextView;
    ImageView imageView;
    CardView cardView1, cardView2, cardView3, cardView4;

    public TestimonialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_testimonial, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_testimonials, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_testimonials);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        cardView1 = (CardView) rootView.findViewById(R.id.saraBrownCardView);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.hala.org.il/Image/uploaded/lady-sarrah-letr-medium.jpg";
                Util.createDialog(R.string.testimonial, R.string.testimonial_message, R.string.open, R.string.cancel, "url", url);
            }
        });

        cardView2 = (CardView) rootView.findViewById(R.id.sharonHarelCardView);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.hala.org.il/Image/uploaded/lady-sharon-letr-medium1.jpg";
                Util.createDialog(R.string.testimonial, R.string.testimonial_message, R.string.open, R.string.cancel, "url", url);
            }
        });


        cardView3 = (CardView) rootView.findViewById(R.id.susangKomenCardView);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.hala.org.il/Image/uploaded/Hala_Komen_Partnership.pdf";
                Util.createDialog(R.string.testimonial, R.string.testimonial_message, R.string.open, R.string.cancel, "url", url);

            }
        });

        cardView4 = (CardView) rootView.findViewById(R.id.yaakovLitzmanCardView);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.hala.org.il/Image/uploaded/Letter%20from%20Ministry%20of%20Health.pdf";
                Util.createDialog(R.string.testimonial, R.string.testimonial_message, R.string.open, R.string.cancel, "url", url);
            }
        });

    }

    /**
     * Function to set mainActivity to this fragment
     *
     * @param mainActivity - set mainActivity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
