package com.example.faigy.hala.Activities;

import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.faigy.hala.Utilities.DatabaseOperations;
import com.example.faigy.hala.Fragments.AboutUsFragment;
import com.example.faigy.hala.Fragments.AppointmentFragment;
import com.example.faigy.hala.Fragments.ContactFragment;
import com.example.faigy.hala.Fragments.ContactUsFragment;
import com.example.faigy.hala.Fragments.DonateFragment;
import com.example.faigy.hala.Fragments.FAQFragment;
import com.example.faigy.hala.Fragments.FormFragment;
import com.example.faigy.hala.Fragments.HomeFragment;
import com.example.faigy.hala.Fragments.InTheNewsFragment;
import com.example.faigy.hala.Fragments.NewsTabFragment;
import com.example.faigy.hala.Fragments.ServiceDetailFragment;
import com.example.faigy.hala.Fragments.ServicesFragment;
import com.example.faigy.hala.Fragments.TeamListFragment;
import com.example.faigy.hala.Fragments.TestimonialFragment;
import com.example.faigy.hala.Classes.MyApplication;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;

import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declare controls
    public Toolbar toolbar;
    public DrawerLayout drawer;
    NavigationView navigationView;

    // Declare Fragments
    HomeFragment homeFragment;
    public ContactFragment contactFragment;
    public FormFragment contactFormFragment;
    public ContactUsFragment contactUsFragment;
    TestimonialFragment testimonialFragment;
    TeamListFragment teamListFragment;
    FAQFragment faqFragment;
    InTheNewsFragment inTheNewsFragment;
    public ServicesFragment servicesFragment;
    public DonateFragment donateFragment;
    public ServiceDetailFragment serviceDetailFragment;
    public AboutUsFragment aboutUsFragment;
    AppointmentFragment appointmentFragment;
    protected MyApplication app;
    NewsTabFragment newsTabFragment;
    DatabaseOperations databaseOperations;

    // Declare variables
    String location;

    @Override
    public void onResume() {
        super.onResume();
//        if (!Locale.getDefault().getLanguage().equals(location))
//            Toast.makeText(Util.getActivity(),location, Toast.LENGTH_LONG).show();
            //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the parent activities onCreate
        super.onCreate(savedInstanceState);
        if (!Locale.getDefault().getLanguage().equals("en")){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        // attach xml to activity
        setContentView(R.layout.activity_main);

        // send activity reference to Util class
        Util.setReference(this);

        // check if phone is connected to internet or not
        if (!Util.isConnected()) {
            // if its not connected, show dialog:"No connection found. Please connect to internet"
            Util.createDialog(R.string.internet_connection, R.string.internet_message, R.string.connect, R.string.cancel, "internet", null);
        }

        initializeViews();
        initializeFragments();
        inflateScrollViewWithFragment();
        app = (MyApplication) getApplication();
    }


    /**
     * Function to initialize controls
     */
    public void initializeViews() {
        // initialize and reference controls
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert getSupportActionBar() != null;
        // designate toolbar as the action bar for this activity
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        openNavigationDrawer();
        disableNavigationViewScrollbars(navigationView);

        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Util.hideSoftKeyboard();
            }
        });
    }

    public void openNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        Util.hideSoftKeyboard();

    }

    public void openNavigationDrawer2(Toolbar toolbar, int icon) {
        toolbar.setNavigationIcon(icon);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        Util.hideSoftKeyboard();

    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    /**
     * @param item - position of item to be selected to
     */
    public void setSelectedNavigationItem(int item) {
        // navigationView.getMenu().getItem(item).setChecked(true);
        navigationView.setCheckedItem(item);
    }

    /**
     * Function to instantiate fragments
     */
    public void initializeFragments() {
        homeFragment = new HomeFragment();
        homeFragment.setMainActivity(this);
        contactFragment = new ContactFragment();
        contactFragment.setMainActivity(this);
        contactFormFragment = new FormFragment();
        contactFormFragment.setMainActivity(this);
        contactUsFragment = new ContactUsFragment();
        contactUsFragment.setMainActivity(this);
        testimonialFragment = new TestimonialFragment();
        testimonialFragment.setMainActivity(this);
        teamListFragment = new TeamListFragment();
        teamListFragment.setMainActivity(this);
        faqFragment = new FAQFragment();
        faqFragment.setMainActivity(this);
        inTheNewsFragment = new InTheNewsFragment();
        inTheNewsFragment.setMainActivity(this);
        servicesFragment = new ServicesFragment();
        servicesFragment.setMainActivity(this);
        donateFragment = new DonateFragment();
        donateFragment.setMainActivity(this);
        serviceDetailFragment = new ServiceDetailFragment();
        serviceDetailFragment.setMainActivity(this);
        aboutUsFragment = new AboutUsFragment();
        aboutUsFragment.setMainActivity(this);
        appointmentFragment = new AppointmentFragment();
        appointmentFragment.setMainActivity(this);
        newsTabFragment = new NewsTabFragment();
        newsTabFragment.setMainActivity(this);
        databaseOperations = new DatabaseOperations();
    }

    /**
     * Function to inflate scroll view with fragment
     */
    private void inflateScrollViewWithFragment() {
        // inflate scrollView with dashboardFragment
        getFragmentManager().beginTransaction().replace(R.id.container,
                homeFragment).addToBackStack("Home").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        // if there are fragments in the back stack
        else if (getFragmentManager().getBackStackEntryCount() > 1) {
            // undo the last back stack transaction
            getFragmentManager().popBackStack();
        } else {
            // finish this activity
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Util.replaceFragment(homeFragment, R.string.fragment_home);
        } else if (id == R.id.nav_about) {
            Util.replaceFragment(aboutUsFragment, R.string.fragment_about);
        } else if (id == R.id.nav_team) {
            Util.replaceFragment(teamListFragment, R.string.fragment_team);
        } else if (id == R.id.nav_services) {
            Util.replaceFragment(servicesFragment, R.string.fragment_services);
        } else if (id == R.id.nav_news) {
            Util.replaceFragment(newsTabFragment, R.string.fragment_news);
        } else if (id == R.id.nav_testimonials) {
            Util.replaceFragment(testimonialFragment, R.string.fragment_testimonials);
        } else if (id == R.id.nav_donate) {
            Util.replaceFragment(donateFragment, R.string.fragment_donate);
        } else if (id == R.id.nav_appointments) {
            Util.replaceFragment(appointmentFragment, R.string.fragment_appointment);
        } else if (id == R.id.nav_contact) {
            Util.replaceFragment(contactUsFragment, R.string.fragment_contact);
        } else if (id == R.id.nav_faqs) {
            Util.replaceFragment(faqFragment, R.string.fragment_faq);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//
//    @Override
//    public void onPause() {
//
//        super.onPause();
//        location = Locale.getDefault().getLanguage();
//
//    }
//
//    @Override
//    public void onStop() {
//
//        super.onStop();
//        location = Locale.getDefault().getLanguage();
//
//    }
//
//    @Override
//    public void onStart() {
//
//        super.onStart();
//        if (Locale.getDefault().getLanguage().equals("ar")){
//            Toast.makeText(Util.getActivity(),"same language", Toast.LENGTH_LONG).show();
//            finish();
//        }else{
//            Toast.makeText(Util.getActivity(),"diff language", Toast.LENGTH_LONG).show();
//
//        }
//
//
//    }

}


