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
    MainActivity mainActivity;
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    TeamMemberAdapter mAdapter;
    int prev = -1;
    //ArrayList<TeamMember>teamMemberArrayList;

    // Declare variables
    TeamMember[] teamMembersData;
    ArrayList<TeamMember> teamMembersList;
    private RequestQueue requestQueue;

    // Declare class
    private VolleySingleton volleySingleton;

    private static String TAG = MainActivity.class.getSimpleName();
    protected MyApplication app;
    ProgressDialog pDialog;
    TextView errorTextView;



    public TeamListFragment() {
        // Required empty public constructor
    }

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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mAdapter = new TeamMemberAdapter(getActivity());
        //recyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //teamMemberArrayList =MySingleton.getInstance().getTeamMembersArrayList();
        //tm = new TeamMemberAdapter(teamMemberArrayList,Util.getContext());
        recyclerView.setAdapter(mAdapter);
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
        errorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayRequest("http://162.243.100.186/members_array.php");
            }
        });
        makeJsonArrayRequest("http://162.243.100.186/members_array.php");




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

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    /**
     * Method to make json array request where response starts with
     */
    public void makeJsonArrayRequest(String urlJsonArry) {
        pDialog = new ProgressDialog(Util.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        errorTextView.setVisibility(View.GONE);
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        Gson gson = new Gson();
                        String jsonOutput = response.toString();
                        try {
                            teamMembersData = gson.fromJson(jsonOutput, TeamMember[].class);
                            teamMembersList = new ArrayList<>(Arrays.asList(teamMembersData));
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
                Util.handleVolleyError(error,errorTextView);
                pDialog.hide();
            }
        });
        requestQueue.add(req);
    }



    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
