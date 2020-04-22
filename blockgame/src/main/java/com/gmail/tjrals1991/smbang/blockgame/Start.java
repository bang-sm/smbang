package com.gmail.tjrals1991.smbang.blockgame;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {
    TextView tvTime,tvPoint;
    int time=3; //시간값
    int point=0;

    //떨어지는 블럭의 이미지뷰 배열객체
    ImageView[] iv = new ImageView[8];

    private int soundID_OK;
    private int soundID_Error;

    private Vibrator vibrator;
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;

    //다이얼로그 아이디
    final int DIALOG_TIMEOVER=1;
    boolean back=false;
    //시간
    Handler handler=new Handler();

    GameThread gameThread=new GameThread();

    int [] img={R.drawable.block_red,R.drawable.block_green,R.drawable.block_blue};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //상태바 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);

        tvTime=findViewById(R.id.tvTime);
        tvPoint=findViewById(R.id.tvPoint);

        ImageView ivRed=findViewById(R.id.ivRed);
        ImageView ivGreen=findViewById(R.id.ivGreen);
        ImageView ivBlue=findViewById(R.id.ivBlue);

        iv[0]=findViewById(R.id.ivBlock1);
        iv[1]=findViewById(R.id.ivBlock2);
        iv[2]=findViewById(R.id.ivBlock3);
        iv[3]=findViewById(R.id.ivBlock4);
        iv[4]=findViewById(R.id.ivBlock5);
        iv[5]=findViewById(R.id.ivBlock6);
        iv[6]=findViewById(R.id.ivBlock7);
        iv[7]=findViewById(R.id.ivBlock8);

        //게임이 시작되면 초기화 블락의 색상 랜덤지정
        for (int i = 0; i <iv.length ; i++) {
            //0,1,2 빨 초 파
            int num=new Random().nextInt(3);//0,1,2
            iv[i].setImageResource(img[num]);
            iv[i].setTag(num+""); //버튼의 색상 판단하기 위한 값으로 활용용
       }

        //점수초기화
        point=0;
        tvPoint.setText("점수 : "+point);
        ivRed.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivBlue.setOnClickListener(this);


        new GameThread().start();
    }
    @Override
    public void onClick(View v) {
        //버튼을 눌렀을떄 호출되는 콜백
        //블럭과 같은 색깔의 버튼이 눌렀는지 판별, 같은 블럭이면 이미지 블럭 한칸씩 내려오기

        boolean isOk=false;
        ImageView imageView= (ImageView) v;
        switch (imageView.getId()){
            //맨 아래 블랙 이미지뷰 의 색상과 일치하는 버튼인지 판정
            case R.id.ivRed:
                if("0".equals(iv[7].getTag().toString())){
                    isOk=true;
                }
                break;
            case R.id.ivBlue:
                if("2".equals(iv[7].getTag().toString())){
                    isOk=true;
                }
                break;
            case R.id.ivGreen:
                if("1".equals(iv[7].getTag().toString())){
                    isOk=true;
                }
                break;
        }
        if(isOk){
            for (int i=iv.length-2;i>=0; i--) {
               int num=Integer.parseInt(iv[i].getTag().toString());
               iv[i+1].setImageResource(img[num]);
               iv[i+1].setTag(num+"");
            }
            int num=new Random().nextInt(3);
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num+"");

            vibrator.vibrate(200);
            soundPool.play(soundID_OK,1,1,0,0,1);
            point++;
            tvPoint.setText("점수: "+point);
        }else{
            vibrator.vibrate(new long[]{20,80,20,80,20,80},-1);
            soundPool.play(soundID_Error,1,1,0,0,1);
            point--;
            tvPoint.setText("점수: "+point);
        }

    }
    //시간표시 ,게임진행 쓰레드 시작작
    class GameThread extends Thread{
        @Override
        public void run() {
            //시간을 1초마다 다시 표기(업데이트)
            //핸들러 사용하여 화면 UI업데이트
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(time>=0){
                        tvTime.setText("시간: "+time);
                        if(time> 0){
                            time--;
                            handler.postDelayed(this,1000);
                        }else{
                            AlertDialog.Builder builder
                                    =new AlertDialog.Builder(Start.this);
                            builder.setTitle("타임아웃")
                                    .setNegativeButton("그만하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                                    .setPositiveButton("다시하기", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //게임 리셋하고 새게임 시작
                                            time=3;
                                            point=0;
                                            tvPoint.setText("시간: "+time);
                                            tvPoint.setText("점수: "+point);
                                            if(!back){
                                                gameThread.start();
                                            }else{
                                                return;
                                            }
                                        }
                                    });
                            builder.show();
                        }
                    }
                }
            },1000);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gameThread.interrupt();
        gameThread.setDaemon(true);
        back=true;
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        soundPool=new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK=soundPool.load(Start.this,R.raw.gun3,1);
        soundID_Error=soundPool.load(Start.this,R.raw.error,1);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.bgm);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //자원해제
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        finish();
    }
}
