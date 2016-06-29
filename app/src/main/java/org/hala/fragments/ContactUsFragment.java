package org.hala.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.*;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hala.activities.MainActivity;
import org.hala.classes.MySingleton;
import org.hala.R;
import org.hala.utilities.Util;

import java.lang.reflect.Field;

public class ContactUsFragment extends Fragment {
    // Declare activities
    MainActivity mainActivity;

    // Declare controls
    TabLayout tabLayout;
    ViewPager viewPager;

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
        //Util.setToolbarTitle(R.string.fragment_contact, mainActivity.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_contact));

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_contact);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // set last fragment in MySingleton class
        MySingleton.getInstance().setLastFragment("contactFragment");

        // initialize and reference controls
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.our_location));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.contact_form));
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        // set adapter
        viewPager.setAdapter(new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount()));
        // add OnPageChangeListener to tabLayout
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // set onTabSelectedListener for tabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // set currentItem of viewpager to tabs position
                viewPager.setCurrentItem(tab.getPosition());
                // if position of tab is not equal to 1
                if (tab.getPosition() != 1) {
                    // hide soft keyboard
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

    // PagerAdapter class
    public class PagerAdapter extends FragmentStatePagerAdapter {
        // declare variables
        int mNumOfTabs;

        //constructor
        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        /**
         *
         * @param position - of type int
         * @return Fragment
         */
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    // initialize tab1
                    return new ContactFragment();
                case 1:
                    // initialize tab2
                    return new FormFragment();
                default:
                    return null;
            }
        }

        /**
         * Function that returns the number of tabs
         * @return int
         */
        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            //do nothing here! no call to super.restoreState(arg0, arg1);
        }
    }


//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//        if (sChildFragmentManagerField != null) {
//            try {
//                sChildFragmentManagerField.set(this, null);
//            } catch (Exception e) {
//                Log.e(LOGTAG, "Error setting mChildFragmentManager field", e);
//            }
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
