package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.khr.justquitit.adapter.SavingHistoryAdapter;
import com.khr.justquitit.utils.SpacingItemDecoration;

import java.util.ArrayList;

public class Savings extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SavingHistoryAdapter savingAdapter;
    private ArrayList<SavingHistory> savingArray;
    private static final int VERTICAL_ITEM_SPACE = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

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


}