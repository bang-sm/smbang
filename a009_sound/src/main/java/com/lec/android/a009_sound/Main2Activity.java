package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnResume;
    private ImageView btnStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바

    MediaPlayer mediaPlayer;

    int pos;//재생위치
    boolean isTracking=false;
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (mediaPlayer.isPlaying()){  //재생중이라면
                pos= mediaPlayer.getCurrentPosition(); //현재 재생중인 위치
                if(!isTracking){
                    sb.setProgress(pos);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);
        sb = findViewById(R.id.sb);

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //값 변경될떄마다 호출
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //음악이 끝까지 연주완료되면
                if(seekBar.getMax()==progress && !fromUser){

                    btnPlay.setVisibility(View.VISIBLE);
                    btnPause.setVisibility(View.INVISIBLE);
                    btnResume.setVisibility(View.INVISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                    if(mediaPlayer!=null){
                        mediaPlayer.stop();
                    }
                }
            }
            ///드래그시작 하면
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking=true;
            }
            //드래그 마치면면
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pos=seekBar.getProgress();
                if(mediaPlayer!=null){
                    mediaPlayer.seekTo(pos);
                }
                isTracking=false;
            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //미디어플레이어 객체 초기화 ,재생
                mediaPlayer = MediaPlayer.create(
                        getApplicationContext(),
                        R.raw.chacha
                );
                mediaPlayer.setLooping(false);

                //재생이 끝나면 호출되는 콜백메소드
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("myadd","연주종료"+mp.getCurrentPosition()+" / "+mp.getDuration());
                    }
                });
                mediaPlayer.start();

                //음악의 재생시간 ms
                //SeekBar 의 범위를 음악의 재생시간으로 설정
                //시크바 스레드시작

                int duration=mediaPlayer.getDuration();
                sb.setMax(duration);
                new MyThread().start();

                btnPlay.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //음악종료
                pos=0;
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer.seekTo(0);//음악의 처음으로
                    mediaPlayer.release(); //자원해제
                    mediaPlayer=null;
                }
                sb.setProgress(0); //시그바도 초기위치로

                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=mediaPlayer.getCurrentPosition(); //재생중이던 현재위치 저장
                mediaPlayer.pause();

                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        //멈춘 시점부터 재시작
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(pos);
                mediaPlayer.start();
                new MyThread().start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer!=null){
            mediaPlayer.release(); //자원해제
        }
        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);
    }
}
