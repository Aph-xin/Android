package com.example.labbytedance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class ItemJumpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_jump);

        Intent getItt = getIntent();
        TextView jumpText = (TextView) findViewById(R.id.jump_text);
        String inputLab2 = getItt.getStringExtra("extra");
        String[] spiltText = inputLab2.split(" ");
        jumpText.setText("This page is from "+spiltText[2]);

        editText = (EditText) findViewById(R.id.input_message);
        btn_back = (Button) findViewById(R.id.btn_lab2_jump_back);
        btn_back.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lab2_jump_back:
                String data_return = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data_return", data_return);
                setResult(RESULT_OK, intent);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                finish();
            default:
                break;
        }
    }

}