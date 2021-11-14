package com.serviceprovider.serviceprovider.Login;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.serviceprovider.serviceprovider.Activities.ForgetPassword;
import com.serviceprovider.serviceprovider.Activities.MainClientActivity;
import com.serviceprovider.serviceprovider.R;
import com.serviceprovider.serviceprovider.SignUp.SignUpClient;

public class LoginClient extends AppCompatActivity {


    EditText userEmail,userPassword;
    TextView tvForget;
    Button btnLogin;
    private FirebaseAuth auth;
    private ProgressDialog pDialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String prefName = "Clients";
    // Button btnLogin,tvForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        pref = getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginClient.this, MainClientActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login_client);

        userEmail = findViewById(R.id.edtLoginEmailClient);
        userPassword = findViewById(R.id.edtLoginPassClient);
        tvForget = findViewById(R.id.tvForgetPassClient);
        btnLogin = findViewById(R.id.btn_User_LoginClient);
        auth = FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginClient.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                if (validation()){
                    showpDialog();
                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginClient.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        hidepDialog();
                                        Toast.makeText(LoginClient.this, "Provide valid Credentials or signup to Continue", Toast.LENGTH_SHORT).show();

                                    } else {
                                        // fetchLoginInformation(email);
                                        Intent intent = new Intent(LoginClient.this, MainClientActivity.class);
                                        startActivity(intent);
                                        finish();
                                        hidepDialog();
                                        /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();*/
                                    }
                                }
                            });
                }

            }
        });
    }

//    public void fetchLoginInformation(String email){
//        DatabaseReference mDatabase;
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Client_Account");
//        mDatabase.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for(DataSnapshot readData: dataSnapshot.getChildren()){
//                        RegisterClient data = readData.getValue(RegisterClient.class);
//                        String email,name,username,password,gender,phone;
//                        email=data.getEmail();
//                        name=data.getName();
//                        username=data.getUsername();
//                        password=data.getPassword();
//                        gender=data.getGender();
//                        phone=data.getPhoneNumber();
//                        Log.e("values", name + email + password + gender+username+phone);
//                     //   saveIntoSharedPref(name,email,password,gender,username,phone);
////                        Intent intent = new Intent(LoginClient.this, MainClientActivity.class);
////                        startActivity(intent);
////                        finish();
//                    }
//                } else {
//                    //username doesn't exists.
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

//    private void saveIntoSharedPref(String name, String email, String password, String gender, String username, String phone) {
//        editor = pref.edit();
//        editor.putString("username", name);
//        editor.putString("email", email);
//        editor.putString("password", password);
//        editor.putString("gender", gender);
//        editor.putString("phone", phone);
//        editor.putString("uniqueId", username);
//        editor.apply();
//
//    }

    public boolean validation(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if (email.matches(emailPattern) && email.length() > 0) {
        } else {
            userEmail.setError("Invalid E-mail Pattern");
            userEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)||password.length()<6) {
            userPassword.setError("Enter valid 6 Digit Password");
            userPassword.requestFocus();
            return false;
        }

        return true;
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    public void tvRegClient(View view) {
        Intent intent= new Intent(LoginClient.this, SignUpClient.class);
        startActivity(intent);
    }
}
