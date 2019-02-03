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

        Log.d("messageReceived","messageReceived");

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

//        Intent i = new Intent(context, MessageDetail.class);
//        i.putExtra("sender",title);
//        i.putExtra("message", message);
//        int notificationId = 23232313;
//
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
////        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        taskStackBuilder.addNextIntentWithParentStack(i);
//
//        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), title)
//                .setColor(context.getResources().getColor(R.color.colorPrimary))
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));
//
//        //Check version of sdk
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setPriority(NotificationManager.IMPORTANCE_HIGH);
//        } else {
//            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
//        }
//
//        if (notificationManager != null) {
//            notificationManager.notify(notificationId, builder.build());
//        }

        Intent i = new Intent(context, MessageDetail.class);
        i.putExtra("sender",title);
        i.putExtra("message", message);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
