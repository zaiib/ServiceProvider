package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serviceprovider.serviceprovider.Databases.HistoryClient;
import com.serviceprovider.serviceprovider.R;

public class ViewCompleteTask extends AppCompatActivity {

    TextView taskName,date,bdget,location,address,tasker,desription;
    Button buWhatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complete_task);

        init();

        final String title = getIntent().getStringExtra("taskTitleName");
        final String taskAddress = getIntent().getStringExtra("addressUser");
        final String amountTasker = getIntent().getStringExtra("amounted");
        final String budgetTasker = getIntent().getStringExtra("budgetTask");
        final String dateTasker = getIntent().getStringExtra("dateTask");
        final String despTasker = getIntent().getStringExtra("taskDes");
        final String locTasker = getIntent().getStringExtra("taskLocations");
        final String phoneTasker = getIntent().getStringExtra("phoneTask");
        taskName.setText(title);
        address.setText("Address: "+taskAddress);
        tasker.setText("Tasker: "+amountTasker);
        bdget.setText("Budget: "+budgetTasker);
        date.setText(dateTasker);
        desription.setText(despTasker);
        location.setText(locTasker);
        buWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsApp(phoneTasker);
                HistoryClient historyClient = new HistoryClient(ViewCompleteTask.this);
                historyClient.addUser(title,despTasker,amountTasker,budgetTasker,taskAddress,phoneTasker,dateTasker,locTasker);
            }
        });
    }

    private void whatsApp(String phoneTasker) {
        Uri uri = Uri.parse("smsto:" + phoneTasker);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(i);
    }


    private void init() {
//        TextView taskName,date,bdget,location,address,tasker,desription;
//        Button buWhatsApp,buCall;
        taskName = findViewById(R.id.textTitle);
        date = findViewById(R.id.textDate);
        bdget = findViewById(R.id.textBudget);
        tasker = findViewById(R.id.ttextTaskers);
        location = findViewById(R.id.textLocation);
        address = findViewById(R.id.textAddress);
        desription = findViewById(R.id.textDescription);
        buWhatsApp = findViewById(R.id.buttonWhatsApp);
    }
}
