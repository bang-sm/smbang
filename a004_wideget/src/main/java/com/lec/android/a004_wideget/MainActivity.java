package com.lec.android.a004_wideget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName,etPassword,etNumber,etEmail;
    TextView tvName,tvPassword,tvNumber,tvEmail,tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult=findViewById(R.id.tvResult);

        etName=findViewById(R.id.etName);
        etPassword=findViewById(R.id.etPassword);
        etNumber=findViewById(R.id.etNumber);
        etEmail=findViewById(R.id.etEmail);

        tvName=findViewById(R.id.etName);
        tvPassword=findViewById(R.id.tvPassword);
        tvNumber=findViewById(R.id.tvNumber);
        tvEmail=findViewById(R.id.tvEmail);

        //포커스 변화
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //hasFocus true-> 포커스받은경우 .false 잃은경우
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    v.setBackgroundColor(Color.YELLOW);
                }else{
                    v.setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });
        etPassword.setOnKeyListener(new View.OnKeyListener() {
            //keycode 눌린코드값
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tvResult.setText(((EditText)v).getText().toString());
                return false;
            }
        });

        //값의 변화
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                tvResult.setText("입력완료:" + actionId);
                return false;
            }
        });

    }
}
