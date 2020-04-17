package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


//화면이없는 액티비티 생성가능
public class CalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        int num1=intent.getIntExtra("num1",0);
        int num2=intent.getIntExtra("num2",0);

        intent.putExtra("plus",num1+num2);
        intent.putExtra("minus",num1-num2);

        //보내줬던 화면으로 다시 값 되돌려주기
        setResult(RESULT_OK,intent);

        finish(); //onDestory()와 동일
    }
}
