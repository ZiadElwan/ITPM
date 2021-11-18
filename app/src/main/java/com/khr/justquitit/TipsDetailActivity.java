package com.khr.justquitit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TipsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_detail);

        Toolbar toolbar = findViewById(R.id.tips_toolbar);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        TextView tvTitle = findViewById(R.id.imageTitle);
        ImageView img = findViewById(R.id.imageDetail);
        TextView tvDescription = findViewById(R.id.imageDesc);


        tvTitle.setText(intent.getStringExtra("tipsName"));
        tvDescription.setText(intent.getStringExtra("tipsDetail"));
        img.setImageResource(getIntent().getIntExtra("image" ,0));
    }

    public static void addToFavourite(String tipsId){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("tipsId", "" + tipsId);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FavouriteTips");

    }
}