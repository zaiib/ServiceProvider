package com.serviceprovider.serviceprovider.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.serviceprovider.serviceprovider.Databases.HistoryForCustomer;
import com.serviceprovider.serviceprovider.Model.HistoryCustomer;
import com.serviceprovider.serviceprovider.R;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolderHistory> {
    private Context mContext;
    private List<HistoryCustomer> mData;

    public AdapterHistory(Context mContext, List<HistoryCustomer> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.customer_history_design, parent, false);
        //checkOut = view.findViewById(R.id.checkOut);

        final ViewHolderHistory viewHolder = new ViewHolderHistory(view);
        viewHolder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String date = mData.get(viewHolder.getAdapterPosition()).getDate();
                HistoryForCustomer db = new HistoryForCustomer(mContext);
                db.deleteNote(date);
                Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
                mData.remove(viewHolder.getAdapterPosition());
                notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(mContext, "Delete from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistory holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.budget.setText(mData.get(position).getPrice());
        holder.tasker.setText(mData.get(position).getTaskerPerson());
        holder.date.setText(mData.get(position).getDate());
        holder.desp.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolderHistory extends RecyclerView.ViewHolder {

        TextView title;
        TextView budget;
        TextView tasker;
        TextView date;
        TextView desp;
        ImageView imgCancel;

        public ViewHolderHistory(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTaskTitle);
            budget = itemView.findViewById(R.id.tvBudget);
            tasker = itemView.findViewById(R.id.tvTaskerHistory);
            date = itemView.findViewById(R.id.tvDateHistory);
            desp = itemView.findViewById(R.id.tvDescription);
            imgCancel = itemView.findViewById(R.id.imgCancel);
        }
    }
}
