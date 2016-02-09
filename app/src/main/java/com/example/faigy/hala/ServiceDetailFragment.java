package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceDetailFragment extends Fragment {
    // Declare Controls
    MainActivity mainActivity;
    TextView flashingTextView;


    public ServiceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_service_detail, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle("Tomosynthesis", mainActivity.toolbar);
        //set back button on toolbar
        Util.enableBackButton(R.drawable.ic_arrow_back_24dp, mainActivity.toolbar, mainActivity.drawer);

        return rootView;
    }

    public void initializeViews(View rootView) {
        flashingTextView = (TextView) rootView.findViewById(R.id.flashingTextView);
        flashingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.replaceFragment(mainActivity.helpUsGrowFragment, "Help Us Grow");
            }
        });


        // Set Animation
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        flashingTextView.startAnimation(anim);

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
