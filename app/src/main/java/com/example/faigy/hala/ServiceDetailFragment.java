package com.example.faigy.hala;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceDetailFragment extends Fragment {
    // Declare Controls
    MainActivity mainActivity;
    TextView flashingTextView, serviceDescriptionTextViews;
    ArrayList<Services> servicesArrayList;
    int position;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView image;
    ProgressBar imageProgressBar;
    public ServiceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service_detail2, container, false);
        servicesArrayList = MySingleton.getInstance().getServicesArrayList();
        // Initialize the views for this fragment
        initializeViews(rootView);

        // remove keyboard from screen
        Util.hideSoftKeyboard();
        return rootView;
    }

    public void initializeViews(View rootView) {
        position = MySingleton.getInstance().getPostion();
        image = (ImageView) rootView.findViewById(R.id.imageView1);
        imageProgressBar = (ProgressBar)rootView.findViewById(R.id.imageProgressBar);
        //image.setImageResource(R.drawable.services_details_image2);
        mainActivity.setSupportActionBar((Toolbar) rootView.findViewById(R.id.toolbar));
        collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Collapsing");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        //setPalette();
//
//        TextView serviceDescriptionTextViews = (TextView) rootView.findViewById(R.id.serviceDescriptionTextViews);
//        serviceDescriptionTextViews.setText(mainActivity.servicesFragment.servicesList.get(position).getDescription());

        WebView serviceDescriptionTextViews = (WebView)rootView.findViewById(R.id.serviceDescriptionTextViews);

        String content = String.valueOf(Html
                .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                        + mainActivity.servicesFragment.servicesList.get(position).getDescription()
                        + "</body>]]>"));
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
                        image.setBackgroundResource(R.drawable.ic_add_24dp);
                    }
                });

    }
//        serviceDescriptionTextViews.setText(servicesArrayList.get(0).getDescription());


//        flashingTextView = (TextView) rootView.findViewById(R.id.flashingTextView);
//        flashingTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Util.replaceFragment(mainActivity.helpUsGrowFragment, R.string.fragment_grow);
//            }
//        });
//
//
//        // Set Animation
//        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(1000); //You can manage the blinking time with this parameter
//        anim.setStartOffset(40);
//        anim.setRepeatMode(Animation.REVERSE);
//        anim.setRepeatCount(Animation.INFINITE);
//        flashingTextView.startAnimation(anim);



//    private void setPalette() {
//        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int primaryDark = getResources().getColor(R.color.primary_dark);
//                int primary = getResources().getColor(R.color.primary);
//                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
//                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
//            }
//        });
//
//    }


    @Override
    public void onPause() {

        super.onPause();

        //mainActivity.getSupportActionBar().show();
        //mainActivity.openNavigationDrawer();
        //toolbar.hideOverflowMenu();

        //Util.setToolbarTitle(R.string.fragment_faq, mainActivity.toolbar);
       // MySingleton.getInstance().setServicesDetails(true);
    }

    @Override
    public void onResume() {
        super.onResume();
       mainActivity.getSupportActionBar().hide();
        String title = mainActivity.servicesFragment.servicesList.get(position).getTitle();
        // set toolbar title
        Util.setToolbarTitle(title, mainActivity.toolbar);
        //set back button on toolbar
        Util.enableBackButton(R.drawable.ic_arrow_back_24dp, mainActivity.toolbar, mainActivity.drawer);
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
