package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khr.justquitit.adapter.AdapterFavTips;
import com.khr.justquitit.databinding.ActivityFavTipsBinding;

import java.util.ArrayList;

public class fav_tips extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private ActivityFavTipsBinding binding;
    private ArrayList<TipsFirebase> favtipsArrayList;
    private AdapterFavTips adapterFavTips;
    private FirebaseAuth fAuth;
    String tipsId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_tips);
//        binding = ActivityFavTipsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.favtips_toolbar5);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        fAuth = FirebaseAuth.getInstance();
        loadFavTips();

    }

    private void loadFavTips(){
        favtipsArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserData");
        ref.child(fAuth.getUid()).child("FavouriteTips")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        favtipsArrayList.clear();
                        for(DataSnapshot ds: snapshot.getChildren()){
                            String tipsId = "" + ds.child("tipsId").getValue();

                            TipsFirebase tipsFirebase = new TipsFirebase();
                            tipsFirebase.setTipsId(tipsId);

                            favtipsArrayList.add(tipsFirebase);
                        }

                        adapterFavTips = new AdapterFavTips(fav_tips.this, favtipsArrayList);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapterFavTips);
                        adapterFavTips.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}