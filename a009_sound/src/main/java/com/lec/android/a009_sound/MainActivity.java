package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private final int[] SOUND_RES={R.raw.gun,R.raw.gun2,R.raw.gun3};

    int [] soundId=new int[SOUND_RES.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay1 = findViewById(R.id.btnPlay);
        Button btnPlay2 = findViewById(R.id.btnPlay2);
        Button btnPlay3 = findViewById(R.id.btnPlay3);
        Button btnStop = findViewById(R.id.btnStop);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //롤리팝이상에서는 아래와같이
            new SoundPool.Builder().setMaxStreams(10).build();
        }else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
        for (int i = 0; i <SOUND_RES.length ; i++) {
           soundId[i] =soundPool.load(this,//현재화면의제어권자
                   SOUND_RES[i],1 );
        }
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId[0],1,1,0,0,1f);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <soundId.length ; i++) {
                    soundPool.stop(soundId[i]);
                }
            }
        });
    }
}
