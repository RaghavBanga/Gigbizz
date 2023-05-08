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
import com.gigbiz.databinding.RowItemTaskListBinding;
import com.gigbiz.models.TaskModel;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder>{

    private List<TaskModel> items;
    private OnClickListner onClickListner;
    private String type;

    public TaskListAdapter(List<TaskModel> items,OnClickListner  onClickListner){
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
        return new MyViewHolder(RowItemTaskListBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){
        TaskModel taskModel = items.get(position);
        holder.binding.projectTitle.setText(taskModel.getProjectName());
        holder.binding.compName.setText(taskModel.getComp_name());
        holder.binding.trainingType.setText(taskModel.getMode());
        holder.binding.projectStatus.setText(taskModel.getProject_status());
        holder.binding.price.setText(taskModel.getProject_price());

        Glide.with(holder.itemView.getContext())
                .load(taskModel.getImage())
                .centerCrop()
                .placeholder(R.drawable.logogigbiz)
                .into(holder.binding.logo);

        if (taskModel.getMode().equals("Offline")){
            holder.binding.trainingType.setBackgroundResource(R.drawable.red_text);
        }else {
            holder.binding.trainingType.setBackgroundResource(R.drawable.green_text);
        }

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

        RowItemTaskListBinding binding;

        public MyViewHolder(RowItemTaskListBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}