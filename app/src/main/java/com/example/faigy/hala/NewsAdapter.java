package com.example.faigy.hala;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2/3/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {

    Boolean clicked = false;

    private List<News> newsList;
    public static boolean openDialog = false;
    private VolleySingleton volleySingleton;
    public News news;
    Context context;


    public NewsAdapter(Context context) {
        //this.newsList = newsList;
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
    }

    public void setNewsList(ArrayList<News> newsList){
        this.newsList = newsList;
        notifyItemRangeChanged(0, newsList.size());
    }


    @Override
    public MyNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_in_the_news_item, parent, false);

        return new MyNewsHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyNewsHolder holder, int position) {
        news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.publication.setText(news.getPublication()+", ");
        holder.date.setText(news.getDate().toString());
        clicked = false;
//        holder.shareLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //clicked=true;
//                Util.share(news.getUrl());
//
//            }
//        });
        holder.textLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clicked=true;
                Util.createDialog("Open Article", "View article in browser", "OPEN", "CANCEL", "url", news.getUrl());

            }
        });
        clicked = false;

        holder.rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.share(news.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (newsList != null) {
            return newsList.size();
        }
        return 0;
    }

    public class MyNewsHolder extends RecyclerView.ViewHolder {
        public TextView title, publication, date;
        ImageView emailImageView;
        LinearLayout shareLinearLayout, textLinearLayout;
        final RippleView rippleView;
        Boolean clicked = false;

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
