package com.example.faigy.hala;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by faigy on 3/8/2016.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

      if (!Util.isConnected()){
          Util.createDialog("Internet Connection", "No connection found. Please connect to internet.", "CONNECT", "CANCEL", "internet", null);
      }


    }
}
