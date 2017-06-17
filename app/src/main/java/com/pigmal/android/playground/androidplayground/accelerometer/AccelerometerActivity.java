package com.pigmal.android.playground.androidplayground.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.pigmal.android.playground.androidplayground.R;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "Accelerometer";
    SensorManager mSensorManager;
    Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.v(TAG, "(x, y, z) = ("
            + String.valueOf(event.values[0]) + ", "
            + String.valueOf(event.values[1]) + ", "
            + String.valueOf(event.values[2]) + ")");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
