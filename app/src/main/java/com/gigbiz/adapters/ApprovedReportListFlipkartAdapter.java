package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.RowItemForReportListBinding;
import com.gigbiz.models.ApprovedFlipkart;

import java.util.List;

public class ApprovedReportListFlipkartAdapter  extends RecyclerView.Adapter<ApprovedReportListFlipkartAdapter.MyViewHolder>{

    private List<ApprovedFlipkart> items1;

    private OnClickListner onClickListner;

    public ApprovedReportListFlipkartAdapter(List<ApprovedFlipkart> items, OnClickListner  onClickListner){
        this.items1 = items;
        this.onClickListner = onClickListner;
    }

    public void setNewList(List<ApprovedFlipkart> items){
        this.items1 = items;
        notifyDataSetChanged();

    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(RowItemForReportListBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){

        ApprovedFlipkart taskModel = items1.get(position);
        holder.binding.submissionId.setText(taskModel.getBdeMobileno());
        holder.binding.date.setText(taskModel.getBdeName());
        holder.binding.status.setText(taskModel.getProjectId());

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