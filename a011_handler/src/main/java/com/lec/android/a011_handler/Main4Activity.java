package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


//벨류1 1~10까지 증가시키기1초단위로
//10초에 도달하면 멈추고 토스트 띄우기
//메세지사용

//value2
//1~20까지 1초단위
//20초 도달하면 토스트
//핸들러ㅏ사용
public class Main4Activity extends AppCompatActivity {

    int count=0;
    int value4=0;
    TextView tvResult1,tvResult2,tvResult3,tvResult4;
    public static final int value1=1;
    BackThread backThread=new BackThread();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tvResult1=findViewById(R.id.tvResult1);
        tvResult2=findViewById(R.id.tvResult2);
        tvResult3=findViewById(R.id.tvResult3);
        tvResult4=findViewById(R.id.tvResult4);

        backThread.start();


        new CountDownTimer(15*1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                value4++;
                tvResult4.setText(value4);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"종료",Toast.LENGTH_SHORT).show();
            }
        }.start();

    }
    class BackThread extends Thread{
        @Override
        public void run() {
            while(true){
                count++;   // 작업스레드 값 증가
                if (count<11){
                    handler.sendEmptyMessage(value1);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    } // end class BackThread1

    Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    if(count>11){
                        Toast.makeText(getApplicationContext(),"value1종료",Toast.LENGTH_SHORT).show();
                    }else{
                        tvResult1.setText(count+"");
                    }
                    break;
            }
        }
    };


}
