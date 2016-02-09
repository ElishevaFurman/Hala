package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestimonialFragment extends Fragment {
    // Declare Controls
    MainActivity mainActivity;
    TextView titleTextView;
    ImageView imageView;
    CardView cardView;
    int height;
    int width;


    public TestimonialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_testimonial, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // set toolbar title
        Util.setToolbarTitle("Testimonials", mainActivity.toolbar);

        return rootView;
    }

    public void initializeViews(View rootView) {
        titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
        titleTextView.setText(Html.fromHtml(getString(R.string.testimonial_title)));
        cardView = (CardView) rootView.findViewById(R.id.testimonialCardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.openUrlInBrowser("http://www.hala.org.il/Image/uploaded/lady-sarrah-letr-medium.jpg");
            }
        });

        imageView = (ImageView) rootView.findViewById(R.id.testimonialImageView);

    }

    public void getScreenDimensions() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        //imageView.getLayoutParams().height = height;
        imageView.getLayoutParams().width = width;
        Toast.makeText(mainActivity.getApplicationContext(), height + " " + width + "", Toast.LENGTH_LONG).show();


    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}
