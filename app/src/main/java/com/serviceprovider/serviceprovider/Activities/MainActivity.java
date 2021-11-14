package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.serviceprovider.serviceprovider.Login.LoginClient;
import com.serviceprovider.serviceprovider.Login.LoginCustomer;
import com.serviceprovider.serviceprovider.R;

public class MainActivity extends AppCompatActivity {


    private Button btnUser,btnClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnClient = findViewById(R.id.btnClient);
        btnUser = findViewById(R.id.btnUsers);

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginClient.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(MainActivity.this, LoginCustomer.class);
                startActivity(intent);
            }
        });
    }
}