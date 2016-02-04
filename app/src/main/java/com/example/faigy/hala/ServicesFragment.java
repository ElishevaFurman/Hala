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
        return rootView;
    }

    public void initializeViews(View rootView){
//        CardView card = (CardView) rootView.findViewById(R.id.cardViewServices);
//        card.setCardBackgroundColor(R.color.colorAccentLight);

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
