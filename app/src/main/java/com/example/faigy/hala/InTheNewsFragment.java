package com.example.faigy.hala;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.joda.time.LocalDate;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Home on 2/3/2016.
 */
public class InTheNewsFragment extends Fragment {
    // Declare controls
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;

    // Declare variables
    News[] newsData;
    ArrayList<News> newsList;
    private RequestQueue requestQueue;

    // Declare class
    private VolleySingleton volleySingleton;

    private static String TAG = MainActivity.class.getSimpleName();

    // Declare activities
    MainActivity mainActivity;
    protected MyApplication app;
    ProgressDialog pDialog;
    String p;

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
        View rootView = inflater.inflate(R.layout.fragment_in_the_news, container, false);
        // get the position of the tab in the layout
        p = container.getTag().toString();
        // Initialize the views for this fragment
        initializeViews(rootView);
        // set toolbar title
        //Util.setToolbarTitle(R.string.fragment_news, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
       // mainActivity.setSelectedNavigationItem(R.id.nav_news);

        pDialog = new ProgressDialog(Util.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(final View rootView) {

        // initialize and reference controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mAdapter = new NewsAdapter(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        makeJsonArrayRequest("http://162.243.100.186/news_array.php");

    }

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
                                newsData = gson.fromJson(jsonOutput, News[].class);
                                newsList = new ArrayList<>(Arrays.asList(newsData));
                                mAdapter.setNewsList(sortList(newsList, p));
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


    public ArrayList<News> sortList(ArrayList<News> newsList ,String p){
        ArrayList<News> list = new ArrayList<>();
        for (News n: newsList){
            if (n.getCategory().equals(p))
                list.add(n);
        }
        return list;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}