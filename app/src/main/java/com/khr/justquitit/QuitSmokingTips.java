package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.khr.justquitit.adapter.TipsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuitSmokingTips extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit_smoking_tips);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(QuitSmokingTips.this);
        recyclerView.setLayoutManager(linearLayoutManager);


        List<Tips> tips = getAllTips();
        TipsRecyclerViewAdapter tipsRecyclerViewAdapter = new TipsRecyclerViewAdapter(QuitSmokingTips.this, tips);
        recyclerView.setAdapter(tipsRecyclerViewAdapter);
    }

    private List <Tips> getAllTips(){
        List <Tips> tips = new ArrayList<Tips>();
        tips.add(new Tips("Think Positive", R.drawable.tips1));
        tips.add(new Tips("Make A Plan", R.drawable.tips2));
        tips.add(new Tips("Consider Your Diet", R.drawable.tips3));
        tips.add(new Tips("Change Your Drink", R.drawable.tips4));
        tips.add(new Tips("Get Moving", R.drawable.tips5));
//        tips.add(new Tips("tips6", R.drawable.tips6));
//        tips.add(new Tips("tips7", R.drawable.tips7));
//        tips.add(new Tips("tips8", R.drawable.tips8));

        return tips;
    }
}