package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.serviceprovider.serviceprovider.R;

public class ViewCompleteProfileEmployee extends AppCompatActivity {

    TextView name, email, job, description, budget, experince, gender, time, showOnline,tvisBooked;
    Button btnWhatsApp;
    Button btnCall;
    ImageView shareProfile;
    public static final int REQUEST_CALL =  1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complete_profile_employee);

        init();
        final String clientName = getIntent().getStringExtra("Username");
        final String clientEmail = getIntent().getStringExtra("emailClient");
        final String phonenumberWhatsApp = getIntent().getStringExtra("whatsApp");
        final String clientJob = getIntent().getStringExtra("job");
        final String client_decp = getIntent().getStringExtra("client_descriptions");
        final String clinetBudget = getIntent().getStringExtra("budgetClient");
        final String clientXxperince = getIntent().getStringExtra("experinceClient");
        final String clientGender = getIntent().getStringExtra("genderClient");
        final String workTime = getIntent().getStringExtra("working_Time");
        final String ONLINE = getIntent().getStringExtra("isOnline");
        final String booked = getIntent().getStringExtra("isBooked");
        // name, email, job, description, budget, experince, gender, time, showOnline
        name.setText(clientName);
        email.setText(clientEmail);
        job.setText(clientJob);
        description.setText(client_decp);
        budget.setText(clinetBudget);
        gender.setText(clientGender);
        time.setText(workTime);
        experince.setText(clientXxperince);
        showOnline.setText(ONLINE);
        tvisBooked.setText(booked);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call();
            }
        });
        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsApp(phonenumberWhatsApp);
            }
        });
        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewCompleteProfileEmployee.this, "Profile Shared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Call() {
        if (ContextCompat.checkSelfPermission(ViewCompleteProfileEmployee.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ViewCompleteProfileEmployee.this,new String[]{
                    Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else {
            final String phonenumberlocal = getIntent().getStringExtra("phoneClient");
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(phonenumberlocal)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Call();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void whatsApp(String phonenumberWhatsApp) {
        Uri uri = Uri.parse("smsto:" + phonenumberWhatsApp);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }


    private void init() {
        // name,email,job,description,budget,experince,gender,time;
        name = findViewById(R.id.edtClientNames);
        email = findViewById(R.id.tvEmail);
        job = findViewById(R.id.tvJobs);
        description = findViewById(R.id.tvDescpClient);
        budget = findViewById(R.id.tvBudgets);
        experince = findViewById(R.id.tvExperience);
        gender = findViewById(R.id.tvGender);
        time = findViewById(R.id.tvTimming);
        showOnline = findViewById(R.id.tvOnline);
        btnWhatsApp = findViewById(R.id.btnWhatsApp);
        btnCall = findViewById(R.id.btnCall);
        shareProfile = findViewById(R.id.imgShare);
        tvisBooked = findViewById(R.id.tvisBooked);
    }
}
