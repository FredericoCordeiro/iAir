package com.taes.iair.iair;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor pressure = null;
    private Sensor temperature = null;
    private Sensor humidity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }
        else {
            Toast.makeText(this, "Nao existe sensor de Pressão Atmosférica no seu dispositivo", Toast.LENGTH_SHORT).show();
        }
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            temperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        else{
            Toast.makeText(this, "Não existe sensor de Temperatura Ambiente no seu dispositivo", Toast.LENGTH_SHORT).show();
        }
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null){
            temperature = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        }
        else{
            Toast.makeText(this, "Não existe sensor de Humidade no seu dispositivo", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this, tem, Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float pressure_mbar = event.values[0];
        Toast.makeText(this, String.valueOf(pressure_mbar), Toast.LENGTH_SHORT).show();
    }

}
