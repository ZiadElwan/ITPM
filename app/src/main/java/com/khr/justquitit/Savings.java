package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.khr.justquitit.adapter.SavingHistoryAdapter;
import com.khr.justquitit.utils.SpacingItemDecoration;

import java.util.ArrayList;

public class Savings extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton imgBtn;
    private RecyclerView recyclerView;
    private SavingHistoryAdapter savingAdapter;
    private ArrayList<SavingHistory> savingArray;
    private static final int VERTICAL_ITEM_SPACE = 48;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        setUpToolbar();

        navigationView = (NavigationView) findViewById(R.id.navigation_menu2);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_aboutus:

                        Intent intent = new Intent(Savings.this, about_page.class);
                        startActivity(intent);
                        break;

                    case  R.id.nav_notification2:

                        Intent intent1 = new Intent(Savings.this, Notification.class);
                        startActivity(intent1);

                        break;

                    case  R.id.nav_tips:

                        Intent intent2 = new Intent(Savings.this, QuitSmokingTips.class);
                        startActivity(intent2);

                        break;

                    case R.id.nav_healthprogress:
                        Intent intent3 = new Intent(Savings.this, HealthProgress.class);
                        startActivity(intent3);

                        break;

                    case R.id.nav_savings:
                        Intent intent4 = new Intent(Savings.this, Savings.class);
                        startActivity(intent4);

                        break;

                    case R.id.nav_products:
                        Intent intent5 = new Intent(Savings.this, ShowProducts.class);
                        startActivity(intent5);

                        break;

                    case R.id.nav_logout2:

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Savings.this, login_page.class));

                        break;
                }
                return false;
            }
        });


        imgBtn = findViewById(R.id.imgBtn_saving);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addSaving = new EditText(view.getContext());
                AlertDialog.Builder addSavingDialog = new AlertDialog.Builder(view.getContext());
                addSavingDialog.setTitle("Add Saving");
                addSavingDialog.setMessage("Enter amount(RM) you want to save");
                addSavingDialog.setView(addSaving);
                addSavingDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                addSavingDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String addedSaving = addSaving.getText().toString();

                        //add the action later
                    }
                });

                addSavingDialog.create().show();

            }
        });

        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.recycleviewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        savingArray = new ArrayList<>();
        
        savingAdapter = new SavingHistoryAdapter(this, savingArray);
        recyclerView.setAdapter(savingAdapter);

        //add ItemDecoration
        recyclerView.addItemDecoration(new SpacingItemDecoration(VERTICAL_ITEM_SPACE));

        //test 1 2 3
        CreateDataForCard();
    }
    //Test data. Nnti tukar kepada Firebase Realtime guna database trigger
    private void CreateDataForCard() {
        SavingHistory saving = new SavingHistory("15/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);

        saving = new SavingHistory("16/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("17/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("18/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("19/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("20/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("21/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("22/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("23/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
        saving = new SavingHistory("24/11/2021","Successfully Avoid Smoking","RM 15.00");
        savingArray.add(saving);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawer_Layout);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    /*public void onBackPressed() {
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
    }*/

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
            Intent intent5 = new Intent(Savings.this, smoker_profile.class);
            startActivity(intent5);
            return true;
        }

        else if(id == R.id.nav_changepwrd){
            Intent intent2 = new Intent(Savings.this, Change_password.class);
            startActivity(intent2);
            return true;
        }

        switch (item.getItemId()){

            case R.id.nav_notification:
                Intent intent = new Intent(Savings.this, Notification.class);
                startActivity(intent);
                break;

            case R.id.nav_profile:
                Intent intent5 = new Intent(Savings.this, smoker_profile.class);
                startActivity(intent5);
                break;

            case R.id.nav_feedback:
                Intent intent1 = new Intent(Savings.this, user_feedback_page.class);
                startActivity(intent1);
                break;

            case R.id.nav_changepwrd:
                Intent intent2 = new Intent(Savings.this, Change_password.class);
                startActivity(intent2);
                break;

            /*case R.id.nav_setting:
                Intent intent3 = new Intent(Savings.this, Setting.class);
                startActivity(intent3);
                break;*/

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Savings.this, login_page.class));
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
            Intent intent = new Intent(Savings.this,login_page.class);
            startActivity(intent);
        }

    }


}