package com.example.faigy.hala.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.faigy.hala.CircleTransform;
import com.example.faigy.hala.R;
import com.example.faigy.hala.TeamMember;
import com.example.faigy.hala.Util;
import com.example.faigy.hala.VolleySingleton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 1/31/2016.
 */
public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {
    // Declare ArrayList
    private List<TeamMember> teamMembersList;

    // Declare variables
    public static int expandedPosition = -1;

    // Declare class
    private VolleySingleton volleySingleton;
    public TeamMember tm;

    // Declare context
    Context context;

    // Constructor
    public TeamMemberAdapter(Context context) {
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
    }

    /**
     * Function sets the newsList
     * @return ArrayList
     */
    public void setTeamMembersList(ArrayList<TeamMember> teamMembersList){
        this.teamMembersList = teamMembersList;
        // notify the adapter of item range changed
        notifyItemRangeChanged(0, teamMembersList.size());
    }

    /**
     * Function that create new views (invoked by the layout manager)
     *
     */
    @Override
    public TeamMemberViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // initialize itemView
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.fragment_team_list_item, viewGroup, false);
        // return itemView
        return new TeamMemberViewHolder(itemView);
    }

    /**
     * Function that replace the contents of a view (invoked by the layout manager)
     * @param teamMemberViewHolder   - current viewHolder
     * @param position - current inflated position in viewHolder
     *
     */
    @Override
    public void onBindViewHolder(final TeamMemberViewHolder teamMemberViewHolder, int position) {
        // get data from your newsList at this position
        // replace the contents of the view with that newsList data
        tm = teamMembersList.get(position);
        teamMemberViewHolder.vName.setText(tm.title + " " + tm.name);
        teamMemberViewHolder.vTitle.setText(tm.description);
        teamMemberViewHolder.vBio.setText(tm.bio);
        Picasso.with(context).load("http://" + tm.getImage())
                .transform(new CircleTransform()).into(teamMemberViewHolder.teamMemberImageView
                , new Callback() {
                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub
                        teamMemberViewHolder.imageProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        // TODO Auto-generated method stub
                        teamMemberViewHolder.imageProgressBar.setVisibility(View.GONE);
                        teamMemberViewHolder.teamMemberImageView.setBackgroundResource(R.mipmap.ic_launcher_hala);
                    }
                });



        // if position is equal to expanded position
        if (position == expandedPosition) {
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

    /**
     * Function that returns the number of items in newsList
     * @return int
     */
    @Override
    public int getItemCount() {
        // if newsList is not null
        if (teamMembersList != null) {
            // return size of newsList
            return teamMembersList.size();
        }
        return 0;
    }

    // this will store the references to our view
    public class TeamMemberViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName,  vTitle, vBio;
        public LinearLayout expandLinearLayout;
        public ImageButton expandArrow;
        public ImageView teamMemberImageView;
        public ProgressBar imageProgressBar;

        public TeamMemberViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.txtName);
            vTitle = (TextView) v.findViewById(R.id.title);
            vBio = (TextView) v.findViewById(R.id.bioTextView);
            expandLinearLayout = (LinearLayout) v.findViewById(R.id.expandLinearLayout);
            expandArrow = (ImageButton) v.findViewById(R.id.expandArrow);
            teamMemberImageView = (ImageView) v.findViewById(R.id.teamMemberImageView);
            imageProgressBar = (ProgressBar) v.findViewById(R.id.imageProgressBar);
        }
    }
}