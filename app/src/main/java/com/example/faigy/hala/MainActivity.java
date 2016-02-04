package com.example.faigy.hala;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container,
                    homeFragment).addToBackStack("Home").commit();

        } else if (id == R.id.nav_about) {
            removeFragments(ourTeamFragment);
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.container, aboutUsFragment);
            transaction.addToBackStack("About us");
            // Commit the transaction
            transaction.commit();
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,
//                    aboutUsFragment).addToBackStack("About us").commit();
        } else if (id == R.id.nav_team) {
            if (homeFragment != null && homeFragment.isVisible()) {
                getFragmentManager().beginTransaction().remove(homeFragment).commit();
            }
            removeFragments(aboutUsFragment);
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    ourTeamFragment).addToBackStack("Team List Fragment").commit();
        } else if (id == R.id.nav_services) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container, servicesFragment)
                    .addToBackStack("Services").commit();
        } else if (id == R.id.nav_news) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container,
                    inTheNewsFragment).addToBackStack("In the news").commit();
        } else if (id == R.id.nav_testimonials) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container,
                    testimonialFragment).addToBackStack("Testimonial").commit();
        } else if (id == R.id.nav_grow) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
        } else if (id == R.id.nav_donate) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
        } else if (id == R.id.nav_contact) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container,
                    contactFragment).addToBackStack("Contact").commit();
        } else if (id == R.id.nav_faqs) {
            removeFragments(aboutUsFragment);
            removeFragments(ourTeamFragment);
            getFragmentManager().beginTransaction().replace(R.id.container,
                    faqFragment).addToBackStack("FAQ").commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void removeFragments(Fragment fragment) {
        if (fragment != null && fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
    

}
