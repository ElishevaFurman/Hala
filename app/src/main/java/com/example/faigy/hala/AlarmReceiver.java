package com.example.faigy.hala;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by faigy on 2/23/2016.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(Util.getContext(), "alarm received", Toast.LENGTH_LONG).show();

    }
}
