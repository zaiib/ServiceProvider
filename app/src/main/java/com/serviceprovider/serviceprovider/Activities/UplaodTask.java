package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.serviceprovider.serviceprovider.R;

public class UplaodTask extends AppCompatActivity {
    EditText edtTitleTask,edtTaskDescriptions;
    Button btnNextTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplaod_task);
        edtTitleTask = findViewById(R.id.edtTitleTask);
        edtTaskDescriptions = findViewById(R.id.edtTaskDescription);
        btnNextTask = findViewById(R.id.btnNextTask);

        final String name = getIntent().getStringExtra("name");
        edtTitleTask.setText(name);
        final String titleName = String.valueOf(name);
        //  final String desp =  edtTaskDescriptions.getText().toString();
        btnNextTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UplaodTask.this, GetLocation.class);
                intent.putExtra("taskName",titleName);
                intent.putExtra("taskDescription",edtTaskDescriptions.getText().toString());
                startActivity(intent);
            }
        });
    }
}
