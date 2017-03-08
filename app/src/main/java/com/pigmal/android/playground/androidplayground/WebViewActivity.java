package com.pigmal.android.playground.androidplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView wv = (WebView)findViewById(R.id.main_webview);
        wv.loadUrl("http://google.com");
    }
}
