package com.example.faigy.hala.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.faigy.hala.interfaces.ClickListenerInterface;


public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListenerInterface clickListener;

    /**
     * This method is called when a item in the RecyclerView is clicked
     * @param context - of type Context
     * @param recyclerView - of type recyclerView
     * @param clickListener - of type ClickListener
     */
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListenerInterface clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

        });
    }


    /**
     * This method is called on a touch event of the recyclerView
     * @param rv - recyclerView
     * @param e - MotionEvent
     * @return boolean
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}