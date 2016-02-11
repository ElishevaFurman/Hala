package com.example.faigy.hala;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;


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
        //Util.setToolbarTitle(R.string.fragment_contact, mainActivity.toolbar);

        return rootView;

    }

    public void initializeViews(View rootView) {


    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


}

