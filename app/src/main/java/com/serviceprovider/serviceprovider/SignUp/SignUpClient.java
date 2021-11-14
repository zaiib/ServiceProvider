package com.serviceprovider.serviceprovider.SignUp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serviceprovider.serviceprovider.Activities.GetNiCImageFront;
import com.serviceprovider.serviceprovider.Activities.CountryData;
import com.serviceprovider.serviceprovider.Databases.RegisterClient;
import com.serviceprovider.serviceprovider.R;

import java.security.SecureRandom;

public class SignUpClient extends AppCompatActivity {

    EditText etName,etPassword,etPhone,etEmail;
    Button btnSignUp;
    private RadioGroup genderRadioGroup;
    private FirebaseAuth auth;
    private ProgressDialog pDialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String prefName = "Clients";
    // private RadioGroup genderRadioGroup;
    private RadioButton radioSexButton;
    String gender = "";
    //  Generate Unique User Id
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();
    private Spinner spinner;
    String randomString = "";
    String uniqueUsername = "";
    DatabaseReference mDatabase;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_client);

        init();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Client_Account");
        //Shared Preference Instance
        pref = getSharedPreferences(prefName, Context.MODE_PRIVATE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    showpDialog();
                    // get selected radio button from radioGroup
                    int selectedId = genderRadioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    gender = radioSexButton.getText().toString().trim();
//                    Toast.makeText(SignUp.this,
//                            radioSexButton.getText(), Toast.LENGTH_SHORT).show();
                    uniqueUsername=generateUniqueUsername();
                    //storeUserIntoDb();
                    validateUsernameInFirebase();

                }
            }
        });
    }
    public boolean validation(){

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern) && email.length() > 0) {
        } else {
            etEmail.setError("Invalid E-mail Pattern");
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            etPassword.setError("Password Must be 6 Digit");
            etPassword.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            etName.setError("UserName is mendatory");
            etName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            etPassword.setError("Phone Number is mendatory");
            etPassword.requestFocus();
            return false;
        }
        if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please Select Gender", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public void storeUserIntoDb(){

        RegisterClient user;
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        user = new RegisterClient(uniqueUsername, email, password, gender,name,phone);
        //String id = mDatabase.push().getKey();
        mDatabase.child(uniqueUsername).setValue(user);
        //mDatabase.push().setValue(user);
        Log.e("values", name + email + password + gender+uniqueUsername+phone);
        saveIntoSharedPref(name, email, password, gender,uniqueUsername,phone);

    }

    private void saveIntoSharedPref(String name, String email, String password, String gender, String uniqueUsername, String phone) {
        editor = pref.edit();
        editor.putString("username", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("gender", gender);
        editor.putString("phone", phone);
        editor.putString("uniqueId", uniqueUsername);
        editor.apply();
    }
    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

            sb.append(rndChar);

        }

        return sb.toString();

    }
    public String generateUniqueUsername() {
        /*System.out.println("result : " + generateRandomString(4));
        System.out.println("\n");*/
        String getEmail = etEmail.getText().toString().trim();
        String parseEmail = getEmail.substring(0, 4);
        randomString = generateRandomString(4);
        String concate = parseEmail +"_"+ randomString;
        return concate;
    }
    public void validateUsernameInFirebase() {

        mDatabase.orderByChild("username").equalTo(uniqueUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //userName exists in Database
                    //Toast.makeText(getApplicationContext(),"USername Exists",Toast.LENGTH_LONG).show();

                    uniqueUsername=generateUniqueUsername();
                    validateUsernameInFirebase();
                    //        etName,etPassword,etPhone,etEmail
                } else {
                    //username doesn't exists.
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    registerUserEmail(email,password);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void registerUserEmail(String email, String password) {

        String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

        String number = etPhone.getText().toString().trim();
        if (number.isEmpty() || number.length() < 10) {
            etPhone.setError("Valid number is required");
            etPhone.requestFocus();
            return;
        }
        final String phonenumber = "+" + code + number;
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpClient.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    hidepDialog();
                    Toast.makeText(SignUpClient.this, "Account Already Exists with this Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    hidepDialog();
                    Intent intent = new Intent(SignUpClient.this, GetNiCImageFront.class);
                    // intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);
                    storeUserIntoDb();
                    finish();
                }
            }
        });
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
        etName = findViewById(R.id.edtNameUser);
        etEmail = findViewById(R.id.edtEmailUser);
        etPassword = findViewById(R.id.edtPassword);
        etPhone = findViewById(R.id.edtPhoneNumber);
        btnSignUp = findViewById(R.id.btn_SignUp);
        genderRadioGroup = findViewById(R.id.radioGender);
        spinner = findViewById(R.id.spinnerCountries);

    }

}
