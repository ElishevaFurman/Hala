package com.example.faigy.hala;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Home on 2/1/2016.
 */
public class OurTeamFragment extends Fragment {
    MainActivity mainActivity;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;

    public OurTeamFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_our_team, container, false);
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_team, mainActivity.toolbar);
        return rootView;
    }

    public void initializeViews(View rootView) {
        viewPager = (ViewPager) rootView.findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag((new TeamListFragment()), "TEAM");
        adapter.addFrag((new TeamListFragment()), "BOARD");
        adapter.addFrag((new TeamListFragment()), "FRIENDS");
        viewPager.setAdapter(adapter);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
