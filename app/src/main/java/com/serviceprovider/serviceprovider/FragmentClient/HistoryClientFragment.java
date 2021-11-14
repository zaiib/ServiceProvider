package com.serviceprovider.serviceprovider.FragmentClient;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serviceprovider.serviceprovider.Adapters.ClientHistoryAdapter;
import com.serviceprovider.serviceprovider.Databases.HistoryClient;
import com.serviceprovider.serviceprovider.Model.ClientHistoryModel;
import com.serviceprovider.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryClientFragment extends Fragment {

    private RecyclerView rvCLientHistory;
    List<ClientHistoryModel> mData;
    HistoryClient db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryClientFragment newInstance(String param1, String param2) {
        HistoryClientFragment fragment = new HistoryClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_history_client, container, false);
        rvCLientHistory = view.findViewById(R.id.rvCLientHistory);
        mData = new ArrayList<>();
        db = new HistoryClient(getContext());
        Cursor cursor = db.alldata();
        if (cursor.getColumnCount() == 0) {

            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                final String title = cursor.getString(1);
                final String desp = cursor.getString(2);
                final String price = cursor.getString(4);
                final String date = cursor.getString(7);

                ClientHistoryModel model = new ClientHistoryModel();
                model.setTitles(title);
                model.setDescription(desp);
                model.setBudget(price);
                model.setDaTe(date);
                mData.add(model);
                addInRecyclerView(mData);
            }
        }
        return view;
    }

    private void addInRecyclerView(List<ClientHistoryModel> mData) {
        ClientHistoryAdapter adapter = new ClientHistoryAdapter(getContext(),mData);
        rvCLientHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCLientHistory.setAdapter(adapter);
    }
}
