package com.example.faigy.hala;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContactFormFragment extends Fragment {

    // Declare Controls
    MainActivity mainActivity;

    public ContactFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_form, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_contact, mainActivity.toolbar);
        // set back icon on toolbar
        Util.enableBackButton(R.drawable.ic_arrow_back_24dp, mainActivity.toolbar, mainActivity.drawer);

        return rootView;

    }

    public void initializeViews(View rootView) {


    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


}

