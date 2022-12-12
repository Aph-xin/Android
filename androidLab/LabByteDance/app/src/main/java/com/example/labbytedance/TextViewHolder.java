package com.example.labbytedance;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextView;

    public TextViewHolder(View v) {
        super(v);
        mTextView = itemView.findViewById(R.id.text);
        itemView.setOnClickListener(this);
    }

    public void bind(String text) {
        mTextView.setText(text);
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ItemJumpActivity.class);
        intent.putExtra("extra", mTextView.getText().toString());
        ((Activity)v.getContext()).startActivityForResult(intent, 1);
    }
}
