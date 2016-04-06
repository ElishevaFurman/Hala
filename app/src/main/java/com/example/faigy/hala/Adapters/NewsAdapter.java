package com.example.faigy.hala.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.andexert.library.RippleView;
import com.example.faigy.hala.Classes.News;
import com.example.faigy.hala.R;
import com.example.faigy.hala.Utilities.Util;

import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {
    // Declare ArrayList
    private List<News> newsList;

    // Declare context
    Context context;

    // Declare class
    public News news;

    // Constructor
    public NewsAdapter(Context context) {
        this.context = context;
    }

    /**
     * Function sets the newsList
     */
    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
        // notify the adapter of item range changed
        //notifyItemRangeChanged(0, newsList.size());
        notifyDataSetChanged();
    }

    /**
     * Function that create new views (invoked by the layout manager)
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
     *
     * @param holder   - current viewHolder
     * @param position - current inflated position in viewHolder
     */
    @Override
    public void onBindViewHolder(final MyNewsHolder holder, final int position) {
        // get data from your newsList at this position
        // replace the contents of the view with that newsList data
        news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());

        final String url = news.getUrl();
        Bidi bidi = new Bidi(news.getPublication(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        Bidi bidi2 = new Bidi(news.getTitle(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);

        if (!holder.date.getText().equals("")) {
            // instantiate a stringBuilder
            StringBuilder publication = new StringBuilder();
            // append the publication of that news item to publication
            publication.append(news.getPublication());
            // append a comma to publication
            publication.append(", ");
            // set text of publication of this current position
            holder.publication.setText(publication);
        }


        if (!Locale.getDefault().getLanguage().equals("en")) {
            if (bidi.isLeftToRight()) {
                holder.publication.setText(news.getDate());
                if (!holder.date.getText().equals("")) {
                    // instantiate a stringBuilder
                    StringBuilder publication = new StringBuilder();
                    // append the publication of that news item to publication
                    publication.append(news.getPublication());
                    // append a comma to publication
                    publication.append(", ");
                    // set text of publication of this current position
                    holder.date.setText(publication);
                }
            }
            if (bidi2.isLeftToRight()) {
                holder.dateLinearLayout.setGravity(Gravity.LEFT);
            }
        }
        holder.textLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.createDialog(R.string.news, R.string.news_message, R.string.open, R.string.cancel, null, "url", url);

            }
        });
        holder.rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.share(url);
            }
        });
    }

    /**
     * Function that returns the number of items in newsList
     *
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
    public static class MyNewsHolder extends RecyclerView.ViewHolder {
        // declare the controls in the views
        public TextView title, publication, date;
        ImageView emailImageView;
        LinearLayout shareLinearLayout, textLinearLayout, dateLinearLayout;
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
            dateLinearLayout = (LinearLayout) view.findViewById(R.id.dateLinearLayout);
        }

    }
}
