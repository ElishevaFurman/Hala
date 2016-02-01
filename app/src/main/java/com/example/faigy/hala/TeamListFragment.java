package com.example.faigy.hala;

import android.content.Context;
import android.net.Uri;
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
        View rootView =  inflater.inflate(R.layout.fragment_team_list, container, false);
        intializeViews(rootView);
        TeamMemberAdapter tm = new TeamMemberAdapter(createList(10));
        recList.setAdapter(tm);
        return rootView;
    }

    private List createList(int size) {

        List result = new ArrayList();
        for (int i=1; i <= size; i++) {
            TeamMember tm = new TeamMember();
            tm.name = "Dr. Yamin Cohen";
            tm.surname = "Chief Diagnostic Clinic - Senior Medical Officer";
            //tm.name = TeamMember.NAME_PREFIX + i;
            //tm.surname = TeamMember.SURNAME_PREFIX + i;
           //tm.email = TeamMember.EMAIL_PREFIX + i + "@test.com";

            result.add(tm);

        }

        return result;
    }

    public void intializeViews(View rootView){
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mainActivity.getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
