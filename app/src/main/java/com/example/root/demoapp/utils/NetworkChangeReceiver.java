package com.example.root.demoapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by root on 20/07/2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        //should check null because in airplane mode it will be null
        boolean status = isNetworkAvailable(context);

        //Log.d("Debud", "network: " + String.valueOf(status));
        EventBus.getDefault().postSticky(new MessageEvent(String.valueOf(status)));
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnected());
    }
}
