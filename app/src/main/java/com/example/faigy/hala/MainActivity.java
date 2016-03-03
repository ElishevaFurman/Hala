package com.example.faigy.hala;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // Declare controls
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    // Declare Fragments
    HomeFragment homeFragment;
    ContactFragment contactFragment;
    ContactFormFragment contactFormFragment;
    ContactUsFragment contactUsFragment;
    TestimonialFragment testimonialFragment;
    TeamListFragment teamListFragment;
    OurTeamFragment ourTeamFragment;
    AskFragment askFragment;
    FAQFragment faqFragment;
    InTheNewsFragment inTheNewsFragment;
    ServicesFragment servicesFragment;
    HelpUsGrowFragment helpUsGrowFragment;
    DonateFragment donateFragment;
    ServiceDetailFragment serviceDetailFragment;
    AboutUsFragment aboutUsFragment;
    AppointmentFragment appointmentFragment;
    protected MyApplication app;
    DataBaseOperations dataBaseOperations;
    NewsTabFragment newsTabFragment;


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the parent activities onCreate
        super.onCreate(savedInstanceState);
        // attach xml to activity
        setContentView(R.layout.activity_main);
        // send activity reference to Util class
        Util.setReference(this);
        initializeViews();
        initializeFragments();
        inflateScrollViewWithFragment();




        app = (MyApplication) getApplication();

//        dataBaseOperations.makeJsonArrayRequest("news","http://162.243.100.186/news_array.php");
        dataBaseOperations.makeJsonArrayRequest("members","http://162.243.100.186/members_array.php");
        // startAlarm();
        //EventBus myEventBus = EventBus.getDefault();




    }



    public void startAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(), 1 * 60 * 1000, alarmIntent);
        //DataBaseOperations.taskGetLocations = new GetLocations().execute();
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
        contactFormFragment = new ContactFormFragment();
        contactFormFragment.setMainActivity(this);
        contactUsFragment = new ContactUsFragment();
        contactUsFragment.setMainActivity(this);
        testimonialFragment = new TestimonialFragment();
        testimonialFragment.setMainActivity(this);
        teamListFragment = new TeamListFragment();
        teamListFragment.setMainActivity(this);
        ourTeamFragment = new OurTeamFragment();
        ourTeamFragment.setMainActivity(this);
        askFragment = new AskFragment();
        askFragment.setMainActivity(this);
        faqFragment = new FAQFragment();
        faqFragment.setMainActivity(this);
        inTheNewsFragment = new InTheNewsFragment();
        inTheNewsFragment.setMainActivity(this);
        servicesFragment = new ServicesFragment();
        servicesFragment.setMainActivity(this);
        helpUsGrowFragment = new HelpUsGrowFragment();
        helpUsGrowFragment.setMainActivity(this);
        donateFragment = new DonateFragment();
        donateFragment.setMainActivity(this);
        serviceDetailFragment = new ServiceDetailFragment();
        serviceDetailFragment.setMainActivity(this);
        aboutUsFragment = new AboutUsFragment();
        aboutUsFragment.setMainActivity(this);
        appointmentFragment = new AppointmentFragment();
        appointmentFragment.setMainActivity(this);
        dataBaseOperations = new DataBaseOperations(this);
        //dataBaseOperations.setMainActivity(this);
        newsTabFragment = new NewsTabFragment();
        newsTabFragment.setMainActivity(this);
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
//        } else if (id == R.id.nav_grow) {
//            Util.replaceFragment(helpUsGrowFragment, R.string.fragment_grow);
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


    public ArrayList<News> getNewsArrayList() {
        return app.getNewsArrayList();
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        app.setNewsArrayList(newsArrayList);
    }

    // This method will be called when a HelloWorldEvent is posted
    public void onEvent(DownloadDataEvent event){
        // your implementation
       // Toast.makeText(Util.getContext(), event.getTag(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {

        EventBus.getDefault().unregister(this);
        super.onPause();
    }


}


