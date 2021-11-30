package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
    private static final int VERTICAL_ITEM_SPACE = 28;

    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

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