package com.example.faigy.hala;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import de.greenrobot.event.EventBus;

public class FAQFragment extends Fragment {
    // Declare Controls
    RecyclerView recyclerView;
    FAQExpandableAdapter mAdapter;
    FloatingActionButton fab;
    ArrayList<Faqs> faqArrayList;
    MainActivity mainActivity;


    Faqs[] newsData;
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    private static String TAG = MainActivity.class.getSimpleName();
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

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

        pDialog = new ProgressDialog(Util.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        return rootView;
    }

    public void initializeViews(final View rootView) {


        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mainActivity.openNavigationDrawer2(toolbar, R.drawable.ic_menu_24dp);


        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Faq's");

        fab = (FloatingActionButton) rootView.findViewById(R.id.askFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack("ASK").replace(R.id.container,
                        mainActivity.contactFormFragment).commit();
            }
        });


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        makeJsonArrayRequest("http://162.243.100.186/faqs_array.php");
        recyclerView.setAdapter(mAdapter);
    }

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
//            @Override
//            public void onClick(View view, int position) {
//                // Check for an expanded view, collapse if you find one
//                if (mAdapter.expandedPosition >= 0) {
//                    // set pre to expandedPosition
//                    prev = mAdapter.expandedPosition;
//                    // notify adapter on item changed
//                    mAdapter.notifyItemChanged(prev);
//                }
//                // if position is expanded
//                if (position == mAdapter.expandedPosition) {
//                    // Set the current position to "collapse"
//                    mAdapter.expandedPosition = -1;
//                    // notify adapter on item changed
//                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
//                } else {
//                    // Set the current position to "expanded"
//                    mAdapter.expandedPosition = position;
//                    // notify adapter on item changed
//                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
//                }
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
//    }

    /**
     * Method to make json array request where response starts with
     * */
    public void makeJsonArrayRequest(String urlJsonArry) {

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        Gson gson = new Gson();
                        String jsonOutput = response.toString();
                        try {
                            newsData = gson.fromJson(jsonOutput, Faqs[].class);
                            faqArrayList = new ArrayList<>(Arrays.asList(newsData));
                            mAdapter = new FAQExpandableAdapter(faqArrayList);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(Util.getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(req);

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPause() {

        super.onPause();

        mainActivity.getSupportActionBar().show();
        //Util.setToolbarTitle(R.string.fragment_faq, mainActivity.toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.getSupportActionBar().hide();
    }

    @Override
    public void onStop() {

        super.onStop();
        //mainActivity.getSupportActionBar().show();
        //Util.setToolbarTitle(R.string.fragment_faq, mainActivity.toolbar);
    }


}



