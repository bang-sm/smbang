package com.lec.android.a004_wideget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    EditText op1,op2;
    Button btnPlus,btnMinus,btnMul,btnDiv;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        tvResult=findViewById(R.id.tvResult);


        op1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                op1.setBackgroundColor(Color.YELLOW);
                op2.setBackgroundColor(Color.alpha(0));
                tvResult.setText("");
            }
        });
        op2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                op1.setBackgroundColor(Color.alpha(0));
                op2.setBackgroundColor(Color.YELLOW);
                tvResult.setText("");
            }
        });


    }

    public void clickButton(View view) {
        double num1;
        double num2;
        op2.setBackgroundColor(Color.alpha(0));
        switch (view.getId()){
            case R.id.btnPlus:
                num1=Double.parseDouble(op1.getText().toString());
                num2=Double.parseDouble(op2.getText().toString());
                tvResult.setText(num1+num2+"");
                break;
            case R.id.btnMinus:
                num1=Double.parseDouble(op1.getText().toString());
                num2=Double.parseDouble(op2.getText().toString());
                tvResult.setText(num1-num2+"");
                break;
            case R.id.btnMul:
                num1=Double.parseDouble(op1.getText().toString());
                num2=Double.parseDouble(op2.getText().toString());
                tvResult.setText(num1*num2+"");
                break;
            case R.id.btnDiv:
                num1=Double.parseDouble(op1.getText().toString());
                num2=Double.parseDouble(op2.getText().toString());
                tvResult.setText(num1/num2+"");
                break;
        }
    }
}
