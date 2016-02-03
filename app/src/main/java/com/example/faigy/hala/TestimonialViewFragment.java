package com.example.faigy.hala;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestimonialViewFragment extends Fragment {

    // Declare Variables
    MainActivity mainActivity;


    public TestimonialViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testimonial_view, container, false);
    }

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
