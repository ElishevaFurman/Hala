package org.hala.fragments;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.hala.adapters.TeamMemberAdapter;
import org.hala.classes.TeamMember;
import org.hala.utilities.DatabaseOperations;
import org.hala.activities.MainActivity;
import org.hala.classes.MyApplication;
import org.hala.R;
import org.hala.utilities.Util;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class TeamListFragment extends Fragment {
    // Declare application
    protected MyApplication app;

    // Declare activity
    MainActivity mainActivity;

    // Declare controls
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    TextView errorTextView;

    // Declare variables
    TeamMember[] teamMembersData;
    ArrayList<TeamMember> teamMembersList;
    DatabaseOperations databaseOperations;
    TeamMemberAdapter mAdapter;
    static String TAG = "json_team_request";
    String url;
    private int prev;

    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        // remove keyboard from screen
        Util.hideSoftKeyboard();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instantiate databaseOperations
        databaseOperations = new DatabaseOperations();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_team_list, container, false);

        // Initialize views for this fragment
        initializeViews(rootView);

        // Set up recyclerView
        setUpRecyclerView();

        // Register all listeners for controls
        registerListeners();

        // Download data from url
        downloadData();

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
        llm = new LinearLayoutManager(getActivity().getBaseContext());
        errorTextView = (TextView) rootView.findViewById(R.id.errorTextView);
        //prev = -1;
    }

    /**
     * Function to set up recyclerView
     */
    public void setUpRecyclerView() {
        // set layout manager to recycler view
        recyclerView.setLayoutManager(llm);
        // set orientation for linear layout manager
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        // set adapter for recycler view
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Function to register Listener
     */
    public void registerListeners() {
        // set on click listener to refresh the data in the recycler view
        errorTextView.setOnClickListener(errorTextViewListener);
        // set click listener for items in recyclerView to expand and collapse
    }

    // Listener set to errorTextVeiw
    View.OnClickListener errorTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            errorTextView.setText("");
            // refresh data
            downloadData();
        }
    };

    /**
     * Method to make json array request where response starts with
     */
    public void downloadData() {
        // set url data to corresponding language of phone settings
        if (!Locale.getDefault().getLanguage().equals("en")) {
            // set url to pick up hebrew data
            url = "http://162.243.100.186/members_array_he.php";

        } else {
            // set url to pick up english data
            url = "http://162.243.100.186/members_array.php";
        }

        databaseOperations.makeJsonArrayRequest(url, TAG, errorTextView,
                new DatabaseOperations.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        Gson gson = new Gson();
                        try {

                            // convert json array into array of class type
                            teamMembersData = gson.fromJson(result, TeamMember[].class);

                            // convert array to arrayList
                            teamMembersList = new ArrayList<>(Arrays.asList(teamMembersData));

                            // set list to adapter
                            mAdapter.setTeamMembersList(teamMembersList);

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    // set current fragment to this main activity
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
