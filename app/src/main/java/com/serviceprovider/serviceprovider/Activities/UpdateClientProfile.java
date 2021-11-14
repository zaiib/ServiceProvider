package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serviceprovider.serviceprovider.Model.ClientProfileModel;
import com.serviceprovider.serviceprovider.R;

public class UpdateClientProfile extends AppCompatActivity {

    EditText edtName,edtEmail,edtPhone,edtWhatsApp,edtJob,edtDescription,edtBudget,edtExperience,edtGender,edtworkingTime;
    Button btnUpdate;
    FirebaseAuth auth;
    SharedPreferences pref;
    String prefName = "Clients_Profile";
    String id;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client_profile);

        auth = FirebaseAuth.getInstance();
        edtName = findViewById(R.id.edtNameClientNameUpdate);
        edtEmail =  findViewById(R.id.edtNameClientEmailUpdate);
        edtPhone = findViewById(R.id.edtNameClientPhoneUpdate);
        edtWhatsApp = findViewById(R.id.edtNameClientWhatsAppUpdate);
        edtJob =  findViewById(R.id.edtNameClientJobUpdate);
        edtDescription =  findViewById(R.id.edtNameClientDescUpdate);
        edtBudget =  findViewById(R.id.edtNameClientBudgetUpdate);
        edtExperience = findViewById(R.id.edtNameClientExperienceUpdate);
        edtGender = findViewById(R.id.edtNameClientGenderUpdate);
        edtworkingTime = findViewById(R.id.edtworkingTimeUpdate);
        btnUpdate =  findViewById(R.id.btnUpdate);


        pref = this.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        id =pref.getString(ID, "");
        edtName.setText(pref.getString(Name, ""));
        edtEmail.setText(pref.getString(EMAIL, ""));
        edtPhone.setText(pref.getString(PHONE, ""));
        edtGender.setText(pref.getString(GENDER, ""));
        edtWhatsApp.setText(pref.getString(WHATSAPP, ""));
        edtJob.setText(pref.getString(JOB, ""));
        edtDescription.setText(pref.getString(DESCRIPTION, ""));
        edtBudget.setText(pref.getString(BUDGET, ""));
        edtworkingTime.setText(pref.getString(TIME, ""));
        edtExperience.setText(pref.getString(EXPERIENCE, ""));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    updateUserInfo();
                }
            }
        });
    }

    public boolean validation(){
        String names = edtName.getText().toString();
        String emails = edtEmail.getText().toString();
        String phones = edtPhone.getText().toString();
        String whatsapps = edtWhatsApp.getText().toString();
        String jobs = edtJob.getText().toString();
        String desps = edtDescription.getText().toString();
        String budgets = edtBudget.getText().toString();
        String experiences = edtExperience.getText().toString();
        String genders = edtGender.getText().toString();
        String times = edtworkingTime.getText().toString();
        if (TextUtils.isEmpty(names)) {
            edtName.setError("UserName is mendatory");
            edtName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(emails)) {
            edtEmail.setError("Email is mendatory");
            edtEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(phones)) {
            edtPhone.setError("Phones is mendatory");
            edtPhone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(whatsapps)) {
            edtWhatsApp.setError("WhatsApp Number is mendatory");
            edtWhatsApp.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(jobs)) {
            edtJob.setError("Job is mendatory");
            edtJob.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(desps)) {
            edtDescription.setError("Description is mendatory");
            edtDescription.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(budgets)) {
            edtBudget.setError("budget is mendatory");
            edtBudget.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(experiences)) {
            edtExperience.setError("Experience is mendatory");
            edtExperience.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(genders)) {
            edtGender.setError("Gender is mendatory");
            edtGender.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(times)) {
            edtworkingTime.setError("workingTime is mendatory");
            edtworkingTime.requestFocus();
            return false;
        }
        return true;
    }
    private void updateUserInfo() {

        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();
        String whatsapp = edtWhatsApp.getText().toString();
        String job = edtJob.getText().toString();
        String desp = edtDescription.getText().toString();
        String budget = edtBudget.getText().toString();
        String experience = edtExperience.getText().toString();
        String gender = edtGender.getText().toString();
        String time = edtworkingTime.getText().toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client_Profile").child(id);
        ClientProfileModel profileModel = new ClientProfileModel(id,name,email,phone,whatsapp,job,desp,budget,experience,gender,time,"OFFLINE","Free For Work");
        databaseReference.setValue(profileModel);
        Toast.makeText(this, "Update Your Data", Toast.LENGTH_SHORT).show();
    }

}