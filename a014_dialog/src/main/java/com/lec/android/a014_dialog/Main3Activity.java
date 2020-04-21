package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnTimePicker = findViewById(R.id.btnTimePicker);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPickerDialog(DIALOG_DATE); // 날짜 설정 다이얼로그 띄우기
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPickerDialog(DIALOG_TIME);
            }
        });
    } // end of onCreate

    protected Dialog showPickerDialog(int id) {

        switch (id){
            case DIALOG_DATE:
                DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                        //날짜 설정후 다이얼로그 빠져나올떄 콜백
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { //month 0부터 시작
                                Toast.makeText(getApplicationContext(),year+"년"+ (month+1) +"월"+dayOfMonth+"일 선택",Toast.LENGTH_SHORT).show();
                            }
                        },2020,3,21 //기본값 년원일

                );
                datePickerDialog.show();
                return datePickerDialog;
            case DIALOG_TIME:
                TimePickerDialog timePickerDialog=new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(getApplicationContext(),hourOfDay+"시"+minute +"분 선택",Toast.LENGTH_SHORT).show();
                            }
                        },17,21,false  //기본값 시,분   true면 24시간제 false면 12시간에 오전 오후 항목 표시도됨
                );
                timePickerDialog.show();
                return timePickerDialog;
        }

        return null;
    }
}


