package com.example.faigy.hala;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.Adapters.FAQExpandableAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

public class FAQFragment extends Fragment {
    // Declare Controls
    RecyclerView recyclerView;
    FAQExpandableAdapter mAdapter;
    FloatingActionButton fab;
    ArrayList<Faqs> faqArrayList;
    MainActivity mainActivity;
    DatabaseOperations databaseOperations;

    Faqs[] faqsData;

    private static String TAG = "json_faq_request";

    String url = "http://162.243.100.186/faqs_array.php";
    ProgressDialog pDialog;
    public static final int COLLAPSE_MODE_PARALLAX=2;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TextView errorTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        databaseOperations = new DatabaseOperations();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_faq2, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_faqs);

        return rootView;
    }

    public void initializeViews(final View rootView) {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mainActivity.openNavigationDrawer2(toolbar, R.drawable.ic_menu_24dp);
        mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Faq's");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        fab = (FloatingActionButton) rootView.findViewById(R.id.askFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("ASK").replace(R.id.container,
                        mainActivity.askFragment).commit();
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FAQExpandableAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
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


        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshFragment();
            }
        });
    }
    public void refreshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
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
        mainActivity.getSupportActionBar().show();
        mainActivity.openNavigationDrawer();
        toolbar.hideOverflowMenu();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.getSupportActionBar().hide();
    }

}



