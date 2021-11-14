package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.serviceprovider.serviceprovider.R;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GetOtp extends AppCompatActivity {

    private String verificationid;
    private FirebaseAuth mAuth;
    private TextView timer;
    private Button btnSignUp;
    private EditText editTextCode;
    private CountDownTimer countDownTimer;
    private long timerLeftInMiliSecond = 60000;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);
        mAuth = FirebaseAuth.getInstance();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Verifying Your Number... ");
        btnSignUp = findViewById(R.id.buttonOTP);
        editTextCode = findViewById(R.id.editTextCode);
        timer = findViewById(R.id.timer);

        //Update Time
        countDownTimer = new CountDownTimer(timerLeftInMiliSecond, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerLeftInMiliSecond = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        String phonenumber = getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();

                if ((code.isEmpty() || code.length() < 6)){

                    editTextCode.setError("Enter code...");
                    editTextCode.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });
    }
    private void updateTimer() {
        int minutes = (int) timerLeftInMiliSecond/60000;
        int second = (int) timerLeftInMiliSecond % 60000/1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (second < 10) timeLeftText += "0";
        timeLeftText += second;
        timer.setText(timeLeftText);

    }
    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(GetOtp.this, GetNiCImageFront.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(GetOtp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
    private void sendVerificationCode(String phonenumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD,
                mCallBack

        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
                mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(GetOtp.this, e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };
}