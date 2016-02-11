package com.example.faigy.hala;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Home on 1/21/2016.
 */
public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    // Declare Controls
    LinearLayout aboutLinearLayout, servicesLinearLayout, contactLinearLayout;
    ImageView homeImageView;

    public HomeFragment(){

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
        registerListeners();
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_home, mainActivity.toolbar);

        return rootView;
    }

    public void initializeViews(View rootView) {
       aboutLinearLayout = (LinearLayout)rootView.findViewById(R.id.aboutLinearLayout);
       servicesLinearLayout = (LinearLayout)rootView.findViewById(R.id.servicesLinearLayout);
       contactLinearLayout = (LinearLayout)rootView.findViewById(R.id.contactLinearLayout);
        homeImageView = (ImageView) rootView.findViewById(R.id.homeImageView);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getResources(), R.drawable.home_pg, options);
//        int imageHeight = options.outHeight;
//        int imageWidth = options.outWidth;
//        String imageType = options.outMimeType;

        homeImageView.setImageBitmap(
            Util.decodeSampledBitmapFromResource(getResources(), R.drawable.home_pg, 100, 100));
    }

    public void registerListeners() {
        aboutLinearLayout.setOnClickListener(aboutLinearLayoutListener);
        servicesLinearLayout.setOnClickListener(servicesLinearLayoutListener);
        contactLinearLayout.setOnClickListener(contactLinearLayoutListener);
    }

    /**
     * OnClickListener for aboutLinearLayoutListener
     */
    View.OnClickListener aboutLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
     Util.replaceFragment(mainActivity.aboutUsFragment, R.string.fragment_about);
        }
    };

    /**
     * OnClickListener for servicesLinearLayoutListener
     */
    View.OnClickListener servicesLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Util.replaceFragment(mainActivity.servicesFragment, R.string.fragment_services);
        }
    };

    /**
     * OnClickListener for contactLinearLayoutListener
     */
    View.OnClickListener contactLinearLayoutListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


                Util.replaceFragment(mainActivity.contactUsFragment, R.string.fragment_contact);


        }
    };
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


}
