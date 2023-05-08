package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.databinding.RowItemForReportListBinding;
import com.gigbiz.models.ApprovedGood;

import java.util.List;

public class ApprovedListAdapter extends RecyclerView.Adapter<ApprovedListAdapter.MyViewHolder> {

    //    private List<Approved> items;
    private List<ApprovedGood> items1;
    private OnClickListner onClickListner;

    /***
     * use for flipkart list
     * */
//    public ApprovedListAdapter(List<Approved> items,OnClickListner  onClickListner){
//        this.items = items;
//        this.onClickListner = onClickListner;
//    }

    /***
     * use for goodWorker list
     * */
    public ApprovedListAdapter(List<ApprovedGood> items, OnClickListner onClickListner) {
        this.items1 = items;
        this.onClickListner = onClickListner;
    }

    /***
     * not in use
     * */
//
//    public TaskListAdapter(List<TaskModel> items){
//        this.items = items;
//    }

    /***
     * use for flipkart list
     * */
//    public void setNewList(List<Approved> items){
//        this.items = items;
//        notifyDataSetChanged();
//
//    }

    /**
     * Use for GoodWorker Form
     **/
    public void setNewList(List<ApprovedGood> items) {
        this.items1 = items;
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowItemForReportListBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
/***
 * use for flipkart list
 * */
        //        Approved taskModel = items.get(position);
//        holder.binding.submissionId.setText(taskModel.getShopNo());
//        holder.binding.date.setText(taskModel.getCity());
//        holder.binding.status.setText("Approved");

/***
 * use for goodWorker list
 * */
        ApprovedGood taskModel = items1.get(position);
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
    public int getItemCount() {
//        return items.size();
        return items1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        RowItemForReportListBinding binding;

        public MyViewHolder(RowItemForReportListBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}