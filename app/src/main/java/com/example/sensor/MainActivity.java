package com.example.sensor;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    Sensor accelerometer,gyrometer;
    TextView acc_x, acc_y, acc_z,gyro_x,gyro_y,gyro_z;
    Button btn_start,btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acc_x=findViewById(R.id.accelerate_x_value);
        acc_y=findViewById(R.id.accelerate_y_value);
        acc_z=findViewById(R.id.accelerate_z_value);
        gyro_x=findViewById(R.id.gyro_x_value);
        gyro_y=findViewById(R.id.gyro_y_value);
        gyro_z=findViewById(R.id.gyro_z_value);

        btn_start =findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log. d(TAG, "onCreate: Initializing Sensor Services");
                sensorManager = (SensorManager) getSystemService (Context.SENSOR_SERVICE) ;
                accelerometer = sensorManager. getDefaultSensor (Sensor.TYPE_ACCELEROMETER) ;
                if(accelerometer!= null){
                    sensorManager.registerListener( MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
                    Log. d(TAG, "onCreate: Registered accelerometer listener");
                }

                gyrometer = sensorManager. getDefaultSensor (Sensor.TYPE_GYROSCOPE) ;
                if(gyrometer!= null){
                    sensorManager.registerListener( MainActivity.this,gyrometer,SensorManager.SENSOR_DELAY_NORMAL);
                    Log. d(TAG, "onCreate: Registered Gyrometer listener");
                }

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();

            }
        });
    }



    @Override
    public void onAccuracyChanged (Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged (SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG,"onSensorChanged X: "+sensorEvent.values[0] +"onSensorChanged Y: "+sensorEvent.values[1] +"onSensorChanged Z: "+sensorEvent.values[2]);
            acc_x.setText("X Value: "+sensorEvent.values[0]);
            acc_y.setText("Y Value: "+sensorEvent.values[1]);
            acc_z.setText("Z Value: "+sensorEvent.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG,"onSensorChanged X: "+sensorEvent.values[0] +"onSensorChanged Y: "+sensorEvent.values[1] +"onSensorChanged Z: "+sensorEvent.values[2]);
            gyro_x.setText("X Value: "+sensorEvent.values[0]);
            gyro_y.setText("Y Value: "+sensorEvent.values[1]);
            gyro_z.setText("Z Value: "+sensorEvent.values[2]);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}