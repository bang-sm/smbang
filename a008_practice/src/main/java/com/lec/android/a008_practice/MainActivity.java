package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProfileAdapter profileAdapter;
    RecyclerView recyclerView;
    ImageButton btnAdd;
    TextView tvName,tvAge,tvAdress;
    EditText etName,etAge,etAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAdd);
        tvName=findViewById(R.id.tvName);
        tvAge=findViewById(R.id.tvAge);
        tvAdress=findViewById(R.id.tvAdress);

        etName=findViewById(R.id.etName);
        etAge=findViewById(R.id.etAge);
        etAdress=findViewById(R.id.etAdress);


        recyclerView=findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        profileAdapter=new ProfileAdapter();

        recyclerView.setAdapter(profileAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equals("") || etAge.getText().toString().equals("") || etAdress.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"입력을 전부하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                Profile pf=new Profile();
                pf.setName(etName.getText().toString());
                pf.setAge(etAge.getText().toString());
                pf.setAdress(etAdress.getText().toString());
                profileAdapter.items.add(pf);
                profileAdapter.notifyDataSetChanged();
            }
        });

    }
}
