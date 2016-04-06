package com.example.faigy.hala.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.faigy.hala.Utilities.CircleTransform;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Classes.TeamMember;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeamMemberAdapter extends RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder> {
    // Declare ArrayList
    private List<TeamMember> teamMembersList;

    // Declare variables
    public int expandedPosition;
    String content;

    // Declare class
    public TeamMember tm;

    // Declare context
    Context context;

    // Constructor
    public TeamMemberAdapter(Context context) {
        this.context = context;
        expandedPosition = -1;
    }

    /**
     * Function sets the newsList
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
        // instantiate a stringBuilder
        StringBuilder name = new StringBuilder();
        // append title of current tm to name
        name.append(tm.getTitle());
        // append empty space to name
        name.append(" ");
        // append name of tm to name
        name.append(tm.getName());
        // set text of vName to name
        teamMemberViewHolder.vName.setText(name);

        // Set name for each textView
        //teamMemberViewHolder.vName.setText(tm.title + " " + tm.name);
        // Set description for each textView
        teamMemberViewHolder.vDescription.setText(tm.description);

        if (!Locale.getDefault().getLanguage().equals("en")){
            // set content to string for the bio text
            content = String.valueOf(Html
                    .fromHtml("<![CDATA[<body dir=RTL style=\"text-align:justify;\">"
                            + tm.bio.replace("\n", "<br /> <br />") + "</body>]]>"));

        }else{
            // set content to string for the bio text
            content = String.valueOf(Html
                    .fromHtml("<![CDATA[<body style=\"text-align:justify;\">"
                            + tm.bio.replace("\n", "<br /> <br />") + "</body>]]>"));

        }

        // Load content into bio webView as text
        teamMemberViewHolder.bio.loadData(content, "text/html; charset=utf-8", "utf-8");
        // Instantiate webViewSettings
        WebSettings webSettings = teamMemberViewHolder.bio.getSettings();

        webSettings.setDefaultTextEncodingName("utf-8");
        // Set font size to text in webView
        webSettings.setDefaultFontSize(12);

        // Load member image from url into imageView
        Picasso.with(context).load("http://" + tm.getImage())
                .transform(new CircleTransform()).into(teamMemberViewHolder.teamMemberImageView
                , new Callback() {
                    @Override
                    public void onSuccess() {
                        //Remove progressBar on success of loaded image
                        teamMemberViewHolder.imageProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        // Remove progressBar on error of loaded image
                        teamMemberViewHolder.imageProgressBar.setVisibility(View.GONE);
                        // Set the image to default
                        teamMemberViewHolder.teamMemberImageView.setBackgroundResource(
                                R.mipmap.ic_launcher_hala);
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
        protected TextView vName,  vDescription;
        public WebView bio;
        public LinearLayout expandLinearLayout;
        public ImageButton expandArrow;
        public ImageView teamMemberImageView;
        public ProgressBar imageProgressBar;

        public TeamMemberViewHolder(View v) {
            super(v);
            // Initialize all views in viewHolder
            vName = (TextView) v.findViewById(R.id.txtName);
            vDescription = (TextView) v.findViewById(R.id.title);
            bio = (WebView) v.findViewById(R.id.bioWebView);
            expandLinearLayout = (LinearLayout) v.findViewById(R.id.expandLinearLayout);
            expandArrow = (ImageButton) v.findViewById(R.id.expandArrow);
            teamMemberImageView = (ImageView) v.findViewById(R.id.teamMemberImageView);
            imageProgressBar = (ProgressBar) v.findViewById(R.id.imageProgressBar);
        }
    }
}