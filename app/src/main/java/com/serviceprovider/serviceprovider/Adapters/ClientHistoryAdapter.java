package com.serviceprovider.serviceprovider.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.serviceprovider.serviceprovider.Model.ClientHistoryModel;
import com.serviceprovider.serviceprovider.R;

import java.util.List;

public class ClientHistoryAdapter extends RecyclerView.Adapter<ClientHistoryAdapter.ClientHistoryViewHolder>{
    private Context mContext;
    List<ClientHistoryModel> mData;

    public ClientHistoryAdapter(Context mContext, List<ClientHistoryModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ClientHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.client_history_design,parent,false);
        //checkOut = view.findViewById(R.id.checkOut);

        final ClientHistoryViewHolder viewHolder =  new ClientHistoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHistoryViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitles());
        holder.budget.setText(mData.get(position).getBudget());
        holder.desp.setText(mData.get(position).getDescription());
        holder.date.setText(mData.get(position).getDaTe());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ClientHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView title,budget,date,desp;
        public ClientHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTaskHistoryClient);
            budget = itemView.findViewById(R.id.tvBudgetHistoryClient);
            date = itemView.findViewById(R.id.tvDateHistoryClient);
            desp = itemView.findViewById(R.id.tvDescriptionHistoryClient);
        }
    }
}
