package com.serviceprovider.serviceprovider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serviceprovider.serviceprovider.Model.ClientProfileModel;
import com.serviceprovider.serviceprovider.R;

public class CreateClientProfile extends AppCompatActivity {

    EditText edtName,edtEmail,edtPhone,edtWhatsApp,edtJob,edtDescription,edtBudget,edtExperience,edtGender,edtworkingTime;
    Button btnDone;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String prefName = "Clients";
    private ProgressDialog pDialog;
    private static final String Name ="username";
    private static final String GENDER ="gender";
    private static final String EMAIL ="email";
    private static final String PHONE ="phone";
    DatabaseReference mDatabase;
    SharedPreferences prefs;
    String prefNames = "Clients_Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client_profile);

        init();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        mDatabase = FirebaseDatabase.getInstance().getReference("Client_Profile");
        prefs = getSharedPreferences(prefNames, Context.MODE_PRIVATE);
        pref = this.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (pref.contains(Name)){
            edtName.setText(pref.getString(Name,""));
        }if (pref.contains(GENDER)){
            edtGender.setText(pref.getString(GENDER,""));
        }if (pref.contains(EMAIL)){
            edtEmail.setText(pref.getString(EMAIL,""));
        }if (pref.contains(PHONE)){
            edtPhone.setText(pref.getString(PHONE,""));
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    //  showpDialog();
                    AddDataInFireBaseDataBase();
                }
            }
        });
    }
    public boolean validation(){
        //  edtName,edtEmail,edtPhone,edtWhatsApp,edtJob,edtDescription,edtBudget,edtExperience,edtGender
        String email = edtEmail.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String whatsApp = edtWhatsApp.getText().toString().trim();
        String job = edtJob.getText().toString().trim();
        String Descp = edtDescription.getText().toString().trim();
        String budget = edtBudget.getText().toString().trim();
        String experience = edtExperience.getText().toString().trim();
        String gender = edtGender.getText().toString().trim();
        String time = edtworkingTime.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            edtworkingTime.setError("Time is mendatory");
            edtworkingTime.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            edtName.setError("UserName is mendatory");
            edtName.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email is mendatory");
            edtEmail.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(phone)) {
            edtPhone.setError("phone is mendatory");
            edtPhone.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(whatsApp)) {
            edtWhatsApp.setError("WhatsApp is mendatory");
            edtWhatsApp.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(job)) {
            edtJob.setError("UserName is mendatory");
            edtJob.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(Descp)) {
            edtDescription.setError("Description is mendatory");
            edtDescription.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(budget)) {
            edtBudget.setError("Budget is mendatory");
            edtBudget.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(experience)) {
            edtExperience.setError("Experience is mendatory");
            edtExperience.requestFocus();
            return false;
        }  if (TextUtils.isEmpty(gender)) {
            edtGender.setError("Gender is mendatory");
            edtGender.requestFocus();
            return false;
        }
        return true;
    }
    private void AddDataInFireBaseDataBase() {

        String id = mDatabase.push().getKey();
        ClientProfileModel profileModel;
        String email = edtEmail.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String whatsApp = edtWhatsApp.getText().toString().trim();
        String job = edtJob.getText().toString().trim();
        String Descp = edtDescription.getText().toString().trim();
        String budget = edtBudget.getText().toString().trim();
        String experience = edtExperience.getText().toString().trim();
        String gender = edtGender.getText().toString().trim();
        String time = edtworkingTime.getText().toString().trim();
        String isOnline = "OFFLINE";
        String isBooked = "Free for work";
        profileModel = new ClientProfileModel(id,name,email,phone,whatsApp,job,Descp,budget,experience,gender,time,isOnline,isBooked);
        mDatabase.child(id).setValue(profileModel);
        Toast.makeText(this, "Added in DataBase", Toast.LENGTH_SHORT).show();
        saveIntoSharedPrefs(id,name,email,phone,whatsApp,job,Descp,budget,experience,gender,time,isOnline,isBooked);
//        Intent intent = new Intent(CreateClientProfile.this, MainClientActivity.class);
//        startActivity(intent);
//        finish();
    }

    private void saveIntoSharedPrefs(String id,String name, String email, String phone, String whatsApp, String job, String descp, String budget, String experience, String gender,String time,String isOnline, String isBooked) {
        editor = prefs.edit();
        editor.putString("id", id);
        editor.putString("names", name);
        editor.putString("emails", email);
        editor.putString("phones", phone);
        editor.putString("genders", gender);
        editor.putString("whatsApps", whatsApp);
        editor.putString("jobs", job);
        editor.putString("descps", descp);
        editor.putString("budgets", budget);
        editor.putString("experiences", experience);
        editor.putString("times", time);
        editor.putString("isonlines", isOnline);
        editor.putString("bookeds", isBooked);
        editor.apply();

        Intent intent = new Intent(CreateClientProfile.this, MainClientActivity.class);
        startActivity(intent);
        finish();
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void init() {
        edtName =  findViewById(R.id.edtNameClientName);
        edtEmail =  findViewById(R.id.edtNameClientEmail);
        edtPhone =  findViewById(R.id.edtNameClientPhone);
        edtWhatsApp =  findViewById(R.id.edtNameClientWhatsApp);
        edtJob =  findViewById(R.id.edtNameClientJob);
        edtDescription =  findViewById(R.id.edtNameClientDesc);
        edtBudget =  findViewById(R.id.edtNameClientBudget);
        edtExperience =  findViewById(R.id.edtNameClientExperience);
        edtGender =  findViewById(R.id.edtNameClientGender);
        edtworkingTime =  findViewById(R.id.edtworkingTime);
        btnDone =  findViewById(R.id.btnDone);
    }
}
