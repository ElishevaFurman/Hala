package com.example.faigy.hala;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    HomeFragment homeFragment;
    ContactFragment contactFragment;
    AboutUsFragment aboutUsFragment;
    TestimonialFragment testimonialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeFragments();
    }


    public void initializeViews(){
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

    public void initializeFragments(){
        homeFragment = new HomeFragment();
        homeFragment.setMainActivity(this);
        contactFragment = new ContactFragment();
        contactFragment.setMainActivity(this);
        aboutUsFragment = new AboutUsFragment();
        aboutUsFragment.setMainActivity(this);
        testimonialFragment = new TestimonialFragment();
        testimonialFragment.setMainActivity(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            getFragmentManager().beginTransaction().replace(R.id.container,
                    homeFragment).addToBackStack("Home").commit();

        } else if (id == R.id.nav_about) {
            getFragmentManager().beginTransaction().replace(R.id.container,
                    aboutUsFragment).addToBackStack("About us").commit();

        } else if (id == R.id.nav_team) {

        } else if (id == R.id.nav_services) {

        } else if (id == R.id.nav_news) {

        } else if (id == R.id.nav_testimonials) {
            getFragmentManager().beginTransaction().replace(R.id.container,
                    testimonialFragment).addToBackStack("Testimonial").commit();

        } else if (id == R.id.nav_grow) {

        } else if (id == R.id.nav_donate) {

        } else if (id == R.id.nav_contact) {
            getFragmentManager().beginTransaction().replace(R.id.container,
                    contactFragment).addToBackStack("Contact").commit();

        } else if (id == R.id.nav_faqs) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
