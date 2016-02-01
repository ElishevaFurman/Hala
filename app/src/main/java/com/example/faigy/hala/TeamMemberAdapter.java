package com.example.faigy.hala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Home on 1/31/2016.
 */
public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {

    private List<TeamMember> teamMembersList;

    public TeamMemberAdapter(List<TeamMember> contactList) {
        this.teamMembersList = contactList;
    }

    @Override
    public int getItemCount() {
        return teamMembersList.size();
    }

    @Override
    public void onBindViewHolder(TeamMemberViewHolder teamMemberViewHolder, int i) {
        TeamMember tm = teamMembersList.get(i);
        teamMemberViewHolder.vName.setText(tm.name);
        teamMemberViewHolder.vTitle.setText(tm.surname);
        //teamMemberViewHolder.vSurname.setText(tm.surname);
        //teamMemberViewHolder.vEmail.setText(tm.email);

    }

    @Override
    public TeamMemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.fragment_team_list_item, viewGroup, false);

        return new TeamMemberViewHolder(itemView);
    }

    public class TeamMemberViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        //protected TextView vSurname;
        //protected TextView vEmail;
        protected TextView vTitle;

        public TeamMemberViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vTitle = (TextView) v.findViewById(R.id.title);
            //vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            //vEmail = (TextView)  v.findViewById(R.id.txtEmail);

        }
    }
}