package com.khr.justquitit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.khr.justquitit.adapter.RecoveryHistoryAdapter;
import com.khr.justquitit.databinding.ActivityHealthProgressBinding;
import com.khr.justquitit.utils.SpacingItemDecoration;

import java.util.ArrayList;

public class HealthProgress extends AppCompatActivity {

    ActivityHealthProgressBinding binding;

    private RecyclerView recyclerView;
    private RecoveryHistoryAdapter recoveryAdapter;
    private ArrayList<RecoveryHistory> recoveryArray;
    private static final int VERTICAL_ITEM_SPACE = 48;
    ImageButton btnAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHealthProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startAnimationCounter(0,90);

        Toolbar toolbar = findViewById(R.id.health_toolbar);
        btnAssessment = findViewById(R.id.btn_assessment);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        btnAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthProgress.this, MainSurvey.class);
                startActivity(intent);
            }
        });

        InitializeCardView();
    }

    public void startAnimationCounter(int start_no, int end_no)
    {
        ValueAnimator animator = ValueAnimator.ofInt(start_no,end_no);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                binding.tvcounter.setText(animation.getAnimatedValue().toString()+"");
                binding.progressbar.setProgress(Integer.parseInt(animation.getAnimatedValue().toString()));
            }
        });
        animator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.recycleviewcard);
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
        RecoveryHistory health = new RecoveryHistory("Test 1", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);

        health = new RecoveryHistory("Test 2", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);
        health = new RecoveryHistory("Test 3", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);
        health = new RecoveryHistory("Test 4", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);
        health = new RecoveryHistory("Test 5", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);
        health = new RecoveryHistory("Test 6", "High on Nicotine Dependency. Recommended use patches, inhaler, lozenges and/or gums to replace cigs. ");
        recoveryArray.add(health);
    }
}