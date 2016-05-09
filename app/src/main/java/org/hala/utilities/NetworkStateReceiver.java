package org.hala.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.hala.R;

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // check if there is connection
        if (activeNetwork == null) {
            try {
                Util.createDialog(R.string.internet_connection, R.string.internet_message, R.string.connect, R.string.cancel, null, "internet", null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}