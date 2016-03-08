package com.example.faigy.hala;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TeamListFragment extends Fragment {
    MainActivity mainActivity;
    RecyclerView recList;
    LinearLayoutManager llm;
    TeamMemberAdapter tm;
    int prev = -1;
    ArrayList<TeamMember>teamMemberArrayList;

    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity().getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        teamMemberArrayList =MySingleton.getInstance().getTeamMembersArrayList();
        tm = new TeamMemberAdapter(teamMemberArrayList,Util.getContext());
        recList.setAdapter(tm);

        recList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recList, new ClickListenerInterface() {
            @Override
            public void onClick(View view, int position) {
                // Check for an expanded view, collapse if you find one
                if (tm.expandedPosition >= 0) {
                    // set pre to expandedPosition
                    prev = tm.expandedPosition;
                    // notify adapter on item changed
                    tm.notifyItemChanged(prev);
                }
                // if position is expanded
                if (position == tm.expandedPosition) {
                    // Set the current position to "collapse"
                    tm.expandedPosition = -1;
                    // notify adapter on item changed
                    tm.notifyItemChanged(tm.expandedPosition);
                } else {
                    // Set the current position to "expanded"
                    tm.expandedPosition = position;
                    // notify adapter on item changed
                    tm.notifyItemChanged(tm.expandedPosition);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
