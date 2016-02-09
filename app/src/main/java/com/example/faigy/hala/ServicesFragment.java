package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    //Declare Controls
    MainActivity mainActivity;



    public ServicesFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services, container, false);
        // Initialize the views for tis fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_services, mainActivity.toolbar);
        // set navigation drawer to toggle
        mainActivity.openNavigationDrawer();

        return rootView;
    }

    public void initializeViews(View rootView){
        CardView card = (CardView) rootView.findViewById(R.id.tomosynthesisCardView);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.replaceFragment(mainActivity.serviceDetailFragment, R.string.fragment_service_Detail);
            }
        });

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
