package com.pigmal.android.playground.androidplayground;

import android.app.Application;

import com.parse.Parse;

public class ThisApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_KEY);
    }
}
