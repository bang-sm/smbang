package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };
    void doUpload(int n){
        Toast.makeText(getApplicationContext(),"업로드완료",Toast.LENGTH_SHORT).show();
    }
    public void mOnClick1(View v){
        new AlertDialog.Builder(this).setTitle("질문1").setMessage("업로드하시겠습니까")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.sendEmptyMessageDelayed(1,3000);
                    }
                })
                .setNegativeButton("아니오",null).show();
    }

    /*  예제# 2 : Handler 로 Runnable 을 지연(delay)하여 보냄(post)
   메인스레드가 메인스레드 자신에게 Runnable 을 보내는 경우임
   postDelayed(Runnable) 사용
    */

    public void mOnClick2(View v){
        new AlertDialog.Builder(this)
                .setTitle("질문2")
                .setMessage("업로드하시겠습니까")
                .setPositiveButton("예",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doUpload(2);
                            }
                        },3000);
                    }
                })
                .setNegativeButton("아니오",null)
                .show();
    }

    public void mOnClick3(View v){
        new AlertDialog.Builder(this)
                .setTitle("질문2")
                .setMessage("업로드하시겠습니까")
                .setPositiveButton("예",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //버튼을 동해서도 런러블 생성해서 보낼수있다.
                        Button btnUpload=findViewById(R.id.btnUpload3);
                        btnUpload.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doUpload(3);
                            }
                        },3000);
                    }
                })
                .setNegativeButton("아니오",null)
                .show();
    }
    public void mOnClick4(View v){
        new AlertDialog.Builder(this)
                .setTitle("질문2")
                .setMessage("업로드하시겠습니까")
                .setPositiveButton("예",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Timer timer=new Timer();
                        TimerTask task=new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(4);
                            }
                        };
                        timer.schedule(task,3);
                    }
                })
                .setNegativeButton("아니오",null)
                .show();
    }

}
