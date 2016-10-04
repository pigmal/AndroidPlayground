package com.pigmal.android.playground.androidplayground;

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
            Log.d(TAG, "Got Data");
            Map<String, String> data = remoteMessage.getData();

            Log.d(TAG, "title: " + data.get("custom_title"));
            Log.d(TAG, "body: " + data.get("custom_body"));
        }
    }
}
