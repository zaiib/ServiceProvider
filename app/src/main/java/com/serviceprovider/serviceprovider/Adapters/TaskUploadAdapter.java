package com.serviceprovider.serviceprovider.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.serviceprovider.serviceprovider.Activities.UplaodTask;
import com.serviceprovider.serviceprovider.Model.UploadTaskModel;
import com.serviceprovider.serviceprovider.R;

import java.util.List;

public class TaskUploadAdapter extends RecyclerView.Adapter<TaskUploadAdapter.ViewHolder>{

    private Context mContext;
    private List<UploadTaskModel> mData;

    public TaskUploadAdapter(Context mContext, List<UploadTaskModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.upload_task_design, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);


        viewHolder.containerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UplaodTask.class);
                intent.putExtra("name",mData.get(viewHolder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTaskName.setText(mData.get(position).getName());
        holder.imgUploadTask.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgUploadTask;
        TextView tvTaskName;
        LinearLayout containerClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUploadTask = itemView.findViewById(R.id.imgUploadTask);
            tvTaskName = itemView.findViewById(R.id.tvTaskName);
            containerClick = itemView.findViewById(R.id.containerClick);
        }
    }
}
