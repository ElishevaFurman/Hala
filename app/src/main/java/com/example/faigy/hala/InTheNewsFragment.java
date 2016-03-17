package com.example.faigy.hala;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.Adapters.NewsAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Home on 2/3/2016.
 */
public class InTheNewsFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    public NewsAdapter mAdapter;
    TextView errorTextView;

    // Declare variables
    News[] newsData;
    ArrayList<News> newsList;
    String tabSelected;
    public static String TAG ="json_news_request";
    String url = "http://162.243.100.186/news_array.php";

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;
    DatabaseOperations databaseOperations;

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
        View rootView = inflater.inflate(R.layout.fragment_in_the_news, container, false);
        // get the position of the tab in the layout
        tabSelected = container.getTag().toString();

        // Initialize the views for this fragment
        initializeViews(rootView);

        // set up recyclerView
        setupRecyclerView();

        // download data from url
        downloadData();

        // register listeners for controls
        registerListeners();

        // set toolbar title
        //Util.setToolbarTitle(R.string.fragment_news, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        // mainActivity.setSelectedNavigationItem(R.id.nav_news);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference TextView
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);

        // initialize and reference RecyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {
        // instantiate mAdapter
        mAdapter = new NewsAdapter(getActivity());
        // initialize linearLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // attach the adapter to the recyclerView to populate items
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Function to register listeners
     */
    public void registerListeners() {
        // set onClickListeners
        errorTextView.setOnClickListener(errorTextViewListener);
    }

    /**
     * OnClickListener for errorTextView
     */
    View.OnClickListener errorTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // download data from url
            downloadData();
        }
    };

    /**
     * Function to download data from url
     */
    public void downloadData() {
        // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
        databaseOperations.makeJsonArrayRequest(url, TAG, errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        // initialize gson object
                        Gson gson = new Gson();
                        try {
                            // convert json array into array of class type
                            newsData = gson.fromJson(result, News[].class);
                            // convert array to arrayList
                            newsList = new ArrayList<>(Arrays.asList(newsData));
                            // set list to adapter
                            mAdapter.setNewsList(sortList(newsList, tabSelected));
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Function to sort arrayList
     * @param newsList
     * @param tabSelected tab selected
     * @return
     */
    public ArrayList<News> sortList(ArrayList<News> newsList, String tabSelected) {
        // instantiate list
        ArrayList<News> list = new ArrayList<>();
        // loop through all news items in newsList
        for (News news : newsList) {
            // if category of current news item is equal to tabSelected
            if (news.getCategory().equals(tabSelected))
                // add news item to  list
                list.add(news);
        }
        // return list
        return list;
    }

    /**
     * Function to set fragment to this main activity
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}