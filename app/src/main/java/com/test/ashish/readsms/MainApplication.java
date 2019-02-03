package com.test.ashish.readsms;

import android.app.Application;
import android.content.IntentFilter;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new SmsBroadCastReceiver(),intentFilter);
    }
}
