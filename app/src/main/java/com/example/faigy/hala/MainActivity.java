package com.example.faigy.hala;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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
    OurTeamFragment ourTeamFragment;
    AskFragment askFragment;
    FAQFragment faqFragment;
    TestimonialViewFragment testimonialViewFragment;
    InTheNewsFragment inTheNewsFragment;
    InTheNewsArticleFragment inTheNewsArticleFragment;
    ServicesFragment servicesFragment;
    HelpUsGrowFragment helpUsGrowFragment;
    DonateFragment donateFragment;
    ServiceDetailFragment serviceDetailFragment;

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
        //setupToolbar();
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
    }

    public void openNavigationDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
        testimonialViewFragment = new TestimonialViewFragment();
        testimonialViewFragment.setMainActivity(this);
        teamListFragment = new TeamListFragment();
        teamListFragment.setMainActivity(this);
        ourTeamFragment = new OurTeamFragment();
        ourTeamFragment.setMainActivity(this);
        askFragment = new AskFragment();
        askFragment.setMainActivity(this);
        faqFragment = new FAQFragment();
        faqFragment.setMainActivity(this);
        testimonialViewFragment = new TestimonialViewFragment();
        testimonialViewFragment.setMainActivity(this);
        inTheNewsFragment = new InTheNewsFragment();
        inTheNewsFragment.setMainActivity(this);
        inTheNewsArticleFragment = new InTheNewsArticleFragment();
        inTheNewsArticleFragment.setMainActivity(this);
        servicesFragment = new ServicesFragment();
        servicesFragment.setMainActivity(this);
        helpUsGrowFragment = new HelpUsGrowFragment();
        helpUsGrowFragment.setMainActivity(this);
        donateFragment = new DonateFragment();
        donateFragment.setMainActivity(this);
        serviceDetailFragment = new ServiceDetailFragment();
        serviceDetailFragment.setMainActivity(this);
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
            Util.replaceFragment(homeFragment, "Home");
        } else if (id == R.id.nav_about) {
            Util.replaceFragment(aboutUsFragment, "About us");
        } else if (id == R.id.nav_team) {
            Util.replaceFragment(ourTeamFragment, "Team List Fragment");
        } else if (id == R.id.nav_services) {
            Util.replaceFragment(servicesFragment, "Services");
        } else if (id == R.id.nav_news) {
            Util.replaceFragment(inTheNewsFragment, "In the news");
        } else if (id == R.id.nav_testimonials) {
            Util.replaceFragment(testimonialFragment, "Testimonials");
        } else if (id == R.id.nav_grow) {
            Util.replaceFragment(helpUsGrowFragment, "Help us grow");
        } else if (id == R.id.nav_donate) {
            Util.replaceFragment(donateFragment, "Donate now");
        } else if (id == R.id.nav_contact) {
            Util.replaceFragment(contactFragment, "Contact");
        } else if (id == R.id.nav_faqs) {
            Util.replaceFragment(faqFragment, "FAQ");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
