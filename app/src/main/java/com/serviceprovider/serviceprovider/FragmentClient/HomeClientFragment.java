package com.serviceprovider.serviceprovider.FragmentClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serviceprovider.serviceprovider.Adapters.ViewTaskForClientAdapter;
import com.serviceprovider.serviceprovider.Model.ClientProfileModel;
import com.serviceprovider.serviceprovider.Model.TaskViewToClientModel;
import com.serviceprovider.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;
public class HomeClientFragment extends Fragment {
    DatabaseReference reference;
    FirebaseDatabase db;
    private RecyclerView mRecyclerViewTasks;
    private  List<TaskViewToClientModel> modelList;
    private ViewTaskForClientAdapter adapter;
    private ProgressDialog pDialog;
    FirebaseAuth auth;
    SharedPreferences pref;
    private static final String ID ="id";
    private static final String Name ="names";
    private static final String EMAIL ="emails";
    private static final String PHONE ="phones";
    private static final String GENDER ="genders";
    private static final String WHATSAPP ="whatsApps";
    private static final String JOB ="jobs";
    private static final String DESCRIPTION ="descps";
    private static final String BUDGET ="budgets";
    private static final String EXPERIENCE ="experiences";
    private static final String TIME ="times";
    private static final String ISONLINE ="isonlines";
    private static final String ISBOOKED ="bookeds";
    Switch aSwitchOnline,aSwitchOBooked;

    String prefName = "Clients_Profile";
    public HomeClientFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_home_client2, container, false);
        mRecyclerViewTasks = view.findViewById(R.id.rvTasks);
        aSwitchOnline = view.findViewById(R.id.switchOnline);
        aSwitchOBooked = view.findViewById(R.id.switchBooked);
        pDialog = new ProgressDialog(getContext());
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        modelList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerViewTasks.setLayoutManager(linearLayoutManager);
        showpDialog();
        // manageUserOnline();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelList.clear();
                DataSnapshot tasks = dataSnapshot.child("Tasks");
                for (DataSnapshot taskChilds : tasks.getChildren()){
                    TaskViewToClientModel model = new TaskViewToClientModel();
                    DataSnapshot address = taskChilds.child("addressUser");
                    DataSnapshot taskerAmount = taskChilds.child("amounts");
                    DataSnapshot budget = taskChilds.child("budgets");
                    DataSnapshot date = taskChilds.child("dates");
                    DataSnapshot description = taskChilds.child("despTask");
                    DataSnapshot location = taskChilds.child("locations");
                    DataSnapshot title = taskChilds.child("tasknames");
                    DataSnapshot phone = taskChilds.child("userPhone");
                    model.setUserAddress(String.valueOf(address.getValue()));
                    model.setTaskerAmount(String.valueOf(taskerAmount.getValue()));
                    model.setTaskBudget(String.valueOf(budget.getValue()));
                    model.setTaskDate(String.valueOf(date.getValue()));
                    model.setTaskDesp(String.valueOf(description.getValue()));
                    model.setUserLocation(String.valueOf(location.getValue()));
                    model.setTaskName(String.valueOf(title.getValue()));
                    model.setUserPhoneNumber(String.valueOf(phone.getValue()));
                    modelList.add(model);

                    hidepDialog();
                }
                adapter = new ViewTaskForClientAdapter(getContext(),modelList);
                mRecyclerViewTasks.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "OOPSS... Something went wrong.", Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        aSwitchOnline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    manageUserOnline();
                }else{
                    manageUserOffline();
                }
            }
        });
        aSwitchOBooked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    manageUserBooked();
                }else{
                    manageUserUnBooked();
                }
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

    private void manageUserOnline(){
        pref = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id;
        String name;
        String email,phone,gender,whatsapp,job,desp,budget,time,experience;
        String isOnline = "ONLINE";
        String isBooked = "Booked";

        id =pref.getString(ID, "");
        name =pref.getString(Name, "");
        email =pref.getString(EMAIL, "");
        phone =pref.getString(PHONE, "");
        gender =pref.getString(GENDER, "");
        whatsapp =pref.getString(WHATSAPP, "");
        job =pref.getString(JOB, "");
        desp =pref.getString(DESCRIPTION, "");
        budget =pref.getString(BUDGET, "");
        time =pref.getString(TIME, "");
        experience =pref.getString(EXPERIENCE, "");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client_Profile").child(id);
        ClientProfileModel profileModel = new ClientProfileModel(id,name,email,phone,whatsapp,job,desp,budget,experience,gender,time,"ONLINE","Free For Work");
        databaseReference.setValue(profileModel);
    }
    private void manageUserOffline(){
        pref = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id;
        String name;
        String email,phone,gender,whatsapp,job,desp,budget,time,experience;
        String isOnline = "ONLINE";
        String isBooked = "Booked";

        id =pref.getString(ID, "");
        name =pref.getString(Name, "");
        email =pref.getString(EMAIL, "");
        phone =pref.getString(PHONE, "");
        gender =pref.getString(GENDER, "");
        whatsapp =pref.getString(WHATSAPP, "");
        job =pref.getString(JOB, "");
        desp =pref.getString(DESCRIPTION, "");
        budget =pref.getString(BUDGET, "");
        time =pref.getString(TIME, "");
        experience =pref.getString(EXPERIENCE, "");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client_Profile").child(id);
        ClientProfileModel profileModel = new ClientProfileModel(id,name,email,phone,whatsapp,job,desp,budget,experience,gender,time,"OFFLINE","Free For Work");
        databaseReference.setValue(profileModel);
    }
    private void manageUserBooked(){
        pref = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id;
        String name;
        String email,phone,gender,whatsapp,job,desp,budget,time,experience;
        String isOnline = "ONLINE";
        String isBooked = "Booked";

        id =pref.getString(ID, "");
        name =pref.getString(Name, "");
        email =pref.getString(EMAIL, "");
        phone =pref.getString(PHONE, "");
        gender =pref.getString(GENDER, "");
        whatsapp =pref.getString(WHATSAPP, "");
        job =pref.getString(JOB, "");
        desp =pref.getString(DESCRIPTION, "");
        budget =pref.getString(BUDGET, "");
        time =pref.getString(TIME, "");
        experience =pref.getString(EXPERIENCE, "");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client_Profile").child(id);
        ClientProfileModel profileModel = new ClientProfileModel(id,name,email,phone,whatsapp,job,desp,budget,experience,gender,time,"OFFLINE","Booked");
        databaseReference.setValue(profileModel);
    }
    private void manageUserUnBooked(){
        pref = getActivity().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id;
        String name;
        String email,phone,gender,whatsapp,job,desp,budget,time,experience;
        String isOnline = "ONLINE";
        String isBooked = "Booked";

        id =pref.getString(ID, "");
        name =pref.getString(Name, "");
        email =pref.getString(EMAIL, "");
        phone =pref.getString(PHONE, "");
        gender =pref.getString(GENDER, "");
        whatsapp =pref.getString(WHATSAPP, "");
        job =pref.getString(JOB, "");
        desp =pref.getString(DESCRIPTION, "");
        budget =pref.getString(BUDGET, "");
        time =pref.getString(TIME, "");
        experience =pref.getString(EXPERIENCE, "");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client_Profile").child(id);
        ClientProfileModel profileModel = new ClientProfileModel(id,name,email,phone,whatsapp,job,desp,budget,experience,gender,time,"Online","Free For Work");
        databaseReference.setValue(profileModel);
    }

}
