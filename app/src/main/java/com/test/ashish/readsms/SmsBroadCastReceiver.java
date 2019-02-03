package com.test.ashish.readsms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

public class SmsBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle data  = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0;i<pdus.length;i++)
        {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessage.getDisplayOriginatingAddress();
            String messageBody = smsMessage.getMessageBody();
            createNotification(context, sender, messageBody);
        }
    }

    public void createNotification(Context context, String title, String message) {

        Intent i = new Intent(context, MessageDetail.class);
        i.putExtra("sender",title);
        i.putExtra("message", message);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)

                .setSmallIcon( R.drawable.ic_launcher_background).setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pi)
                .setPriority(PRIORITY_HIGH) //private static final PRIORITY_HIGH = 5;
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, mBuilder.build());

    }
}
