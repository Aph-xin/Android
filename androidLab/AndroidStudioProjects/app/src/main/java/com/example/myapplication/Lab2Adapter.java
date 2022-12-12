package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

public class Lab2Adapter extends RecyclerView.Adapter<Lab2Adapter.TextViewHolder> {

    private List<String> mItems = new ArrayList<>();
    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        public void bind(String text) {
            mTextView.setText(text);
        }

        @Override
        public void onClick(View view) {
            //deal with click event
            Intent intent = new Intent(view.getContext(), MyActivity.class);
            intent.putExtra("extra", mTextView.getText().toString());// name and value
            // int position = mTextView.getPosition(view);
            view.getContext().startActivity(intent);
        }


    }
}
