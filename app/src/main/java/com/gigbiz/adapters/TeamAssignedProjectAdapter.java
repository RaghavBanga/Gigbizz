package com.gigbiz.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.models.details;

import java.util.List;

public class TeamAssignedProjectAdapter extends RecyclerView.Adapter<TeamAssignedProjectAdapter.ViewHolder> {

    Context context;
    List<details> team_list;
    OnClickListner onClickListner;

    public TeamAssignedProjectAdapter(Context context, List<details> team_list,OnClickListner onClickListner) {
        this.context = context;
        this.team_list = team_list;
        this.onClickListner = onClickListner;
    }
    public void setNewList(List<details> payment_list) {
        this.team_list=payment_list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if(team_list !=null&&team_list.size()>0){
            details model=team_list.get(position);
            holder.name_tv.setText((model.getTeam_name()));
            holder.process_tv.setText(String.valueOf(model.getNo_of_project()));
            holder.process_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListner.onTaskItemClick(position);
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return team_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv,process_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            process_tv=itemView.findViewById(R.id.process_tv);
            name_tv=itemView.findViewById(R.id.name_tv);
        }
    }
}
