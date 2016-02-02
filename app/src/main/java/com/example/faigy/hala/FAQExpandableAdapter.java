package com.example.faigy.hala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FAQExpandableAdapter extends RecyclerView.Adapter<FAQExpandableAdapter.MyViewHolder> {
//implements View.OnClickListener{

    // Declare controls


    private ArrayList<String> questionList;
    private ArrayList<String> answerList;
    public static int expandedPosition = -1;

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


    public FAQExpandableAdapter(ArrayList<String> questionList, ArrayList<String> answerList) {
        this.questionList = questionList;
        this.answerList = answerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_faq, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     *
     * @param holder - current viewHolder
     * @param position - current inflated position in viewHolder
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // setting text of question/answer of current position
        holder.question.setText(questionList.get(position));
        holder.answer.setText(answerList.get(position));
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
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

}