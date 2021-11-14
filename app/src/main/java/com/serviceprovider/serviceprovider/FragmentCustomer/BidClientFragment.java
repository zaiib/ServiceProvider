package com.serviceprovider.serviceprovider.FragmentCustomer;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serviceprovider.serviceprovider.Adapters.GetClientProfileAdapter;
import com.serviceprovider.serviceprovider.Model.GetClientProfileDataModel;
import com.serviceprovider.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;


public class BidClientFragment extends Fragment {

    DatabaseReference reference;
    RecyclerView mRecyclerView;
    List<GetClientProfileDataModel> mData;
    GetClientProfileAdapter adapter;
    private ProgressDialog pDialog;

    public BidClientFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_bid_client, container, false);
        mRecyclerView = view.findViewById(R.id.rvClientProfile);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reference = FirebaseDatabase.getInstance().getReference().child("Client_Profile");
        mData = new ArrayList<>();
        showpDialog();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    GetClientProfileDataModel model = new GetClientProfileDataModel();
                    DataSnapshot budget = dataSnapshot1.child("budgetClient");
                    DataSnapshot description = dataSnapshot1.child("description");
                    DataSnapshot email = dataSnapshot1.child("emailClient");
                    DataSnapshot experience = dataSnapshot1.child("experinceClient");
                    DataSnapshot gender = dataSnapshot1.child("genderClient");
                    DataSnapshot booked = dataSnapshot1.child("isBooked");
                    DataSnapshot online = dataSnapshot1.child("isOnline");
                    DataSnapshot job = dataSnapshot1.child("job");
                    DataSnapshot clientName = dataSnapshot1.child("nameClient");
                    DataSnapshot phoneNumber = dataSnapshot1.child("phoneClient");
                    DataSnapshot whatsAppNum = dataSnapshot1.child("whatsApp");
                    DataSnapshot workingTimeClient = dataSnapshot1.child("workingTime");
                    model.setBudgetClient(String.valueOf(budget.getValue()));
                    model.setDescription(String.valueOf(description.getValue()));
                    model.setEmailClient(String.valueOf(email.getValue()));
                    model.setExperinceClient(String.valueOf(experience.getValue()));
                    model.setGenderClient(String.valueOf(gender.getValue()));
                    model.setBookedStatus(String.valueOf(booked.getValue()));
                    model.setOnlineStatus(String.valueOf(online.getValue()));
                    model.setJob(String.valueOf(job.getValue()));
                    model.setNameClient(String.valueOf(clientName.getValue()));
                    model.setPhoneClient(String.valueOf(phoneNumber.getValue()));
                    model.setWhatsApp(String.valueOf(whatsAppNum.getValue()));
                    model.setWorkingTime(String.valueOf(workingTimeClient.getValue()));
                    mData.add(model);
                    hidepDialog();
                }
                adapter = new GetClientProfileAdapter(getContext(),mData);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "OOPSS... Something went wrong.", Toast.LENGTH_SHORT).show();

                hidepDialog();
            }
        });
        return view;
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
