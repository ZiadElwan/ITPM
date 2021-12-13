package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khr.justquitit.databinding.TipsDetailBinding;
//import com.khr.justquitit.databinding.ActivityTipsDetailBinding;
//import com.khr.justquitit.databinding.TipsDetailBinding;

import java.util.HashMap;
import java.util.Objects;

public class TipsDetailActivity extends AppCompatActivity {
    boolean isInMyFavourite = false;
    String tipsId = "";
    private FirebaseAuth fAuth;
    private Context context;
    private TipsDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TipsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null){
            checkIsFavourite();
        }



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
        FloatingActionButton floatingActionButton = findViewById(R.id.btn_favourite);
//
//

        tvTitle.setText(intent.getStringExtra("tipsName"));
        tvDescription.setText(intent.getStringExtra("tipsDetail"));
//        img.setImageResource(getIntent().getIntExtra("image" ,0));
        String image = intent.getStringExtra("image");
        Glide.with(TipsDetailActivity.this).load(image).into(img);
        tipsId = getIntent().getStringExtra("tipsId");




////
       binding.btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fAuth.getCurrentUser() == null){
                    Toast.makeText(TipsDetailActivity.this,"You're not logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(isInMyFavourite){
                        MyApplication.removeFromFavourite(TipsDetailActivity.this, tipsId);
                    }
                    else{
                        MyApplication.addToFavourite(TipsDetailActivity.this, tipsId);
                    }
                }
            }
       });
    }



    private void checkIsFavourite() {
//        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UserData");

        reference.child(fAuth.getUid()).child("FavouriteTips").child(tipsId)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        isInMyFavourite = dataSnapshot.exists();
                        if (isInMyFavourite) {
                            binding.btnFavourite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_favorite_24));

                        }
                        else{
                            binding.btnFavourite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_favorite_border_24));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }




}

