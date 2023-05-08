package com.gigbiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.R;
import com.gigbiz.models.Project_Report;

import java.util.List;

public class ProjectPayoutAdapter extends RecyclerView.Adapter<ProjectPayoutAdapter.ViewHolder> {

    Context context;
    List<Project_Report> payment_list;

    public ProjectPayoutAdapter(Context context, List<Project_Report> payment_list) {
        this.context = context;
        this.payment_list = payment_list;
    }

    public void setNewList(List<Project_Report> payment_list) {
        this.payment_list=payment_list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(payment_list !=null&&payment_list.size()>0){
            Project_Report model=payment_list.get(position);
            holder.name_tv.setText((model.getProject_name()));
            holder.process_tv.setText(String.valueOf(model.getSubmitted()));
            holder.approaved_tv.setText(String.valueOf(model.getApproved()));
            holder.rejected_tv.setText(String.valueOf(model.getRejected()));
            holder.total_tv.setText(String.valueOf(model.getTotal_payout()));
            holder.paid.setText(String.valueOf(model.getPaid()));
            holder.balance.setText(String.valueOf(model.getBalance()));
        }

    }

    @Override
    public int getItemCount() {
        return payment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv,process_tv,approaved_tv,rejected_tv,total_tv,paid,balance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            process_tv=itemView.findViewById(R.id.process_tv);
            name_tv=itemView.findViewById(R.id.name_tv);
            approaved_tv=itemView.findViewById(R.id.approved_tv);
            rejected_tv=itemView.findViewById(R.id.rejected_tv);
            total_tv=itemView.findViewById(R.id.total_tv);
            paid=itemView.findViewById(R.id.paid);
            balance=itemView.findViewById(R.id.balance);
        }
    }
}
