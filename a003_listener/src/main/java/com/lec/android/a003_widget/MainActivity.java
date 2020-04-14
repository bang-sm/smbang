package com.lec.android.a003_widget;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    LinearLayout ll;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult=findViewById(R.id.tvResult);
        editText=findViewById(R.id.et);
        ll=findViewById(R.id.ll);
        final Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
        Button btn3=findViewById(R.id.btn3);

        Button btnInc= findViewById(R.id.btnInc);
        Button btnDec= findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size=tvResult.getTextSize();

                tvResult.setTextSize(0,size+3);
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size=tvResult.getTextSize();

                tvResult.setTextSize(0,size-3);
            }
        });


        //연습
        //+ - 버튼누르면 tvResult의 글씨가 점점 커지고 작아지게 하기



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),btn1.getText()+"",Toast.LENGTH_LONG).show();
                tvResult.setText("버튼2클릭됨");
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("myapp","버튼2가 롱클릭 되었습니다");
                tvResult.setText("버튼2롱클릭!");
                tvResult.setBackgroundColor(Color.GREEN);
                return false;
            }
        });

        //람다는 안드로이드 스튜디오 셋팅필요
        btn3.setOnClickListener((v) -> {
            Log.d("myapp","버튼3클릭됨");
            tvResult.setText("버튼3클릭됨");
            ll.setBackgroundColor(Color.BLUE);

        });

        Button btnA=findViewById(R.id.btnA);
        Button btnB=findViewById(R.id.btnB);
        Button btnC=findViewById(R.id.btnC);
        Button btnD=findViewById(R.id.btnD);
        Button btnE=findViewById(R.id.btnE);
        Button btnF=findViewById(R.id.btnF);
        Button btnClear=findViewById(R.id.btnClear);

        btnClear.setOnClickListener(this);


        class MyListener implements View.OnClickListener{
            String name;

            public MyListener(String name){
                this.name=name;
            }
            @Override
            public void onClick(View v) {
                String tag=(String)v.getTag();
                String text=(String)((Button)v).getText();
                String msg=String.format("%s 버튼 %s 이 클릭[%s]",name,text,tag);
                tvResult.setText(msg);
                editText.setText(editText.getText().append(name));
            }
        }

        btnA.setOnClickListener(new MyListener("안녕1"));
        btnB.setOnClickListener(new MyListener("안녕2"));
        btnC.setOnClickListener(new MyListener("안녕3"));
        btnD.setOnClickListener(new MyListener("안녕4"));
        btnE.setOnClickListener(new MyListener("안녕5"));
        btnF.setOnClickListener(new MyListener("안녕6"));

    }

    public void changeText(View v) {
        Log.d("myapp","버튼1 클릭되었습니다");
        tvResult.setText("버튼1이 클릭 되었습니다");
        //Toast.makeText(getApplicationContext(),"버튼1클릭",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        tvResult.setText("clear버튼 클릭");
        editText.setText("");
    }
}
