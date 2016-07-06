package org.hala.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.hala.adapters.NewsAdapter;
import org.hala.classes.Faqs;
import org.hala.classes.News;
import org.hala.utilities.DatabaseOperations;
import org.hala.activities.MainActivity;
import org.hala.classes.MyApplication;
import org.hala.R;
import org.hala.utilities.Util;
import org.hala.utilities.DividerItemDecoration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class InTheNewsFragment extends Fragment {

    // Declare controls
    private RecyclerView recyclerView;
    public NewsAdapter mAdapter;
    TextView errorTextView;
    SearchView searchView;
    FloatingActionButton fab;

    // Declare variables
    News[] newsData;
    ArrayList<News> newsList;
    String tabSelected;
    public static String TAG = "json_news_request";
    String url;

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;
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

        // initialize and reference searchView
        searchView = (SearchView) rootView.findViewById(R.id.searchView);

        // initialize and refrence searchFab
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

    }

    /**
     * Function to set up RecyclerView
     */
    public void setupRecyclerView() {
        // instantiate mAdapter
        mAdapter = new NewsAdapter(getActivity());
        // initialize linearLayoutManager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity().getBaseContext());
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        // add item decorator to recyclerView
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
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
        fab.setOnClickListener(searchFabListener);
        searchView.setOnCloseListener(searchViewCloseListener);
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
     * OnClickListener for errorTextView
     */
    View.OnClickListener searchFabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // set searchView to visible
            searchView.setVisibility(View.VISIBLE);
            // set focus
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
            // hide fab
            fab.hide();
            // remove tabLayout
            NewsTabFragment.tabLayout.setVisibility(View.GONE);
            // clear recyclerView
            mAdapter.clearNewsList();

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchDb(query);
                    Util.hideSoftKeyboard();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchDb(newText);
                    return true;
                }
            });
        }
    };


    /**
     * OnClickListener for errorTextView
     */

    SearchView.OnCloseListener searchViewCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            // remove searchView
            searchView.setVisibility(View.GONE);
            // clear recyclerView
            mAdapter.clearNewsList();
            downloadData();
            // show tabLayout
            NewsTabFragment.tabLayout.setVisibility(View.VISIBLE);
            // show fab
            fab.show();
            // remove errorTextView
            errorTextView.setVisibility(View.GONE);
            return false;
        }
    };

    /**
     * Function to download data from url
     */
    public void downloadData() {
        // set url data to corresponding language of phone settings
        if (!Locale.getDefault().getLanguage().equals("en")) {
            url = "http://162.243.100.186/news_array_he.php";
        } else {
            url = "http://162.243.100.186/news_array.php";
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
                            newsData = gson.fromJson(result, News[].class);
                            // convert array to arrayList
                            newsList = new ArrayList<>(Arrays.asList(newsData));
                            // set list to adapter
                            //mAdapter.setNewsList(newsList);
                            mAdapter.setNewsList(sortList(newsList, tabSelected));
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * Function to sort arrayList
     *
     * @param newsList    ArrayList of type News
     * @param tabSelected tab selected
     * @return ArrayList of type News
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
     *
     * @param mainActivity - set main activity
     */
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public void searchDb(String query) {
        mAdapter.clearNewsList();
        errorTextView.setVisibility(View.GONE);
        if (query.isEmpty() || query == null) {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("Please enter a search phrase");
        } else {

            /**
             * Function to download data from url
             */
            // call makeJsonArrayRequest and send url, tag, errorTextView and instantiate a callBack
            databaseOperations.postSearch("http://162.243.100.186/news_search_post.php", query, errorTextView,
                    new DatabaseOperations.VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            if (result.contains("sqlError")) {
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("A Sql error occurred");
                            } else {
                                if (result.contains("noProducts")) {
                                    errorTextView.setVisibility(View.VISIBLE);
                                    errorTextView.setText("No matches found");
                                } else if (result.contains("noFields")) {
                                    errorTextView.setVisibility(View.VISIBLE);
                                    errorTextView.setText("Your search is empty");

                                } else {
                                    // initialize gson object
                                    Gson gson = new Gson();
                                    try {
                                        // convert json array into array of class type
                                        newsData = gson.fromJson(result, News[].class);
                                        // convert array to arrayList
                                        newsList = new ArrayList<>(Arrays.asList(newsData));
                                        // set list to adapter
                                        mAdapter.setNewsList(newsList);
                                    } catch (JsonSyntaxException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });

        }
    }

}