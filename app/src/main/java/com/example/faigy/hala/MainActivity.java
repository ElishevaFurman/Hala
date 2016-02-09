package com.example.faigy.hala;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    About about;

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
        about = new About();
        about.setMainActivity(this);
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
            Util.replaceFragment(about, R.string.fragment_about);
        } else if (id == R.id.nav_team) {
            Util.replaceFragment(ourTeamFragment, R.string.fragment_team);
        } else if (id == R.id.nav_services) {
            Util.replaceFragment(servicesFragment, R.string.fragment_services);
        } else if (id == R.id.nav_news) {
            Util.replaceFragment(inTheNewsFragment, R.string.fragment_news);
        } else if (id == R.id.nav_testimonials) {
            Util.replaceFragment(testimonialFragment, R.string.fragment_testimonials);
        } else if (id == R.id.nav_grow) {
            Util.replaceFragment(helpUsGrowFragment, R.string.fragment_grow);
        } else if (id == R.id.nav_donate) {
            Util.replaceFragment(donateFragment, R.string.fragment_donate);
        } else if (id == R.id.nav_contact) {
            Util.replaceFragment(contactUsFragment, R.string.fragment_contact);
        } else if (id == R.id.nav_faqs) {
            Util.replaceFragment(faqFragment, R.string.fragment_faq);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
