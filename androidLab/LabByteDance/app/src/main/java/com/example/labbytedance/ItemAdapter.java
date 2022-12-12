package com.example.labbytedance;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<TextViewHolder> {
    @NonNull
    private List<String> ListItems = new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(ListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return ListItems.size();
    }

    public void notifyItems(@NonNull List<String> items) {
        ListItems.clear();
        ListItems.addAll(items);
        notifyDataSetChanged();
        String s = "abc";


    }




}
