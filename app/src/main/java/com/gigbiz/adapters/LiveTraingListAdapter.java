package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListnerliveTraining;
import com.gigbiz.databinding.RowLivetrainingItemBinding;
import com.gigbiz.models.LiveTraining;

import java.util.List;


public class LiveTraingListAdapter extends RecyclerView.Adapter<LiveTraingListAdapter.MyViewHolder> {

    private List<LiveTraining> items;
    private OnClickListnerliveTraining onClickListner;


    public LiveTraingListAdapter(List<LiveTraining> items, OnClickListnerliveTraining onClickListner) {
        this.items = items;
        this.onClickListner = onClickListner;
    }

    public void setNewList(List<LiveTraining> items) {
        this.items = items;
        notifyDataSetChanged();

    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowLivetrainingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LiveTraining taskModel = items.get(position);
        holder.binding.trainingtittle.setText(taskModel.getTitle());
        holder.binding.trainingtime.setText(taskModel.getMeetingDate()+" | "+taskModel.getMeetingTime());

        if (taskModel.getStatus().equals("Active")){
            holder.binding.enrollme.setBackgroundColor(Color.RED);
        }else {
            holder.binding.enrollme.setBackgroundColor(Color.BLACK);
            holder.binding.enrollme.setText("Registered");
            holder.binding.enrollme.setEnabled(false);
        }
//        Glide.with(holder.itemView.getContext())
//                .load(taskModel.getIcon())
//                .centerCrop()
//                .into(holder.binding.logo);

//        if (position == 5) {
//            holder.binding.mainCard.setCardBackgroundColor(Color.WHITE);
//            holder.binding.mainCard.setCardElevation(0f);
//            Glide.with(holder.itemView.getContext())
//                    .load(R.drawable.blackerrow)
//                    .centerCrop()
//                    .into(holder.binding.logo);
//            holder.binding.categoriesName.setVisibility(View.GONE);
//        }
        holder.binding.enrollme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onTaskItemViewClick(position);
            }
        });

        holder.binding.mainitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onTaskItemsClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        RowLivetrainingItemBinding binding;

        public MyViewHolder(RowLivetrainingItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}