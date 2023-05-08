package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.RowItemForIdCardProjectBinding;
import com.gigbiz.models.IcardUrl;

import java.util.List;

public class IdCardSelectionAdapter extends RecyclerView.Adapter<IdCardSelectionAdapter.MyViewHolder> {

    private List<IcardUrl> items1;

    private OnClickListner onClickListner;

    public IdCardSelectionAdapter(List<IcardUrl> items, OnClickListner onClickListner) {
        this.items1 = items;
        this.onClickListner = onClickListner;
    }

    public void filterList(List<IcardUrl> filterdNames) {
        this.items1 = filterdNames;
        notifyDataSetChanged();
    }

    public void setNewList(List<IcardUrl> items) {
        this.items1 = items;
        notifyDataSetChanged();

    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowItemForIdCardProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        IcardUrl taskModel = items1.get(position);
        holder.binding.submissionId.setText(taskModel.getProjectName());
//        holder.binding.cardBg.setBackgroundColor(taskModel.getColor());
//        Glide.with(holder.itemView.getContext())
//                .load(taskModel.getImg1())
//                .into(holder.binding.logo);


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

        RowItemForIdCardProjectBinding binding;

        public MyViewHolder(RowItemForIdCardProjectBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}