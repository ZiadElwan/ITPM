package com.khr.justquitit;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import com.khr.justquitit.adapter.TipsAdapterFirebase;

import java.util.ArrayList;

public class QuitSmokingTipsFirebase extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseReference reference;
    //private StorageReference ref;
    private ArrayList<TipsFirebase>tipsList;

    private Context mContext;

    LinearLayoutManager linearLayoutManager;

    private TipsAdapterFirebase tipsAdapterFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit_smoking_tips);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportFragmentManager().beginTransaction().replace(R.id. ,new refragment()).commit();

        Toolbar toolbar = findViewById(R.id.tips_toolbar2);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        
        reference = FirebaseDatabase.getInstance().getReference();
        //ref = FirebaseStorage.getInstance().getReference();
        
        tipsList = new ArrayList<>();

        ClearAll();
        
        GetDataFromFirebase();

    }

    private void GetDataFromFirebase() {
        Query query = reference.child("TipsQuitSmoking");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TipsFirebase tipsFirebase = new TipsFirebase();

                    tipsFirebase.setImage( dataSnapshot.child("Image").getValue().toString());
                    tipsFirebase.setTitle( dataSnapshot.child("Title").getValue().toString());
                    tipsFirebase.setDescription(dataSnapshot.child("Description").getValue().toString());
                    tipsList.add(tipsFirebase);
                }
                tipsAdapterFirebase = new TipsAdapterFirebase(getApplicationContext(), tipsList);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(tipsAdapterFirebase);
                tipsAdapterFirebase.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if (tipsList != null) {
            tipsList.clear();

            if(tipsAdapterFirebase != null){
                tipsAdapterFirebase.notifyDataSetChanged();
            }
        }
        tipsList = new ArrayList<>();
    }

//    private List<Tips> getAllTips() {
//        List<Tips> tips = new ArrayList<Tips>();
//        tips.add(new Tips("Think Positive", R.drawable.tips1, "You might have tried to quit smoking before and not managed it, but don't let that put you off. \n" +
//                "\n" +
//                "Look back at the things your experience has taught you and think about how you're really going to do it this time."));
//        tips.add(new Tips("Make A Plan", R.drawable.tips2, "Make a promise, set a date and stick to it. Sticking to the \"not a drag\" rule can really help.\n" +
//                "\n" +
//                "Whenever you find yourself in difficulty, say to yourself, \"I won't even have a single drag\", and stick with this until the cravings pass.\n" +
//                "\n" +
//                "Think ahead to times where it might be difficult (a party, for instance), and plan your actions and escape routes in advance."));
//        tips.add(new Tips("Consider Your Diet", R.drawable.tips3, "Is your after-dinner cigarette your favourite? A US study revealed that some foods, including meat, make cigarettes more satisfying.\n" +
//                "\n" +
//                "Others, including cheese, fruit and vegetables, make cigarettes taste terrible. So swap your usual steak or burger for a veggie pizza instead.  \n" +
//                "\n" +
//                "You may also want to change your routine at or after mealtimes. Getting up and doing the dishes straight away or settling down in a room where you don't smoke may help."));
//        tips.add(new Tips("Change Your Drink", R.drawable.tips4, "The same US study as above also looked at drinks. Fizzy drinks, alcohol, cola, tea and coffee all make cigarettes taste better.\n" +
//                "\n" +
//                "So when you're out, drink more water and juice. Some people find simply changing their drink (for example, switching from wine to a vodka and tomato juice) affects their need to reach for a cigarette."));
//        tips.add(new Tips("Get Moving", R.drawable.tips5, "A review of scientific studies has proved exercise, even a 5-minute walk or stretch, cuts cravings and may help your brain produce anti-craving chemicals."));
////        tips.add(new Tips("tips6", R.drawable.tips6));
////        tips.add(new Tips("tips7", R.drawable.tips7));
////        tips.add(new Tips("tips8", R.drawable.tips8));
//
//        return tips;
//    }
}
