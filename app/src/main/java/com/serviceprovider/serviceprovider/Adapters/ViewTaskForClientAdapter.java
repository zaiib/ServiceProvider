package com.serviceprovider.serviceprovider.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.serviceprovider.serviceprovider.Activities.ViewCompleteTask;
import com.serviceprovider.serviceprovider.Model.TaskViewToClientModel;
import com.serviceprovider.serviceprovider.R;

import java.util.List;

public class ViewTaskForClientAdapter extends RecyclerView.Adapter<ViewTaskForClientAdapter.ViewHolder> {

    private Context mContext;
    private List<TaskViewToClientModel> mData;

    public ViewTaskForClientAdapter(Context mContext, List<TaskViewToClientModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        View view;
        view = inflater.inflate(R.layout.view_tasks_for_client,parent,false);
        //checkOut = view.findViewById(R.id.checkOut);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.btnViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ViewCompleteTask.class);
                intent.putExtra("taskTitleName",mData.get(viewHolder.getAdapterPosition()).getTaskName());
                intent.putExtra("addressUser",mData.get(viewHolder.getAdapterPosition()).getUserAddress());
                intent.putExtra("amounted",mData.get(viewHolder.getAdapterPosition()).getTaskerAmount());
                intent.putExtra("budgetTask",mData.get(viewHolder.getAdapterPosition()).getTaskBudget());
                intent.putExtra("dateTask",mData.get(viewHolder.getAdapterPosition()).getTaskDate());
                intent.putExtra("taskDes",mData.get(viewHolder.getAdapterPosition()).getTaskDesp());
                intent.putExtra("taskLocations",mData.get(viewHolder.getAdapterPosition()).getUserLocation());
                intent.putExtra("phoneTask",mData.get(viewHolder.getAdapterPosition()).getUserPhoneNumber());

                mContext.startActivity(intent);
                // Toast.makeText(mContext, "View Task", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTaskName());
        holder.budget.setText("Budegt: "+mData.get(position).getTaskBudget());
        holder.tasker.setText("Taskers: "+mData.get(position).getTaskerAmount());
        holder.date.setText(mData.get(position).getTaskDate());
        //  "Budegt: "+
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,budget,tasker,date;
        Button btnViewTask;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTaskTitleCLient);
            budget = itemView.findViewById(R.id.tvTaskBudget);
            tasker = itemView.findViewById(R.id.tvTaskerClient);
            date = itemView.findViewById(R.id.tvDateTask);
            btnViewTask = itemView.findViewById(R.id.btnViewTask);
        }
    }

}
