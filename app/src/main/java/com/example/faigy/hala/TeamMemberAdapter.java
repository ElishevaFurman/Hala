package com.example.faigy.hala;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Home on 1/31/2016.
 */
public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {

    private List<TeamMember> teamMembersList;
    public static int expandedPosition = -1;
    public Context context;

    public TeamMemberAdapter(List<TeamMember> contactList, Context context) {
        this.teamMembersList = contactList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return teamMembersList.size();
    }

    @Override
    public void onBindViewHolder(TeamMemberViewHolder teamMemberViewHolder, int i) {
        TeamMember tm = teamMembersList.get(i);
        teamMemberViewHolder.vName.setText(tm.title + " " + tm.name);
        teamMemberViewHolder.vTitle.setText(tm.description);
        File f = new File("162.243.100.186/images/dr_yamin_cohen.jpg");
        Picasso.with(context).load("http://162.243.100.186/images/dr_yamin_cohen.jpg").placeholder(R.mipmap.ic_launcher_hala).into(teamMemberViewHolder.teamMemberImageView);
        //Picasso.with(Util.getContext()).load(tm.getImage()).into(teamMemberViewHolder.teamMemberImageView);

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
        public ImageView teamMemberImageView;

        public TeamMemberViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.txtName);
            vTitle = (TextView) v.findViewById(R.id.title);
            expandLinearLayout = (LinearLayout) v.findViewById(R.id.expandLinearLayout);
            expandArrow = (ImageButton) v.findViewById(R.id.expandArrow);
            teamMemberImageView = (ImageView) v.findViewById(R.id.teamMemberImageView);
        }
    }
}