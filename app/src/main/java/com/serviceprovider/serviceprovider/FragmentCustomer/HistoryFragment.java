package com.serviceprovider.serviceprovider.FragmentCustomer;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serviceprovider.serviceprovider.Adapters.AdapterHistory;
import com.serviceprovider.serviceprovider.Databases.HistoryForCustomer;
import com.serviceprovider.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryForCustomer db;
    RecyclerView historyRecyclerView;
    List<com.serviceprovider.serviceprovider.Model.HistoryCustomer> mHistory;
    public HistoryFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_history, container, false);
        mHistory = new ArrayList<>();
        historyRecyclerView = view.findViewById(R.id.rvHistory);
        db = new HistoryForCustomer(getContext());
        Cursor cursor = db.alldata();
        if (cursor.getColumnCount() == 0) {

            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                final String title = cursor.getString(1);
                final String desp = cursor.getString(2);
                final String price = cursor.getString(4);
                final String tasker = cursor.getString(3);
                final String date = cursor.getString(7);

                com.serviceprovider.serviceprovider.Model.HistoryCustomer historyCustomer = new com.serviceprovider.serviceprovider.Model.HistoryCustomer();
                historyCustomer.setTitle(title);
                historyCustomer.setDescription(desp);
                historyCustomer.setPrice(price);
                historyCustomer.setTaskerPerson(tasker);
                historyCustomer.setDate(date);
                mHistory.add(historyCustomer);
                addInRecyclerView(mHistory);
            }
        }


        return view;
    }

    private void addInRecyclerView(List<com.serviceprovider.serviceprovider.Model.HistoryCustomer> mHistory) {
        AdapterHistory catgAdapter = new AdapterHistory(getContext(), mHistory);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecyclerView.setAdapter(catgAdapter);
    }
}
