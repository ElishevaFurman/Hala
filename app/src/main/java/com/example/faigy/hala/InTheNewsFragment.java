package com.example.faigy.hala;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2/3/2016.
 */
public class InTheNewsFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;

    // Declare variables
    private List<News> NewsList = new ArrayList<>();

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_in_the_news, container, false);
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_news, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_news);
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        NewsList = mainActivity.getNewsArrayList();
        mAdapter = new NewsAdapter(NewsList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                Util.createDialog("Open Article", "View article in browser", "OPEN", "CANCEL", "url", "https://www.google.com/");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
       // prepareNewsData();
        if (mAdapter.openDialog) {
            Util.createDialog("Open Article", "View article in browser", "OPEN", "CANCEL", "url", "https://www.google.com/");
        }

    }

    /**
     * Function to add data to NewsList
     */
    private void prepareNewsData() {
        News news = new News("Revolution in the Haredi Community", "Jerusalem Post", "12-01-2015");
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", "12-01-2015");
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", "12-01-2015");
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", "12-01-2015");
        NewsList.add(news);


        mAdapter.notifyDataSetChanged();
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}

