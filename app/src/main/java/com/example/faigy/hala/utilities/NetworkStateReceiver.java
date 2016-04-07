package com.example.faigy.hala.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.faigy.hala.R;

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

      if (!Util.isConnected()){
          Util.createDialog(R.string.internet_connection, R.string.internet_message, R.string.connect, R.string.cancel, null,  "internet", null);
      }


    }
}
