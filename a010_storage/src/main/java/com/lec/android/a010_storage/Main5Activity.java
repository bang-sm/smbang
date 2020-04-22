package com.lec.android.a010_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Main5Activity extends AppCompatActivity {

    EditText etInput;
    String sfName = "myFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        etInput = findViewById(R.id.etInput);

        //저장되어있던 값을 꺼내서 보여주기
        SharedPreferences sharedPreferences=getSharedPreferences(sfName,MODE_PRIVATE);
        String str=sharedPreferences.getString("name","");
        String xx=sharedPreferences.getString("xx","ABC");
        String yy=savedInstanceState.getString("yy","XVI");

        etInput.setText(str);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(sfName,MODE_PRIVATE);
        //저장하려면 에디터 객체필요
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String str=etInput.getText().toString();

        editor.putString("name",str);
        editor.commit();
    }
}
