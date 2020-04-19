package com.lec.android.a008_practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Popup extends Activity{
    EditText popName,popAge,popAdress;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        popName=findViewById(R.id.popName);
        popAge=findViewById(R.id.popAge);
        popAdress=findViewById(R.id.popAdress);
        btnSave=findViewById(R.id.btnSave);

        Intent intent=getIntent();
        Profile pf=(Profile)intent.getSerializableExtra("profile");
        popName.setText(pf.getName());
        popAge.setText(pf.getAge());
        popAdress.setText(pf.getAdress());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile pf=new Profile();
                pf.setName(popName.getText().toString());
                pf.setAge(popAge.getText().toString());
                pf.setAdress(popAdress.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("popProfile",pf);
                setResult(101,intent);
                finish();
            }
        });
    }

    //팝업창 밖부분 눌러도 안꺼지게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    //뒤로가기버튼으로 팝업창 못끄게하려고고
   @Override
    public void onBackPressed() {
        return;
    }
}
