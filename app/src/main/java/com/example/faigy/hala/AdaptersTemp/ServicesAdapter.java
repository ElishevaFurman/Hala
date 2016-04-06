package com.example.faigy.hala.AdaptersTemp;

import android.content.Context;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.andexert.library.RippleView;
import com.example.faigy.hala.ActivitiesTemp.MainActivity;
import com.example.faigy.hala.ClassesTemp.MySingleton;
import com.example.faigy.hala.R;
import com.example.faigy.hala.ClassesTemp.Services;
import com.example.faigy.hala.UtilitiesTemp.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyNewsHolder> {
    // Declare ArrayList
    private List<Services> servicesList;

    // Declare class
    public Services services;
    public MainActivity mainActivity;

    // Declare context
    Context context;

    // Constructor
    public ServicesAdapter(Context context) {
        this.context = context;
    }

    /**
     * Function sets the newsList
     */
    public void setServicesList(ArrayList<Services> servicesList){
        this.servicesList = servicesList;
        // notify the adapter of item range changed
        notifyItemRangeChanged(0, servicesList.size());
    }

    /**
     * Function that create new views (invoked by the layout manager)
     *
     */
    @Override
    public MyNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // initialize itemView
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_services_list_item, parent, false);
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
    public void onBindViewHolder(final MyNewsHolder holder, final int position) {
        // get data from your newsList at this position
        // replace the contents of the view with that newsList data
        services = servicesList.get(position);
        holder.title.setText(services.getTitle().toUpperCase());
        if (!Locale.getDefault().getLanguage().equals("en")) {
            holder.viewDetailsImageView.setImageResource(R.drawable.ic_service_arrow_left_24dp);
        } else {
            holder.viewDetailsImageView.setImageResource(R.drawable.ic_service_arrow_right);
        }
        holder.serviceLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set and store the clicked position in the recycler view
                MySingleton.getInstance().setPosition(position);
                // switch to service detail fragment
                if (context instanceof MainActivity) {
                    Fragment serviceDetailFragment = ((MainActivity)context).serviceDetailFragment;
                    Util.replaceFragment(serviceDetailFragment,R.id.serviceDescriptionTextViews);
                }
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
        if (servicesList != null) {
            // return size of newsList
            return servicesList.size();
        }
        return 0;
    }

    // this will store the references to our view
    public class MyNewsHolder extends RecyclerView.ViewHolder {
        // declare the controls in the views
        public TextView title;
        ImageView viewDetailsImageView;
        final RippleView rippleView;
        LinearLayout serviceLinearLayout;

        public MyNewsHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            viewDetailsImageView = (ImageView) view.findViewById(R.id.viewDetailsImageView);
            rippleView = (RippleView) view.findViewById(R.id.rippleView);
            serviceLinearLayout = (LinearLayout)view.findViewById(R.id.servicesLinearLayout);
        }
    }
}
