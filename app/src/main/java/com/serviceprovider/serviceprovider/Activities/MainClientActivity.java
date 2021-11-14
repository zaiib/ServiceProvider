package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.serviceprovider.serviceprovider.FragmentClient.ClientProfileFragment;
import com.serviceprovider.serviceprovider.FragmentClient.HistoryClientFragment;
import com.serviceprovider.serviceprovider.FragmentClient.HomeClientFragment;
import com.serviceprovider.serviceprovider.Login.LoginClient;
import com.serviceprovider.serviceprovider.R;

public class MainClientActivity extends AppCompatActivity {

    private String privacy_policy_Url = "https://drive.google.com/file/d/1v_bhQ5RcpQ6B49wk6citKuypciYMt_LN/view?usp=sharing";
    private String terms_and_condition_Url = "https://drive.google.com/file/d/1ap8rpDokCoAp0xveleX0oDx3sVJsZlaI/view?usp=sharing";

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomView =  findViewById(R.id.bottomNavClient);
        bottomView.setOnNavigationItemSelectedListener(navListner);
        auth = FirebaseAuth.getInstance();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeClientFragment();
                    break;
                case R.id.activeOrder:
                    selectedFragment = new ClientProfileFragment();
                    break;
                case R.id.menuhistory:
                    selectedFragment = new HistoryClientFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selectedFragment).commit();
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.menuAboutUsClient:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuhelpClient:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuPPClient:
                Uri webUri1 = Uri.parse(privacy_policy_Url);
                Intent webIntent1= new Intent(Intent.ACTION_VIEW, webUri1);
                startActivity(webIntent1);
                break;
            case R.id.menuTCClient:
                Uri webUri = Uri.parse(terms_and_condition_Url);
                Intent webIntent= new Intent(Intent.ACTION_VIEW, webUri);
                startActivity(webIntent);
                break;
            case R.id.profileClient:
                Intent intent = new Intent(MainClientActivity.this, UpdateClientProfile.class);
                startActivity(intent);
                break;
            case R.id.menulogOutClient:
                AlertDialog.Builder b=  new  AlertDialog.Builder(this)
                        .setTitle("Are you sure to logout")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        auth.signOut();
                                        Intent intent = new Intent(MainClientActivity.this, LoginClient.class);
                                        startActivity(intent);
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        );
                AlertDialog alert = b.create();
                alert.show();
                break;
        }
        return true;
    }
}
