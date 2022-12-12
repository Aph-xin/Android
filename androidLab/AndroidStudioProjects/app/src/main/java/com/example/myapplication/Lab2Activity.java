package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class Lab2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Lab2Adapter searchAdapter = new Lab2Adapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(searchAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            items.add("这是item " + String.valueOf(i));
        }
        searchAdapter.notifyItems(items);
    }

//    // receive data from last activity, and set it to a textview
//    String data_return = getIntent().getStringExtra("data_return");
//    TextView receiveContent = findViewById(R.id.rv);
//    receiveContent.setText(data_return);
//    searchAdapter.notifyItemChanged(position(data_return));

}
