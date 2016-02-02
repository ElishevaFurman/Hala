package com.example.faigy.hala;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class AboutUsFragment extends android.support.v4.app.Fragment {
MainActivity mainActivity;
TabLayout tabLayout;
ViewPager viewPager;

    public AboutUsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_about_us, container, false);

        tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Our Mission"));
        tabLayout.addTab(tabLayout.newTab().setText("The Hala Advantage"));

        viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflatedView;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    OneFragment tab1 = new OneFragment();
                    return tab1;
                case 1:
                    TwoFragment tab2 = new TwoFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }


//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        viewPager.setVisibility(View.GONE);
//        tabLayout.setVisibility(View.GONE);
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        viewPager.setVisibility(View.VISIBLE);
//        tabLayout.setVisibility(View.VISIBLE);
//    }
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
