package com.lec.android.a016_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView1);

        SensorManager sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> list=sensorManager.getSensorList(Sensor.TYPE_ALL);
        String str ="<센서목록>\n 센서 총개수"+list.size();

        for (int i = 0; i <list.size() ; i++) {
            Sensor sensor = list.get(i);
            str+=list.get(i);
            str+="\n\n "+i+", 센서이름: "+sensor.getName()
                    +"\n"+"센서전원 -"+sensor.getPower()
                    +"\n"+"resoultion -"+sensor.getResolution()
                    +"\n"+"range - "+sensor.getMaximumRange();
                    ;
        }
        textView.setText(str);
    }
}
