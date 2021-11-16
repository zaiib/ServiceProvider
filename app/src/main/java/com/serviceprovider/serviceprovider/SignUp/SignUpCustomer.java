package com.serviceprovider.serviceprovider.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.serviceprovider.serviceprovider.Activities.MainCustomerActivity;
import com.serviceprovider.serviceprovider.Databases.RegisterUser;
import com.serviceprovider.serviceprovider.R;

import java.security.SecureRandom;

public class SignUpCustomer extends AppCompatActivity {


    //For View Widgets
    EditText edtName,edtEmail,edtPassword;
    Button buSignUp;

    //For Development Components
    private FirebaseAuth auth;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String prefName = "Service_Provider";
    //  Generate Unique User Id
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    private ProgressDialog pDialog;
    String randomString = "";
    String uniqueUsername = "";
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);

        init();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Account");

        pref = getSharedPreferences(prefName, Context.MODE_PRIVATE);

        buSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    showpDialog();
                    // get selected radio button from radioGroup
//                    Toast.makeText(SignUp.this,
//                            radioSexButton.getText(), Toast.LENGTH_SHORT).show();
                    uniqueUsername=generateUniqueUsername();
                    //storeUserIntoDb();
                    validateUsernameInFirebase();

                }
            }
        });
    }
    private boolean validation() {
        //edtName,edtEmail,edtPassword
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String name = edtName.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern) && email.length() > 0) {
        } else {
            edtEmail.setError("Invalid E-mail Pattern");
            edtEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            edtPassword.setError("Password Must be 6 Digit");
            edtPassword.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            edtName.setError("UserName is mendatory");
            edtName.requestFocus();
            return false;
        }
        return true;
    }

    public void storeUserIntoDb() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Account");
        RegisterUser user;
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        user = new RegisterUser(uniqueUsername, email, password,name);
        //String id = mDatabase.push().getKey();
        mDatabase.child(uniqueUsername).setValue(user);
        //mDatabase.push().setValue(user);
        Log.e("values", name + email + password+uniqueUsername);
        saveIntoSharedPref(name, email, password,uniqueUsername);
    }
    public void saveIntoSharedPref(String username, String email, String password,String unique) {
        editor = pref.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("uniqueId", unique);
        editor.apply();
    }
    public String generateUniqueUsername() {
        /*System.out.println("result : " + generateRandomString(4));
        System.out.println("\n");*/
        String getEmail = edtEmail.getText().toString().trim();
        String parseEmail = getEmail.substring(0, 4);
        randomString = generateRandomString(4);
        return parseEmail +"_"+ randomString;
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
    public void validateUsernameInFirebase() {

        mDatabase.orderByChild("username").equalTo(uniqueUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //userName exists in Database
                    //Toast.makeText(getApplicationContext(),"USername Exists",Toast.LENGTH_LONG).show();

                    uniqueUsername=generateUniqueUsername();
                    validateUsernameInFirebase();
//edtName,edtEmail,edtPassword
                } else {
                    //username doesn't exists.
                    String email = edtEmail.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    registerUserEmail(email,password);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void registerUserEmail(String email,String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpCustomer.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    hidepDialog();
                    Toast.makeText(SignUpCustomer.this, "Account Already Exists with this Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    hidepDialog();
                    Intent intent = new Intent(SignUpCustomer.this, MainCustomerActivity.class);
                    startActivity(intent);
                    storeUserIntoDb();
                    finish();
                }
            }
        });
    }
    private void init() {
        edtEmail =  findViewById(R.id.edtSignUpEmail);
        edtName =  findViewById(R.id.edtName);
        edtPassword =  findViewById(R.id.edtSignUpPass);
        buSignUp =  findViewById(R.id.btn_User_SignUp);
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
