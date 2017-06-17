package com.pigmal.android.playground.androidplayground.accelerometer;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.pigmal.android.playground.androidplayground.R;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "Accelerometer";
    SensorManager mSensorManager;
    Sensor mSensor;

    LineChart mChart;

    String[] mLabels = new String[]{"x", "y", "z"};
    int[] mColors = new int[]{Color.RED, Color.GREEN, Color.BLUE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mChart = (LineChart) findViewById(R.id.lineChart);
        mChart.setData(new LineData());
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

        LineData data = mChart.getLineData();
        if (data != null) {
            for (int i = 0; i < 3; i++) {
                ILineDataSet set = data.getDataSetByIndex(i);
                if (set == null) {
                    set = createSet(mLabels[i], mColors[i]);
                    data.addDataSet(set);
                }

                data.addEntry(new Entry(set.getEntryCount(), event.values[i]), i);
                data.notifyDataChanged();
            }

            mChart.notifyDataSetChanged();
            mChart.setVisibleXRange(50, 50);
            mChart.getXAxis().setDrawLabels(false);
            mChart.moveViewToX(data.getEntryCount());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private LineDataSet createSet(String label, int color) {
        LineDataSet set = new LineDataSet(null, label);
        set.setLineWidth(2.5f);
        set.setColor(color);
        set.setDrawCircles(false);
        set.setDrawValues(false);

        return set;
    }
}
