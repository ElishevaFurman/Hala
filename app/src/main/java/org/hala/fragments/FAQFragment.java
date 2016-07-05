package org.hala.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import org.hala.adapters.FAQExpandableAdapter;
import org.hala.classes.Faqs;
import org.hala.utilities.DatabaseOperations;
import org.hala.activities.MainActivity;
import org.hala.classes.MySingleton;
import org.hala.R;
import org.hala.utilities.Util;
import org.hala.utilities.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class FAQFragment extends Fragment {

    // Declare Controls
    RecyclerView recyclerView;
    FAQExpandableAdapter mAdapter;
    FloatingActionButton fab;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TextView errorTextView;
    ImageView faqImageHeader;

    // Declare variables
    ArrayList<Faqs> faqArrayList;
    Faqs[] faqsData;
    String TAG = "json_faq_request";
    String url;

    // Declare classes
    MainActivity mainActivity;
    DatabaseOperations databaseOperations;

    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instantiate databaseOperations
        databaseOperations = new DatabaseOperations();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        // Initialize the views for this fragment
        initializeViews(rootView);

        // set up recyclerView
        setupRecyclerView();

        // set title for toolbar
        //Util.setToolbarTitle(R.string.fragment_faq, mainActivity.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_faq));

        mainActivity.openNavigationDrawer();

        // download data from url
        downloadData();

        // register listeners for controls
        registerListeners();

        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_faqs);

        animate();
        return rootView;
    }

    public void initializeViews(final View rootView) {
        // initialize and reference CollapsingToolbar
        collapsingToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);

        // initialize and reference Fab
        fab = (FloatingActionButton) rootView.findViewById(R.id.askFab);

        // initialize and reference RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // initialize and reference TextView
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);

        // initialize and reference ImageView
        faqImageHeader = (ImageView) rootView.findViewById(R.id.faqImageHeader);

        // set imageBitmap of faqImageHeader
        faqImageHeader.setImageBitmap(Util.decodeSampledBitmapFromResource(getResources(),
                R.drawable.ask, 180, 180));
    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {
        // instantiate mAdapter
        mAdapter = new FAQExpandableAdapter(getActivity());
        // initialize linearLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // setHasFixedSize to false
        recyclerView.setHasFixedSize(false);
        // attach the adapter to the recyclerView to populate items
        recyclerView.setAdapter(mAdapter);
    }


    /**
     * Function to download data from url
     */
    public void downloadData() {

        // set url data to corresponding language of phone settings
        if (!Locale.getDefault().getLanguage().equals("en")) {
            url = "http://162.243.100.186/faqs_array_he.php";
        } else {
            url = "http://162.243.100.186/faqs_array.php";
        }

        // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
        databaseOperations.makeJsonArrayRequest(url, TAG, errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        // initialize gson object
                        Gson gson = new Gson();
                        try {
                            // convert json array into array of class type
                            faqsData = gson.fromJson(result, Faqs[].class);
                            // convert array to arrayList
                            faqArrayList = new ArrayList<>(Arrays.asList(faqsData));
                            // set list to adapter
                            mAdapter.setFaqList(faqArrayList);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        errorTextView.setOnClickListener(errorTextViewListener);
        fab.setOnClickListener(fabListener);
    }

    /**
     * OnClickListener for errorTextView
     */
    View.OnClickListener errorTextViewListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            errorTextView.setText("");
            // download data from url
            downloadData();
        }
    };

    /**
     * OnClickListener for fab
     */
    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // switch to ask fragment
            getFragmentManager().beginTransaction().addToBackStack("ASK").replace(R.id.container,
                     mainActivity.contactFormFragment).commit();
        }
    };


    /**
     * Function to post data to the server
     */
    public void postData() {

        // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
        databaseOperations.post("http://162.243.100.186/faqs_post.php", TAG, errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {

                    }
                });
    }

    public void animate(){
        ObjectAnimator scalex = ObjectAnimator.ofFloat(fab, "scaleX", 0, 1);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(fab, "scaleY", 0, 1);
        AnimatorSet scaleFab = new AnimatorSet();
        scaleFab.playTogether(scalex, scaley);

        int titleStartValue = collapsingToolbar.getTop();
        int titleEndValue = collapsingToolbar.getBottom();
        ObjectAnimator animatorTitle = ObjectAnimator.ofInt(collapsingToolbar, "bottom", titleStartValue);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(scaleFab);
        set.start();
    }



    /**
     * Function to set fragment to this main activity
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPause() {
        super.onPause();
        // set lastFragment to "faqFragment" in MySingleton class
        MySingleton.getInstance().setLastFragment("faqFragment");

    }

}



