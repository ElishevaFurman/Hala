package com.example.faigy.hala.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.*;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faigy.hala.Activities.MainActivity;
import com.example.faigy.hala.Classes.MySingleton;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;

import java.lang.reflect.Field;

public class ContactUsFragment extends Fragment {

    // Declare controls
    TabLayout tabLayout;
    ViewPager viewPager;

    // Declare activities
    MainActivity mainActivity;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    // Declare variables
    private static final Field sChildFragmentManagerField;
    private static final String LOGTAG = "a";

    static {
        Field f = null;
        try {
            f = Fragment.class.getDeclaredField("mChildFragmentManager");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.e(LOGTAG, "Error getting mChildFragmentManager field", e);
        }
        sChildFragmentManagerField = f;
    }

    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_contact, mainActivity.toolbar);
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_contact);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        MySingleton.getInstance().setLastFragment("contactFragment");
        // initialize and reference controls
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.our_location));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.contact_form));
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() != 1) {
                    Util.hideSoftKeyboard();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
                    ContactFragment tab1 = new ContactFragment();
                    return tab1;
                case 1:
                    FormFragment tab2 = new FormFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            //do nothing here! no call to super.restoreState(arg0, arg1);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

        if (sChildFragmentManagerField != null) {
            try {
                sChildFragmentManagerField.set(this, null);
            } catch (Exception e) {
                Log.e(LOGTAG, "Error setting mChildFragmentManager field", e);
            }
        }
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
