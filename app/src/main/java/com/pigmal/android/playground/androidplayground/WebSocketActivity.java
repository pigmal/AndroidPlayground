package com.pigmal.android.playground.androidplayground;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import static okhttp3.ws.WebSocket.TEXT;

public class WebSocketActivity extends AppCompatActivity implements View.OnClickListener {
    static final String TAG = "WebSocketSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        try {
            if (mWebSocket != null) {
                mWebSocket.close(1000, "Good bye");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            initSocket("ws://echo.websocket.org");
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private void toastOnUiThread(final String msg) {
        final Context c = this.getApplicationContext();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSocket(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        WebSocketCall call = WebSocketCall.create(client, request);

//        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
//        client.getDispatcher().getExecutorService().shutdown();
        call.enqueue(new MyListener());

    }

    private WebSocket mWebSocket;
    class MyListener implements WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.v(TAG, "onOpen");
            mWebSocket = webSocket;
            try {
                webSocket.sendMessage(RequestBody.create(TEXT, "hellow world"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IOException e, Response response) {
            Log.v(TAG, "onFailure");
        }

        @Override
        public void onMessage(ResponseBody message) throws IOException {
            toastOnUiThread("onMessage : " + message.string());
        }

        @Override
        public void onPong(Buffer payload) {
            Log.v(TAG, "onPong");
        }

        @Override
        public void onClose(int code, String reason) {
            Log.v(TAG, "onClose");
        }
    }
}
