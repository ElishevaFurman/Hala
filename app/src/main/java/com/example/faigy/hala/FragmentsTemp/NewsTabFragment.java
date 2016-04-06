package com.example.faigy.hala.FragmentsTemp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faigy.hala.AdaptersTemp.ViewPagerAdapter;
import com.example.faigy.hala.ActivitiesTemp.MainActivity;
import com.example.faigy.hala.R;
import com.example.faigy.hala.UtilitiesTemp.Util;
import com.example.faigy.hala.UtilitiesTemp.CustomViewPager;

import java.lang.reflect.Field;

public class NewsTabFragment extends Fragment {
    // Declare controls
    MainActivity mainActivity;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    CustomViewPager customViewPager;
    int id;

    public NewsTabFragment() {
        // Required empty public constructor
    }

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
        View rootView = inflater.inflate(R.layout.fragment_in_the_news_tabs, container, false);
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_news, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_news);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize viewpager
        customViewPager = (CustomViewPager) rootView.findViewById(R.id.tabanim_viewpager);
        setupViewPager(this.customViewPager);

        // initialize tabs
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabanim_tabs);

        tabLayout.setupWithViewPager(this.customViewPager);
        this.customViewPager.beginFakeDrag();

        // disable swiping in viewpager
        customViewPager.setPagingEnabled(false);

        // set on click listener for tabs
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                NewsTabFragment.this.customViewPager.setTag(tab.getPosition());
                NewsTabFragment.this.customViewPager.setAdapter(adapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    /**
     * Function to setup ViewPager
     *
     * @param viewPager - set viewPager
     */
    private void setupViewPager(final ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        // add 3 tab fragments to the adapter
        adapter.addFrag((new InTheNewsFragment()), Util.getContext().getString(R.string.news_tab1));
        adapter.addFrag((new InTheNewsFragment()), Util.getContext().getString(R.string.news_tab2));
        adapter.addFrag((new InTheNewsFragment()), Util.getContext().getString(R.string.news_tab3));
        adapter.addFrag((new InTheNewsFragment()), Util.getContext().getString(R.string.news_tab4));
        viewPager.setTag("0");
        viewPager.setAdapter(adapter);
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
