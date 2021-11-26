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
import java.util.Objects;

public class TipsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
//        ActionBar myActionbar = getSupportActionBar();
//        myActionbar.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        TextView tvTitle = findViewById(R.id.img_title);
        ImageView img = findViewById(R.id.img_Detail);
        TextView tvDescription = findViewById(R.id.img_Desc);
//
//
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