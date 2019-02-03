package com.test.ashish.readsms;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.provider.Telephony;
import android.util.Log;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new SmsBroadCastReceiver(),intentFilter);
    }
}
