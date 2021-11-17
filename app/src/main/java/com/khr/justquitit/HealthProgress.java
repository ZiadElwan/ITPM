package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.khr.justquitit.adapter.RecoveryHistoryAdapter;
import com.khr.justquitit.adapter.SavingHistoryAdapter;
import com.khr.justquitit.utils.SpacingItemDecoration;

import java.util.ArrayList;

public class HealthProgress extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecoveryHistoryAdapter recoveryAdapter;
    private ArrayList<RecoveryHistory> recoveryArray;
    private static final int VERTICAL_ITEM_SPACE = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_progress);

        InitializeCardView();
    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.recycleviewCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recoveryArray = new ArrayList<>();

        recoveryAdapter = new RecoveryHistoryAdapter(this, recoveryArray);
        recyclerView.setAdapter(recoveryAdapter);

        //add ItemDecoration
        recyclerView.addItemDecoration(new SpacingItemDecoration(VERTICAL_ITEM_SPACE));

        //test 1 2 3
        CreateDataForCard();

    }

    private void CreateDataForCard() {
        RecoveryHistory health = new RecoveryHistory("Day 1", "Started my own journey to stop smoking");
        recoveryArray.add(health);

        health = new RecoveryHistory("Day 1", "Started my own journey to stop smoking");
        recoveryArray.add(health);
        health = new RecoveryHistory("Day 2", "Able to constraint myself to smoke");
        recoveryArray.add(health);
        health = new RecoveryHistory("Day 3", "Able to constraint myself to smoke");
        recoveryArray.add(health);
        health = new RecoveryHistory("Day 4", "Able to constraint myself to smoke");
        recoveryArray.add(health);
        health = new RecoveryHistory("Day 5", "Able to constraint myself to smoke");
        recoveryArray.add(health);
    }
}