package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.serviceprovider.serviceprovider.FragmentCustomer.DatePickerFragment;
import com.serviceprovider.serviceprovider.R;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UploadDataForTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText edtDateTos,edtTaskerAmounts,edtBudgets,edtLocations,edtAddresss,edtPhones;
    Geocoder geocoder;
    List<Address> addresses;
    Button btnNextForOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data_for_task);
        init();
        final String longitude = getIntent().getStringExtra("long");
        final String latitude = getIntent().getStringExtra("lat");
        final String description = getIntent().getStringExtra("desps");
        final String title = getIntent().getStringExtra("names");
        final String taskTitle = String.valueOf(title);
        final String taskTitleDesp = String.valueOf(description);
        Toast.makeText(this, taskTitleDesp+" "+taskTitle, Toast.LENGTH_SHORT).show();
        //    Toast.makeText(this,longitude+" "+ latitude, Toast.LENGTH_SHORT).show();
        //  assert longitude != null;
        Double longe = Double.valueOf(longitude);
        //  assert latitude != null;
        Double lat = Double.valueOf(latitude);

        geocoder =  new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(lat,longe,1);
            String address = addresses.get(0).getAddressLine(0);
            String area = addresses.get(0).getLocality();
            String city = addresses.get(0).getAdminArea();
            String Country = addresses.get(0).getCountryName();
            String fullAddress = address+", "+area+", "+city+", "+Country;
            edtLocations.setText(address);
        }catch (IOException e){
            e.printStackTrace();
        }

        btnNextForOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadDataForTask.this,PlaceOrder.class);
                intent.putExtra("Titles",taskTitle);
                intent.putExtra("Descriptions",taskTitleDesp);
                intent.putExtra("date",edtDateTos.getText().toString());
                intent.putExtra("amount",edtTaskerAmounts.getText().toString());
                intent.putExtra("budget",edtBudgets.getText().toString());
                intent.putExtra("location",edtLocations.getText().toString());
                intent.putExtra("address",edtAddresss.getText().toString());
                intent.putExtra("phone",edtPhones.getText().toString());
                startActivity(intent);
            }
        });



    }

    private void init() {
        btnNextForOrder = findViewById(R.id.btnNextForOrders);
        edtDateTos =  findViewById(R.id.edtDateTo);
        edtTaskerAmounts =  findViewById(R.id.edtTaskerAmount);
        edtBudgets =  findViewById(R.id.edtBudget);
        edtLocations =  findViewById(R.id.edtLocation);
        edtAddresss =  findViewById(R.id.edtAddress);
        edtPhones =  findViewById(R.id.edtPhone);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        edtDateTos.setText(currentDateString);
        // edtDateFrom.setText(currentDateString);
    }

    public void btnPickDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }
}

