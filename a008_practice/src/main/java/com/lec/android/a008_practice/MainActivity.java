package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProfileAdapter profileAdapter;
    RecyclerView recyclerView;
    ImageButton btnAdd;
    TextView tvName,tvAge,tvAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAdd);
        tvName=findViewById(R.id.tvName);
        tvAge=findViewById(R.id.tvAge);
        tvAdress=findViewById(R.id.tvAdress);


        recyclerView=findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        profileAdapter=new ProfileAdapter();

        recyclerView.setAdapter(profileAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile pf=new Profile();
                pf.setName(tvName.getText().toString());
                pf.setAge(tvAge.getText().toString());
                pf.setAdress(tvAdress.getText().toString());
                profileAdapter.items.add(pf);
                profileAdapter.notifyDataSetChanged();
            }
        });


    }

}
