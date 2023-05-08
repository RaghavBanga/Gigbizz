package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.ReportListSelectionRowBinding;
import com.gigbiz.models.ReportList;

import java.util.List;

public class ReportListSelectionAdapter extends RecyclerView.Adapter<ReportListSelectionAdapter.MyViewHolder> {

    private List<ReportList> items1;

    private OnClickListner onClickListner;

    public ReportListSelectionAdapter(List<ReportList> items, OnClickListner onClickListner) {
        this.items1 = items;
        this.onClickListner = onClickListner;
    }

    public void filterList(List<ReportList> filterdNames) {
        this.items1 = filterdNames;
        notifyDataSetChanged();
    }

    public void setNewList(List<ReportList> items) {
        this.items1 = items;
        notifyDataSetChanged();

    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ReportListSelectionRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ReportList taskModel = items1.get(position);
        holder.binding.projectName.setText(taskModel.getTitle());
//        holder.binding.cardBg.setBackgroundColor(taskModel.getColor());
        Glide.with(holder.itemView.getContext())
                .load(taskModel.getImg1())
                .into(holder.binding.logo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onTaskItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ReportListSelectionRowBinding binding;

        public MyViewHolder(ReportListSelectionRowBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}