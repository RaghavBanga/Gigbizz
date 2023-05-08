package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.TransactionHistoryRowBinding;
import com.gigbiz.models.Redeem;

import java.util.List;


public class TransactionHIstoryAdapter extends RecyclerView.Adapter<TransactionHIstoryAdapter.MyViewHolder>{

    private List<Redeem> items;
    private OnClickListner onClickListner;
    private String type;


    public TransactionHIstoryAdapter(List<Redeem> items, OnClickListner  onClickListner){
        this.items = items;
        this.onClickListner = onClickListner;
    }
//
//    public TaskListAdapter(List<TaskModel> items){
//        this.items = items;
//    }

    public void setNewList(List<Redeem> items){
        this.items = items;
        notifyDataSetChanged();

    }

    public void settype(String type){
        this.type = type;

    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(TransactionHistoryRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){
        Redeem taskModel = items.get(position);
        holder.binding.rupees.setText(taskModel.getAmount()+" Rs");
        holder.binding.date.setText("Date:- "+taskModel.getCreatedDate());
        holder.binding.paymentStatus.setText(taskModel.getStatus());

//        Glide.with(holder.itemView.getContext())
//                .load(taskModel.getImage())
//                .centerCrop()
//                .placeholder(R.drawable.asmaa_logo)
//                .into(holder.binding.logo);

//        if (taskModel.getMode().equals("Offline")){
//            holder.binding.trainingType.setBackgroundResource(R.drawable.red_text);
//        }else {
//            holder.binding.trainingType.setBackgroundResource(R.drawable.green_text);
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onTaskItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TransactionHistoryRowBinding binding;

        public MyViewHolder(TransactionHistoryRowBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}