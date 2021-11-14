package com.serviceprovider.serviceprovider.Login;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.serviceprovider.serviceprovider.Activities.ForgetPassword;
import com.serviceprovider.serviceprovider.Activities.MainCustomerActivity;
import com.serviceprovider.serviceprovider.R;
import com.serviceprovider.serviceprovider.SignUp.SignUpCustomer;

public class LoginCustomer extends AppCompatActivity {
    private Button btn_User_Login;
    EditText etEmail,etPassword;
    private FirebaseAuth auth;
    private ProgressDialog pDialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String prefName = "Service_Provider";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        pref = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginCustomer.this, MainCustomerActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login_customer);

        init();
        auth = FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        btn_User_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                if (validation()){
                    showpDialog();
                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginCustomer.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        hidepDialog();
                                        Toast.makeText(LoginCustomer.this, "Provide valid Credentials or signup to Continue", Toast.LENGTH_SHORT).show();

                                    } else {
                                        //fetchLoginInformation(email);
                                        hidepDialog();
                                        Intent intent = new Intent(LoginCustomer.this, MainCustomerActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });


    }
    public boolean validation(){

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.matches(emailPattern) && email.length() > 0) {
        } else {
            etEmail.setError("Invalid E-mail Pattern");
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)||password.length()<6) {
            etPassword.setError("Enter valid 6 Digit Password");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

//    public void fetchLoginInformation(String email) {
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Account");
//        mDatabase.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot readData : dataSnapshot.getChildren()) {
//                        RegisterUser data = readData.getValue(RegisterUser.class);
//                        String email, name, username, password;
//                        assert data != null;
//                        email = data.getEmail();
//                        name = data.getName();
//                        username = data.getUsername();
//                        password = data.getPassword();
//                        Log.e("values", name + email + password + username);
//                        saveIntoSharedPref(name, email, password, username);
//                    }
//                } else {
//                    //username doesn't exists.
//                    Toast.makeText(LoginCustomer.this, "username doesn't exists", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(LoginCustomer.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    //Problem is here we will found out later..
//    public void saveIntoSharedPref(String name, String email, String password, String username) {
//        editor = pref.edit();
//        editor.putString("username", name);
//        editor.putString("email", email);
//        editor.putString("password", password);
//        editor.putString("uniqueId", username);
//        editor.apply();
//        Toast.makeText(this, "Login Cancelled", Toast.LENGTH_SHORT).show();
////        Intent intent = new Intent(LoginCustomer.this, MainCustomerActivity.class);
////        startActivity(intent);
////        finish();
//    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void init() {
        etEmail = findViewById(R.id.edtLoginEmail);
        etPassword = findViewById(R.id.edtLoginPass);
        btn_User_Login = findViewById(R.id.btn_User_Login);
    }

    public void forget(View view) {
        Intent intent = new Intent(LoginCustomer.this, ForgetPassword.class);
        startActivity(intent);
    }

    public void tvRegUser(View view) {
        Intent intent= new Intent(LoginCustomer.this, SignUpCustomer.class);
        startActivity(intent);
    }
}
