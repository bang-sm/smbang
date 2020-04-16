package com.lec.android.a006_wideget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1,pb2,pb3;

    int value=0 ;// 막대프로그레스 바
    int add=10;
    int value2=0 ;// 막대프로그레스 바
    int add2=1;
    Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1=findViewById(R.id.pb1);
        pb2=findViewById(R.id.pb2);
        pb3=findViewById(R.id.pb3);

        ToggleButton toggleButton=findViewById(R.id.toggleButton);
        Button button1=findViewById(R.id.button1);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pb1.setVisibility(View.INVISIBLE);
                }
                else{
                    pb1.setVisibility(View.VISIBLE);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=value+add;
                if(value>100||value<0){
                    value=-add;
                    Log.d("myadd","value"+value);
                }
                pb2.setProgress(value);
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    value2=value2+add2;
                    if(value>100||value<0){
                        add2=-add2;
                    }
                    //메인ui접근위해 핸들러 사용
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb3.setProgress(value2);
                        }
                    });
                    try {
                       Thread.sleep(100);
                    }catch (Exception e){

                    }
                }
            }
        });
        thread.start();
    }
}
