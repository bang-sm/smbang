package com.lec.android.a008_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    PhonebookAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager =new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);

        adapter=new PhonebookAdapter();

        initAdapter(adapter);

        recyclerView.setAdapter(adapter);

        Button btnInsert=findViewById(R.id.btnInsert);
        Button btnAppend=findViewById(R.id.btnAppend);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(v);
            }
        });
        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appdendData(v);
            }
        });

    }
    protected void initAdapter(PhonebookAdapter adapter) {
        for (int i = 0; i < 10; i++) {
            int idx = D.next();
            adapter.addItem(new Phonebook(D.FACEID[idx], D.NAME[idx], D.PHONE[idx], D.EMAIL[idx]));
        }
    }
    protected void insertData(View v){
        int idx=D.next();

        //리스타 맨앞에 추가가
        adapter.addItem(0,new Phonebook(D.FACEID[idx],D.NAME[idx],D.PHONE[idx],D.EMAIL[idx]));
        adapter.notifyDataSetChanged();
    }
    protected void appdendData(View v){
        int idx=D.next();

        //리스타 맨앞에 추가가
        adapter.addItem(new Phonebook(D.FACEID[idx],D.NAME[idx],D.PHONE[idx],D.EMAIL[idx]));
        adapter.notifyDataSetChanged();
    }
}
