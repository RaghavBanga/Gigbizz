package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.databinding.RowlayoutForHomeTaskBinding;
import com.gigbiz.models.TaskModel;

import java.util.List;

public class LifeInsuranceAdapter extends RecyclerView.Adapter<LifeInsuranceAdapter.MyViewHolder>{

    private List<TaskModel> items;
    private OnClickListner onClickListner;
    private String type;

    public LifeInsuranceAdapter(List<TaskModel> items, OnClickListner  onClickListner){
        this.items = items;
        this.onClickListner = onClickListner;
    }
//
//    public TaskListAdapter(List<TaskModel> items){
//        this.items = items;
//    }

    public void setNewList(List<TaskModel> items){
        this.items = items;
        notifyDataSetChanged();

    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(RowlayoutForHomeTaskBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){
        TaskModel taskModel = items.get(position);
        holder.binding.projectTitle.setText(taskModel.getProjectName());

        Glide.with(holder.itemView.getContext())
                .load(taskModel.getImage())
                .centerCrop()
                .placeholder(R.drawable.logogigbiz)
                .into(holder.binding.logo);


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

        RowlayoutForHomeTaskBinding binding;

        public MyViewHolder(RowlayoutForHomeTaskBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}