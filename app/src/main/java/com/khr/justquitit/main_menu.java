package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main_menu extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;
    ImageButton btntips, btnsavings, btnhealth, btnprod;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btntips = findViewById(R.id.btn_tips);
        btnsavings = findViewById(R.id.btn_savings);
        btnhealth = findViewById(R.id.btn_healthprogress);
        btnprod = findViewById(R.id.btn_products);
        setUpToolbar();

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_aboutus:

                        Intent intent = new Intent(main_menu.this, about_page.class);
                        startActivity(intent);
                        break;

                    case  R.id.nav_notification2:

                        Intent intent1 = new Intent(main_menu.this, Notification.class);
                        startActivity(intent1);

                        break;

                    case  R.id.nav_tips:

                        Intent intent2 = new Intent(main_menu.this, QuitSmokingTipsFirebase.class);
                        startActivity(intent2);

                        break;

                    case R.id.nav_healthprogress:
                        Intent intent3 = new Intent(main_menu.this, HealthProgress.class);
                        startActivity(intent3);

                        break;

                    case R.id.nav_savings:
                        Intent intent4 = new Intent(main_menu.this, Savings.class);
                        startActivity(intent4);

                        break;

                    case R.id.nav_products:
                        Intent intent5 = new Intent(main_menu.this, ShowProducts.class);
                        startActivity(intent5);

                        break;

                    case R.id.nav_logout2:

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(main_menu.this, login_page.class));

                        break;
                }
                return false;
            }
        });

        btntips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_menu.this, QuitSmokingTipsFirebase.class);
                startActivity(intent);
            }
        });

        btnsavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_menu.this, Savings.class);
                startActivity(intent);
            }
        });

        btnhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_menu.this, HealthProgress.class);
                startActivity(intent);
            }
        });

        btnprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_menu.this, ShowProducts.class);
                startActivity(intent);
            }
        });


    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    public void onBackPressed() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure want to exit");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);

            }
        });
        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDlg.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile){
            Intent intent5 = new Intent(main_menu.this, smoker_profile.class);
            startActivity(intent5);
            return true;
        }

        else if(id == R.id.nav_changepwrd){
            Intent intent2 = new Intent(main_menu.this, Change_password.class);
            startActivity(intent2);
            return true;
        }

        switch (item.getItemId()){

            case R.id.nav_notification:
                Intent intent = new Intent(main_menu.this, Notification.class);
                startActivity(intent);
                break;

            case R.id.nav_profile:
                Intent intent5 = new Intent(main_menu.this, smoker_profile.class);
                startActivity(intent5);
                break;

            case R.id.nav_feedback:
                Intent intent1 = new Intent(main_menu.this, user_feedback_page.class);
                startActivity(intent1);
                break;

            case R.id.nav_changepwrd:
                Intent intent2 = new Intent(main_menu.this, Change_password.class);
                startActivity(intent2);
                break;

            /*case R.id.nav_setting:
                Intent intent3 = new Intent(main_menu.this, Setting.class);
                startActivity(intent3);
                break;*/

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(main_menu.this, login_page.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
        fAuth=FirebaseAuth.getInstance();
        fUser=fAuth.getCurrentUser();

        if(fUser==null){
            //go to login page
            Intent intent = new Intent(main_menu.this,login_page.class);
            startActivity(intent);
        }

    }
}