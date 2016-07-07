package org.hala.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.hala.activities.MainActivity;
import org.hala.R;
import org.hala.utilities.Util;

import java.lang.reflect.Field;

public class HomeFragment extends Fragment {

    // Declare Activities
    MainActivity mainActivity;

    // Declare Controls
    ImageView homeImageView, servicesImageView, testimonialsImageView, newsImageView;
    CardView aboutUsCardView, servicesCardView, testimonialsCardView, newsCardView;

    public HomeFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // Register listeners for controls
        registerListeners();

        if (mainActivity.toolbar!= null){

        }
        // set toolbar title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_home));

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_home);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize and reference LinearLayouts
        aboutUsCardView = (CardView) rootView.findViewById(R.id.aboutUsCardView);
        servicesCardView = (CardView) rootView.findViewById(R.id.servicesCardView);
        testimonialsCardView = (CardView) rootView.findViewById(R.id.testimonialsCardView);
        newsCardView = (CardView) rootView.findViewById(R.id.newsCardView);

        // initialize and reference ImageView
        homeImageView = (ImageView) rootView.findViewById(R.id.homeImageView);
        servicesImageView = (ImageView) rootView.findViewById(R.id.servicesImageView);
        testimonialsImageView = (ImageView) rootView.findViewById(R.id.testimonialsImageView);
        newsImageView = (ImageView) rootView.findViewById(R.id.newsImageView);

        // set imageBitmap to homeImageView
        homeImageView.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.help_us_grow, 180, 180));
        servicesImageView.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.home_services_image, 180, 180));
        testimonialsImageView.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.testimonials_image, 180, 180));
        newsImageView.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.home_news_image, 180, 180));

    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        aboutUsCardView.setOnClickListener(aboutUsCardViewListener);
        servicesCardView.setOnClickListener(servicesCardViewListener);
        newsCardView.setOnClickListener(newsCardViewListener);
        testimonialsCardView.setOnClickListener(testimonialsListener);
    }

    /**
     * OnClickListener for aboutLinearLayoutListener
     */
    View.OnClickListener aboutUsCardViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.aboutUsFragment, R.string.fragment_about);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_about);
        }
    };

    /**
     * OnClickListener for servicesLinearLayoutListener
     */
    View.OnClickListener servicesCardViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.servicesFragment, R.string.fragment_services);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_services);
        }
    };

    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener testimonialsListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Util.replaceFragment(mainActivity.testimonialFragment, R.string.fragment_testimonials);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_testimonials);
        }
    };


    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener newsCardViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Util.replaceFragment(mainActivity.newsTabFragment, R.string.fragment_news);
            //set navigation selected to current fragment
            mainActivity.setSelectedNavigationItem(R.id.nav_news);
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
