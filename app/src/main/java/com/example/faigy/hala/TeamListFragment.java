package com.example.faigy.hala;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.faigy.hala.Adapters.TeamMemberAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;


public class TeamListFragment extends Fragment {
    // Declare application
    protected MyApplication app;
    // Declare activity
    MainActivity mainActivity;
    // Declare controls
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    ProgressDialog pDialog;
    TextView errorTextView;
    // Declare adapter
    TeamMemberAdapter mAdapter;
    // Declare variables
    TeamMember[] teamMembersData;
    ArrayList<TeamMember> teamMembersList;
    private RequestQueue requestQueue;
    private static String TAG = MainActivity.class.getSimpleName();
    int prev = -1;
    // Declare class
    private VolleySingleton volleySingleton;


    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize variables
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_team_list, container, false);
        initializeViews(rootView);
        // set toolbar title
        Util.setToolbarTitle(R.string.fragment_team, mainActivity.toolbar);
        // remove keyboard from screen
        Util.hideSoftKeyboard();
        //set navigation selected to current fragment
        mainActivity.setSelectedNavigationItem(R.id.nav_team);

        return rootView;
    }

    /**
     * Function to initialize controls
     */
    public void initializeViews(View rootView) {
        // initialize controls
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mAdapter = new TeamMemberAdapter(getActivity());
        llm = new LinearLayoutManager(getActivity().getBaseContext());
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
        // set layout manager to recycler view
        recyclerView.setLayoutManager(llm);
        // set orientation for linear layout manager
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        // set adapter for recycler view
        recyclerView.setAdapter(mAdapter);
        // set click listener for error text view to refresh data
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send json request to retrieve data from server
                makeJsonArrayRequest("http://162.243.100.186/members_array.php");
            }
        });
        // send json request to retrieve data from server
        makeJsonArrayRequest("http://162.243.100.186/members_array.php");

        // set click listener for items in recyclerView to expand and collapse
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                // Check for an expanded view, collapse if you find one
                if (mAdapter.expandedPosition >= 0) {
                    // set pre to expandedPosition
                    prev = mAdapter.expandedPosition;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(prev);
                }
                // if position is expanded
                if (position == mAdapter.expandedPosition) {
                    // Set the current position to "collapse"
                    mAdapter.expandedPosition = -1;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
                } else {
                    // Set the current position to "expanded"
                    mAdapter.expandedPosition = position;
                    // notify adapter on item changed
                    mAdapter.notifyItemChanged(mAdapter.expandedPosition);
                }
            }
        }));
    }

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArry) {
        // initialize progress dialog
        pDialog = new ProgressDialog(Util.getContext());
        // set message to display in progress dialog
        pDialog.setMessage("Loading...");
        // display dialog
        pDialog.show();
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // hide the text view from displaying error message
                        errorTextView.setVisibility(View.GONE);
                        Log.d(TAG, response.toString());
                        // hide progress dialog
                        pDialog.hide();
                        // initialize Gson object
                        Gson gson = new Gson();
                        // initialize string to store json result
                        String jsonOutput = response.toString();
                        try {
                            // convert json array into array of class type
                            teamMembersData = gson.fromJson(jsonOutput, TeamMember[].class);
                            // convert array to arrayList
                            teamMembersList = new ArrayList<>(Arrays.asList(teamMembersData));
                            // set list to adapter
                            mAdapter.setTeamMembersList(teamMembersList);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error exceptions
                Util.handleVolleyError(error, errorTextView);
                // hide dialog after successfully retrieving data
                pDialog.hide();
            }
        });
        requestQueue.add(req);
    }

    // set current fragment to this main activity
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
