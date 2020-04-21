package com.gmail.tjrals1991.smbang.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// 현재 화면이 가로/세로 변경되지 않도록 지정하기
// AndroidManifest.xml 에 screenOrientation="portrait" 지정

// 액션바 없애기 -> styles.xml 에서 NoActionBar 지정
public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler handler=new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent=new Intent(getApplicationContext(),Main.class);
                startActivity(intent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(1,3000);
    }
}
