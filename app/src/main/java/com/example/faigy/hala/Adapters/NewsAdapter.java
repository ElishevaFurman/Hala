package com.example.faigy.hala.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.faigy.hala.News;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Util;
import com.example.faigy.hala.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2/3/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {
    // Declare ArrayList
    private List<News> newsList;

    // Declare context
    Context context;

    // Declare class
    public News news;
    private VolleySingleton volleySingleton;

    // Constructor
    public NewsAdapter(Context context) {
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
    }

    /**
     * Function sets the newsList
     * @return ArrayList
     */
    public void setNewsList(ArrayList<News> newsList){
        this.newsList = newsList;
        // notify the adapter of item range changed
        notifyItemRangeChanged(0, newsList.size());
    }

    /**
     * Function that create new views (invoked by the layout manager)
     *
     */
    @Override
    public MyNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // initialize itemView
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_in_the_news_item, parent, false);
        // return itemView
        return new MyNewsHolder(itemView);
    }


    /**
     * Function that replace the contents of a view (invoked by the layout manager)
     * @param holder   - current viewHolder
     * @param position - current inflated position in viewHolder
     *
     */
    @Override
    public void onBindViewHolder(final MyNewsHolder holder, int position) {
        // get data from your newsList at this position
        // replace the contents of the view with that newsList data
        news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.publication.setText(news.getPublication()+", ");
        holder.date.setText(news.getDate().toString());
        holder.textLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.createDialog("Open Article", "View article in browser", "OPEN", "CANCEL", "url", news.getUrl());

            }
        });
              holder.rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.share(news.getUrl());
            }
        });
    }

    /**
     * Function that returns the number of items in newsList
     * @return int
     */
    @Override
    public int getItemCount() {
        // if newsList is not null
        if (newsList != null) {
            // return size of newsList
            return newsList.size();
        }
        return 0;
    }


    // this will store the references to our view
    public class MyNewsHolder extends RecyclerView.ViewHolder {
        // declare the controls in the views
        public TextView title, publication, date;
        ImageView emailImageView;
        LinearLayout shareLinearLayout, textLinearLayout;
        final RippleView rippleView;

        public MyNewsHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            publication = (TextView) view.findViewById(R.id.publicationTextView);
            date = (TextView) view.findViewById(R.id.dateTextView);
            emailImageView = (ImageView) view.findViewById(R.id.emailImageView);
            shareLinearLayout = (LinearLayout) view.findViewById(R.id.shareLinearLayout);
            textLinearLayout = (LinearLayout) view.findViewById(R.id.textLinearLayout);
            rippleView = (RippleView) view.findViewById(R.id.rippleView);

        }

    }



}
