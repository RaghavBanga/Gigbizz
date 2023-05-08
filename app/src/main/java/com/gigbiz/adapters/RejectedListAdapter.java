package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.RowItemForReportListBinding;
import com.gigbiz.models.RejectedGood;

import java.util.List;

public class RejectedListAdapter extends RecyclerView.Adapter<RejectedListAdapter.MyViewHolder>{

//    private List<Rejected> items;
    private List<RejectedGood> items1;

    private OnClickListner onClickListner;

//    public RejectedListAdapter(List<Rejected> items,OnClickListner  onClickListner){
//        this.items = items;
//        this.onClickListner = onClickListner;
//    }
//
//    public TaskListAdapter(List<TaskModel> items){
//        this.items = items;
//    }

    public RejectedListAdapter(List<RejectedGood> items,OnClickListner  onClickListner){
        this.items1 = items;
        this.onClickListner = onClickListner;
    }

//    public void setNewList(List<Rejected> items){
//        this.items = items;
//        notifyDataSetChanged();
//
//    }

    public void setNewList(List<RejectedGood> items){
        this.items1 = items;
        notifyDataSetChanged();

    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(RowItemForReportListBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){
//        Rejected taskModel = items.get(position);
//        holder.binding.submissionId.setText(taskModel.getShopNo());
//        holder.binding.date.setText(taskModel.getCity());
//        holder.binding.status.setText("Rejected");

        /***
         * use for goodWorker list
         * */
        RejectedGood taskModel = items1.get(position);
        holder.binding.submissionId.setText(taskModel.getGwTeamleaderName());
        holder.binding.date.setText(taskModel.getGwEmpCode());
        holder.binding.status.setText(taskModel.getGwType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onTaskItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount(){
        return items1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        RowItemForReportListBinding binding;

        public MyViewHolder(RowItemForReportListBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}