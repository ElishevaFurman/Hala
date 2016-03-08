package com.example.faigy.hala;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2/3/2016.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyNewsHolder> {

    Boolean clicked = false;

    private List<Services> servicesList;
    public static boolean openDialog = false;
    private VolleySingleton volleySingleton;
    public Services services;
    Context context;


    public ServicesAdapter(Context context) {
        //this.newsList = newsList;
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
    }

    public void setServicesList(ArrayList<Services> servicesList){
        this.servicesList = servicesList;
        notifyItemRangeChanged(0, servicesList.size());
    }


    @Override
    public MyNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_services_list_item, parent, false);

        return new MyNewsHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyNewsHolder holder, int position) {
        services = servicesList.get(position);
        holder.title.setText(services.getTitle().toUpperCase());
        //holder.description.setText(services.getDescription());
        //holder.date.setText(services.getDate().toString());
        //clicked = false;
//        holder.shareLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //clicked=true;
//                Util.share(news.getUrl());
//
//            }
//        });
//        holder.textLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //clicked=true;
//                Util.createDialog("Open Article", "View article in browser", "OPEN", "CANCEL", "url", news.getUrl());
//
//            }
//        });
//        clicked = false;
//
//        holder.rippleView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Util.share(news.getUrl());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (servicesList != null) {
            return servicesList.size();
        }
        return 0;
    }

    public class MyNewsHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView viewDetailsImageView;
//        LinearLayout shareLinearLayout, textLinearLayout;
        final RippleView rippleView;


        public MyNewsHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            //description = (TextView) view.findViewById(R.id.descriptionTextView);
            viewDetailsImageView = (ImageView) view.findViewById(R.id.viewDetailsImageView);
            rippleView = (RippleView) view.findViewById(R.id.rippleView);

        }

    }



}
