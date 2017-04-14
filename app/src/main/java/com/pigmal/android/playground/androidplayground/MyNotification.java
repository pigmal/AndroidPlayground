package com.pigmal.android.playground.androidplayground;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class MyNotification {
    public void notify(Context context) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.icon)
                        .setContentTitle("title")
                        .setContentText("text")
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL);

        int mNotificationId = 1;
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
