package com.example.faigy.hala;

import android.view.View;

/**
 * Created by Home on 2/3/2016.
 */
public interface ClickListenerInterface {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}