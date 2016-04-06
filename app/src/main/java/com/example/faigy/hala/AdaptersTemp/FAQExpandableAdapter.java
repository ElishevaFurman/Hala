package com.example.faigy.hala.AdaptersTemp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.faigy.hala.ClassesTemp.Faqs;
import com.example.faigy.hala.R;

import java.util.ArrayList;

public class FAQExpandableAdapter extends RecyclerView.Adapter<FAQExpandableAdapter.MyViewHolder> {

    // Declare ArrayList
    private ArrayList<Faqs> faqList;

    // Declare veriables
    public static int expandedPosition = -1;
    int prev = -1;



    // Declare context
    Context context;

    // Constructor
    public FAQExpandableAdapter(Context context) {
        this.context = context;
        expandedPosition = -1;
    }

    /**
     * Function sets the newsList
     */
    public void setFaqList(ArrayList<Faqs> faqList){
        this.faqList = faqList;
        // notify the adapter of item range changed
        notifyItemRangeChanged(0, faqList.size());
    }

    // this will store the references to our view
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // declare the controls in the views
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

    /**
     * Function that create new views (invoked by the layout manager)
     *
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // initialize itemView
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_faq, parent, false);
        // return itemView
        return new MyViewHolder(itemView);


    }

    /**
     * Function that replace the contents of a view (invoked by the layout manager)
     * @param holder   - current viewHolder
     * @param position - current inflated position in viewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // instantiate a stringBuilder
        StringBuilder question = new StringBuilder();
        // append id of item in faqList to question
        question.append(faqList.get(position).getId());
        // append a period to question
        question.append(". ");
        // append the question of that position in faqList
        question.append(faqList.get(position).getQuestion());
        // setting text of question/answer of current position
        holder.question.setText(question);
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

    /**
     * Function that returns the number of items in newsList
     * @return int
     */
    @Override
    public int getItemCount() {
        // if newsList is not null
        if (faqList != null) {
            // return size of newsList
            return faqList.size();
        }
        return 0;
    }

}