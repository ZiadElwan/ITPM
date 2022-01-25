package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.khr.justquitit.adapter.ProductRecyclerViewAdapter;
import com.khr.justquitit.adapter.TrophyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowTrophy extends AppCompatActivity{

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<ListTrophy> listTrophies;
    GridLayoutManager gridLayoutManager;
    TrophyRecyclerViewAdapter trophyRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_badges);

        Toolbar toolbar = findViewById(R.id.trophy_toolbar2);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview_trophy);
        gridLayoutManager = new GridLayoutManager(this,2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        listTrophies = new ArrayList<>();
        //Clear arraylist
        ClearAll();

        //Get data method
        getAllTrophiesInfor();

    }
    //Get data from firebase
    private void getAllTrophiesInfor(){
        Query query = databaseReference.child("Badge");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ListTrophy trophyList = new ListTrophy();

                    trophyList.setTrophyName(snapshot.child("name").getValue().toString());
                    trophyList.setImageUrl(snapshot.child("image").getValue().toString());

                    listTrophies.add(trophyList);
                }

                TrophyRecyclerViewAdapter trophyRecyclerViewAdapter = new TrophyRecyclerViewAdapter(getApplicationContext(), listTrophies);
                recyclerView.setLayoutManager(new GridLayoutManager(ShowTrophy.this, 2));
                recyclerView.setAdapter(trophyRecyclerViewAdapter);
                trophyRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void ClearAll(){
        if (listTrophies != null){
            listTrophies.clear();

            if (trophyRecyclerViewAdapter != null){
                trophyRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
        listTrophies = new ArrayList<>();
    }

}
