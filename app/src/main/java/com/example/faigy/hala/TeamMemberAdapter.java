package com.example.faigy.hala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Home on 1/31/2016.
 */
public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {

    private List<TeamMember> teamMembersList;
    public static int expandedPosition = -1;


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
        teamMemberViewHolder.vTitle.setText(tm.title);

        // if position is equal to expanded position
        if (i == expandedPosition) {
            // expand view of selected position
            teamMemberViewHolder.expandLinearLayout.setVisibility(View.VISIBLE);
            // set icon to expand less icon
            teamMemberViewHolder.expandArrow.setImageResource(R.drawable.ic_expand_less);
        } else {
            // collapse view
            teamMemberViewHolder.expandLinearLayout.setVisibility(View.GONE);
            // set icon to expand more icon
            teamMemberViewHolder.expandArrow.setImageResource(R.drawable.ic_expand_more);
        }

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
        protected TextView vTitle;
        public LinearLayout expandLinearLayout;
        public ImageButton expandArrow;

        public TeamMemberViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.txtName);
            vTitle = (TextView) v.findViewById(R.id.title);
            expandLinearLayout = (LinearLayout) v.findViewById(R.id.expandLinearLayout);
            expandArrow = (ImageButton) v.findViewById(R.id.expandArrow);
        }
    }
}