package com.example.faigy.hala;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FAQExpandableAdapter extends RecyclerView.Adapter<FAQExpandableAdapter.MyViewHolder> {
//implements View.OnClickListener{

    // Declare controls
    private ArrayList<Faqs> faqList;
    public static int expandedPosition = -1;
    int prev = -1;
    private VolleySingleton volleySingleton;
    public Faqs faqs;
    Context context;


    public FAQExpandableAdapter(Context context) {
        //this.newsList = newsList;
        this.context = context;
        volleySingleton = VolleySingleton.getInstance();
    }

    public void setFaqList(ArrayList<Faqs> faqList){
        this.faqList = faqList;
        notifyItemRangeChanged(0, faqList.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer;
        public LinearLayout expandLinearLayout;
        public ImageButton expandArrow;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.questionTextView);
            answer = (TextView) view.findViewById(R.id.answerTextView);
            expandLinearLayout = (LinearLayout) view.findViewById(R.id.expandLinearLayout);
            expandArrow = (ImageButton) view.findViewById(R.id.expandArrow);
        }
    }


//    public FAQExpandableAdapter(ArrayList<Faqs> faqList) {
//        this.faqList = faqList;
//
//    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_faq, parent, false);

        return new MyViewHolder(itemView);


    }

    /**
     * @param holder   - current viewHolder
     * @param position - current inflated position in viewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // setting text of question/answer of current position
        holder.question.setText(faqList.get(position).getId() + ". " + faqList.get(position).getQuestion());
        holder.answer.setText(faqList.get(position).getAnswer());
        // if position is equal to expanded position
        if (position == expandedPosition) {
            // expand view of selected position
            holder.expandLinearLayout.setVisibility(View.VISIBLE);
            // set icon to expand less icon
            holder.expandArrow.setImageResource(R.drawable.ic_expand_less);
        } else {
            // collapse view
            holder.expandLinearLayout.setVisibility(View.GONE);
            // set icon to expand more icon
            holder.expandArrow.setImageResource(R.drawable.ic_expand_more);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check for an expanded view, collapse if you find one
                if (expandedPosition >= 0) {
                    // set pre to expandedPosition
                    prev = expandedPosition;
                    // notify adapter on item changed
                    notifyItemChanged(prev);
                }
                // if position is expanded
                if (position == expandedPosition) {
                    // Set the current position to "collapse"
                    expandedPosition = -1;
                    // notify adapter on item changed
                    notifyItemChanged(expandedPosition);
                } else {
                    // Set the current position to "expanded"
                    expandedPosition = position;
                    // notify adapter on item changed
                    notifyItemChanged(expandedPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (faqList != null) {
            return faqList.size();
        }
        return 0;
    }

}