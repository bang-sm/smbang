package com.lec.android.a004_wideget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    RadioGroup rg;
    Button btnResult;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        rg=findViewById(R.id.rg);
        btnResult=findViewById(R.id.btnResult);
        tvResult=findViewById(R.id.tvResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=rg.getCheckedRadioButtonId();
                RadioButton rb=findViewById(id);

                tvResult.setText(rb.getText());
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                tvResult.setText(radioButton.getText());
            }
        });

    }
}
