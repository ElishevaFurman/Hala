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
        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {
        // initialize and reference controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAdapter = new NewsAdapter(NewsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareNewsData();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.jpost.com/In-Jerusalem/A-revolution-in-the-haredi-community-438940#article=6030QzIzMUJBMUZDNDcxNDFDQzNDRkVDMEE2M0I0NkU3MEQ=")));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    /**
     * Function to add data to NewsList
     */
    private void prepareNewsData() {
        News news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);
        news = new News("Revolution in the Haredi Community", "Jerusalem Post", LocalDate.now());
        NewsList.add(news);

        mAdapter.notifyDataSetChanged();
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}

