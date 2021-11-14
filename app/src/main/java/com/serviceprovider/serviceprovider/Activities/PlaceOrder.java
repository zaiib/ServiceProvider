package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serviceprovider.serviceprovider.Databases.HistoryForCustomer;
import com.serviceprovider.serviceprovider.Model.SendTaskToFirebaseModel;
import com.serviceprovider.serviceprovider.R;

public class PlaceOrder extends AppCompatActivity {

    TextView tvTitle, tvDesp, tvAddress, tvPhone, tvLocation, tvDate, tvTaskers, tvBudget;
    Button btnPlaceOrder;
    private HistoryForCustomer db;
    DatabaseReference databaseTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        init();
        databaseTask = FirebaseDatabase.getInstance().getReference("Tasks");
        db = new HistoryForCustomer(getApplicationContext());
//        intent.putExtra("Titles",taskTitle);
//        intent.putExtra("Descriptions",taskTitleDesp);
//        intent.putExtra("date",edtDateTos.getText().toString());
//        intent.putExtra("amount",edtTaskerAmounts.getText().toString());
//        intent.putExtra("budget",edtBudgets.getText().toString());
//        intent.putExtra("location",edtLocations.getText().toString());
//        intent.putExtra("address",edtAddresss.getText().toString());
//        intent.putExtra("phone",edtPhones.getText().toString());

        final String tasknames = getIntent().getStringExtra("Titles");
        final String DespTask = getIntent().getStringExtra("Descriptions");
        final String dates = getIntent().getStringExtra("date");
        final String amounts = getIntent().getStringExtra("amount");
        final String budgets = getIntent().getStringExtra("budget");
        final String locations = getIntent().getStringExtra("location");
        final String addressUser = getIntent().getStringExtra("address");
        final String userPhone = getIntent().getStringExtra("phone");

        tvTitle.setText(tasknames);
        tvDesp.setText(DespTask);
        tvAddress.setText(addressUser);
        tvPhone.setText(userPhone);
        tvLocation.setText(locations);
        tvDate.setText(dates);
        tvTaskers.setText(amounts);
        tvBudget.setText(budgets);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlaceOrder.this, "Order Placed", Toast.LENGTH_SHORT).show();
                db.addUser(tasknames, DespTask, amounts, budgets, addressUser, userPhone, dates, locations);
                addTaskToFirebase(tasknames, DespTask, addressUser, userPhone, locations, dates, amounts, budgets);
                Intent intent = new Intent(PlaceOrder.this, MainCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addTaskToFirebase(String titleName, String Desp, String address, String phone, String location, String date, String taskers, String budget) {


        String id =  databaseTask.push().getKey();
        SendTaskToFirebaseModel task = new SendTaskToFirebaseModel(id,titleName,Desp,taskers,budget,address,phone,date,location);

        assert id != null;
        databaseTask.child(id).setValue(task);
        Toast.makeText(this, "Task Added In firebase", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        tvTitle = findViewById(R.id.tvTitle);
        tvDesp = findViewById(R.id.tvDescp);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvLocation = findViewById(R.id.tvLocation);
        tvDate = findViewById(R.id.tvDate);
        tvTaskers = findViewById(R.id.tvTaskers);
        tvBudget = findViewById(R.id.tvBudget);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
    }
}
