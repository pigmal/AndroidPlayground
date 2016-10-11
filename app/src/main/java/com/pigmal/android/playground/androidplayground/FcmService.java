package com.pigmal.android.playground.androidplayground;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FcmService extends FirebaseMessagingService {
    static final String TAG = "FcmService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Received FCM");

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Got Notification");
            String title = remoteMessage.getNotification().getTitle();
            Log.d(TAG, "title: " + title);
            String body = remoteMessage.getNotification().getBody();
            Log.d(TAG, "body: " + body);
        }

        if (remoteMessage.getData() != null) {

            Map<String, String> data = remoteMessage.getData();
            String messageId = remoteMessage.getMessageId();

            Log.d(TAG, "Got FCM: " + messageId);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(data.get("custom_title"))
                            .setContentText(data.get("custom_body"))
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_ALL);

            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);

            int mNotificationId = 1; //(int)System.currentTimeMillis();
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }
    }
}
