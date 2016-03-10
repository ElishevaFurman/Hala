//package com.example.faigy.hala;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.lang.reflect.Field;
//
///**
// * Created by Home on 2/1/2016.
// */
//public class OurTeamFragment extends Fragment {
//    MainActivity mainActivity;
//    ViewPager customViewPager;
//    TabLayout tabLayout;
//    ViewPagerAdapter adapter;
//
//    public OurTeamFragment() {
//        // Required empty public constructor
//    }
//
//    private static final Field sChildFragmentManagerField;
//
//    private static final String LOGTAG = "a";
//
//    static {
//        Field f = null;
//        try {
//            f = Fragment.class.getDeclaredField("mChildFragmentManager");
//            f.setAccessible(true);
//        } catch (NoSuchFieldException e) {
//            Log.e(LOGTAG, "Error getting mChildFragmentManager field", e);
//        }
//        sChildFragmentManagerField = f;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_our_team, container, false);
//        initializeViews(rootView);
//        // set toolbar title
//        Util.setToolbarTitle(R.string.fragment_team, mainActivity.toolbar);
//        // remove keyboard from screen
//        Util.hideSoftKeyboard();
//        //set navigation selected to current fragment
//        mainActivity.setSelectedNavigationItem(R.id.nav_team);
//        return rootView;
//    }
//
//    /**
//     * Function to initialize controls
//     */
//    public void initializeViews(View rootView) {
//        customViewPager = (ViewPager) rootView.findViewById(R.id.tabanim_viewpager);
//        setupViewPager(customViewPager);
//        tabLayout = (TabLayout) rootView.findViewById(R.id.tabanim_tabs);
//        tabLayout.setupWithViewPager(customViewPager);
//    }
//
//    /**
//     * Function to setup ViewPager
//     * @param customViewPager
//     */
//    private void setupViewPager(ViewPager customViewPager) {
//        adapter = new ViewPagerAdapter(getChildFragmentManager());
//        adapter.addFrag((new TeamListFragment()), "TEAM");
//        adapter.addFrag((new TeamListFragment()), "BOARD");
//        adapter.addFrag((new TeamListFragment()), "FRIENDS");
//        customViewPager.setAdapter(adapter);
//    }
//
//
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
//
//    public void setMainActivity(MainActivity mainActivity) {
//        this.mainActivity = mainActivity;
//    }
//}
