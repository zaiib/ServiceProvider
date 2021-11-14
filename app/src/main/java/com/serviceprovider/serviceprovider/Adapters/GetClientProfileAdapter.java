package com.serviceprovider.serviceprovider.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serviceprovider.serviceprovider.Activities.ViewCompleteProfileEmployee;
import com.serviceprovider.serviceprovider.Model.GetClientProfileDataModel;
import com.serviceprovider.serviceprovider.R;

import java.util.List;

public class GetClientProfileAdapter extends RecyclerView.Adapter<GetClientProfileAdapter.MyViewHolder> {

    private Context mContext;
    private List<GetClientProfileDataModel> mData;

    public GetClientProfileAdapter(Context mContext, List<GetClientProfileDataModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(mContext);
        View view;
        view = inflater.inflate(R.layout.client_profile_design,parent,false);
        //checkOut = view.findViewById(R.id.checkOut);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.clientContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewCompleteProfileEmployee.class);
//
                intent.putExtra("Username",mData.get(viewHolder.getAdapterPosition()).getNameClient());
                intent.putExtra("emailClient",mData.get(viewHolder.getAdapterPosition()).getEmailClient());
                intent.putExtra("phoneClient",mData.get(viewHolder.getAdapterPosition()).getPhoneClient());
                intent.putExtra("whatsApp",mData.get(viewHolder.getAdapterPosition()).getWhatsApp());
                intent.putExtra("job",mData.get(viewHolder.getAdapterPosition()).getJob());
                intent.putExtra("client_descriptions",mData.get(viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("budgetClient",mData.get(viewHolder.getAdapterPosition()).getBudgetClient());
                intent.putExtra("experinceClient",mData.get(viewHolder.getAdapterPosition()).getExperinceClient());
                intent.putExtra("genderClient",mData.get(viewHolder.getAdapterPosition()).getGenderClient());
                intent.putExtra("working_Time",mData.get(viewHolder.getAdapterPosition()).getWorkingTime());
                intent.putExtra("isOnline",mData.get(viewHolder.getAdapterPosition()).getOnlineStatus());
                intent.putExtra("isBooked",mData.get(viewHolder.getAdapterPosition()).getBookedStatus());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.clientTaskName.setText(mData.get(position).getJob());
        holder.clientTaskDesp.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout clientContainer;
        TextView clientTaskName;
        TextView clientTaskDesp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clientTaskName = itemView.findViewById(R.id.clientTaskName);
            clientContainer = itemView.findViewById(R.id.clientContainer);
            clientTaskDesp = itemView.findViewById(R.id.clientTaskDesp);
        }
    }
}
