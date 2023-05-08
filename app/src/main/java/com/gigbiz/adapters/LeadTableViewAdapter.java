package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.databinding.TableForPayoutreportGenerationBinding;
import com.gigbiz.models.LeadDetailModel;

import java.util.List;

public class LeadTableViewAdapter extends RecyclerView.Adapter<LeadTableViewAdapter.MyViewHolder>{

    private List<LeadDetailModel> items1;

    private OnClickListner onClickListner;

    public LeadTableViewAdapter(List<LeadDetailModel> items){
        this.items1 = items;
    }

    public void setNewList(List<LeadDetailModel> items){
        this.items1 = items;
        notifyDataSetChanged();

    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(TableForPayoutreportGenerationBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){

        MyViewHolder rowViewHolder = (MyViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            holder.binding.projectname.setBackgroundColor(holder.itemView.getResources().getColor(R.color.back_icon_color));
            holder.binding.inprocess.setBackgroundColor(holder.itemView.getResources().getColor(R.color.back_icon_color));
            holder.binding.approved.setBackgroundColor(holder.itemView.getResources().getColor(R.color.back_icon_color));
            holder.binding.rejected.setBackgroundColor(holder.itemView.getResources().getColor(R.color.back_icon_color));
            holder.binding.totalpayout.setBackgroundColor(holder.itemView.getResources().getColor(R.color.back_icon_color));


            holder.binding.projectname.setText("Project Name");
            holder.binding.inprocess.setText("In Process");
            holder.binding.approved.setText("Approved");
            holder.binding.rejected.setText("Rejected");
            holder.binding.totalpayout.setText("LocTotal Payout");

        } else {
            LeadDetailModel modal = items1.get(rowPos-1);

            // Content Cells. Content appear here
//            rowViewHolder.txtRank.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_bg);
//            rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);

            holder.binding.projectname.setText(modal.getProjectName());
            holder.binding.inprocess.setText((modal.getInprocesss()));
            holder.binding.approved.setText((modal.getApproved()));
            holder.binding.rejected.setText((modal.getRejected()));
            holder.binding.totalpayout.setText((modal.getTotalpayout()));

        }
    }

    @Override
    public int getItemCount(){
        return items1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TableForPayoutreportGenerationBinding binding;

        public MyViewHolder(TableForPayoutreportGenerationBinding b){
            super(b.getRoot());
            binding = b;
        }
    }
}