package com.example.faigy.hala;

import android.support.v4.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declare controls
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    // Declare Fragments
    HomeFragment homeFragment;
    ContactFragment contactFragment;
    AboutUsFragment aboutUsFragment;
    TestimonialFragment testimonialFragment;
    TeamListFragment teamListFragment;

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
        //inflate fragment_home
        getFragmentManager().beginTransaction().replace(R.id.container,
                homeFragment).addToBackStack("Home").commit();
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews() {
        // initialize and reference controls
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Function to instantiate fragments
     */
    public void initializeFragments() {
        homeFragment = new HomeFragment();
        homeFragment.setMainActivity(this);
        contactFragment = new ContactFragment();
        contactFragment.setMainActivity(this);
        aboutUsFragment = new AboutUsFragment();
        aboutUsFragment.setMainActivity(this);
        testimonialFragment = new TestimonialFragment();
        testimonialFragment.setMainActivity(this);
        teamListFragment = new TeamListFragment();
        teamListFragment.setMainActivity(this);

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
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
            getFragmentManager().beginTransaction().replace(R.id.container,
                    homeFragment).addToBackStack("Home").commit();

        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    aboutUsFragment).addToBackStack("About us").commit();

        } else if (id == R.id.nav_team) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
            getFragmentManager().beginTransaction().replace(R.id.container,
                    teamListFragment).addToBackStack("Team List Fragment").commit();
        } else if (id == R.id.nav_services) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
        } else if (id == R.id.nav_news) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
        } else if (id == R.id.nav_testimonials) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
            getFragmentManager().beginTransaction().replace(R.id.container,
                    testimonialFragment).addToBackStack("Testimonial").commit();
        } else if (id == R.id.nav_grow) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
        } else if (id == R.id.nav_donate) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
        } else if (id == R.id.nav_contact) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
            getFragmentManager().beginTransaction().replace(R.id.container,
                    contactFragment).addToBackStack("Contact").commit();
        } else if (id == R.id.nav_faqs) {
            if (aboutUsFragment != null && aboutUsFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(aboutUsFragment).commit();
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
