package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.serviceprovider.serviceprovider.Login.LoginClient;
import com.serviceprovider.serviceprovider.R;

public class MainCustomerActivity extends AppCompatActivity {

    private String privacy_policy_Url = "https://drive.google.com/file/d/1v_bhQ5RcpQ6B49wk6citKuypciYMt_LN/view?usp=sharing";
    private String terms_and_condition_Url = "https://drive.google.com/file/d/1ap8rpDokCoAp0xveleX0oDx3sVJsZlaI/view?usp=sharing";

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);
        toolbar.setTitle("Customer Side");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        int id = item.getItemId();
        switch (id){
            case R.id.menuAboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuhelp:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuPP:
                Uri webUri1 = Uri.parse(privacy_policy_Url);
                Intent webIntent1= new Intent(Intent.ACTION_VIEW, webUri1);
                startActivity(webIntent1);
                break;
            case R.id.menuTC:
                Uri webUri = Uri.parse(terms_and_condition_Url);
                Intent webIntent= new Intent(Intent.ACTION_VIEW, webUri);
                startActivity(webIntent);
                break;
            case R.id.menulogOut:
                AlertDialog.Builder b=  new  AlertDialog.Builder(this)
                        .setTitle("Are you sure to logout")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        auth.signOut();
                                        Intent intent = new Intent(MainCustomerActivity.this, LoginClient.class);
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