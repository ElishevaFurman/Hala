package com.example.faigy.hala;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Home on 1/25/2016.
 */
public class OurMissionFragment extends Fragment{
AboutUsFragment aboutUsFragment;

    public OurMissionFragment() {
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
        return inflater.inflate(R.layout.fragment_our_mission, container, false);
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        aboutUsFragment.viewPager.setVisibility(View.GONE);
//        aboutUsFragment.tabLayout.setVisibility(View.GONE);
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        aboutUsFragment.viewPager.setVisibility(View.VISIBLE);
//        aboutUsFragment.tabLayout.setVisibility(View.VISIBLE);
//    }
}