package com.lec.android.a006_wideget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    SeekBar seekBar;
    TextView tvResult;

    int value=0;
    int add=2;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        seekBar = findViewById(R.id.seekBar);
        tvResult = findViewById(R.id.tvResult);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //값의 변화가 생겻을떄
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvResult.setText(progress + " / " + fromUser);

            }

            //트래킹시장할떄
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트래킹시작", Toast.LENGTH_SHORT).show();
            }

            //끝날때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트래킹끝끝", Toast.LENGTH_SHORT).show();
            }

        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max=seekBar.getMax();

                while (true){
                    value=seekBar.getProgress()+add;
                    if(value>max || value<0){
                        add=-add;
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(value);
                        }
                    });


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
