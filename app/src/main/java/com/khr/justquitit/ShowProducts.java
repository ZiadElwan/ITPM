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

import java.util.ArrayList;
import java.util.List;

public class ShowProducts extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<ProductList> productLists;
    GridLayoutManager gridLayoutManager;
    ProductRecyclerViewAdapter productRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Toolbar toolbar = findViewById(R.id.prod_toolbar);
        setSupportActionBar(toolbar);
        ActionBar myActionbar = getSupportActionBar();
        myActionbar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(this,2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        productLists = new ArrayList<>();
        //Clear arraylist
        ClearAll();

        //Get data method
        getAllProductInfor();

    }

    //Get data from firebase
    private void getAllProductInfor(){
        Query query = databaseReference.child("Products");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductList productList = new ProductList();

                    productList.setProductname(snapshot.child("name").getValue().toString());
                    productList.setImageurl(snapshot.child("image").getValue().toString());
                    productList.setProductdescription(snapshot.child("description").getValue().toString());
                    productList.setLinkUrl(snapshot.child("link").getValue().toString());

                    productLists.add(productList);
                }

                ProductRecyclerViewAdapter productRecyclerViewAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), productLists);
                recyclerView.setLayoutManager(new GridLayoutManager(ShowProducts.this, 2));
                recyclerView.setAdapter(productRecyclerViewAdapter);
                productRecyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void ClearAll(){
        if (productLists != null){
            productLists.clear();

            if (productRecyclerViewAdapter != null){
                productRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
        productLists = new ArrayList<>();
    }

    //Juz testing after that will retrieve data from firebase
    /*private List<ProductList> getAllProductInfor(){
        List<ProductList> allProduct = new ArrayList<ProductList>();
        allProduct.add(new ProductList("Habitrol Patch S1", R.drawable.habitrol, "Habitrol Nicotine Patches come in three decreasing nicotine levels. During our 8-week step-down program, you’ll work your way through these steps, gradually reducing your nicotine dependency and improving your chances of quitting. \n" +
                "\n" +
                "Can help reduce nicotine withdrawal symptoms such as irritability, anxiety, depression, sleeplessness, and increased appetite and nicotine cravings."));
        allProduct.add(new ProductList("Habitrol Patch S2", R.drawable.habitrol2, "Can help reduce nicotine withdrawal symptoms such as irritability, anxiety, depression, sleeplessness, and increased appetite and nicotine cravings. \n" +
                "\n" +
                "Habitrol Nicotine Patches come in three decreasing nicotine levels. During our 8-week step-down program, you’ll work your way through these steps, gradually reducing your nicotine dependency and improving your chances of quitting."));
        allProduct.add(new ProductList("Habitrol Patch S3", R.drawable.habitrol3, "Can help reduce nicotine withdrawal symptoms such as irritability, anxiety, depression, sleeplessness, and increased appetite and nicotine cravings. \n" +
                "\n" +
                "Habitrol Nicotine Patches come in three decreasing nicotine levels. During our 8-week step-down program, you’ll work your way through these steps, gradually reducing your nicotine dependency and improving your chances of quitting."));
        allProduct.add(new ProductList("Aromatherapy Inhaler", R.drawable.inhaler, "Urban ReLeaf CRAVING CRUSHER Aromatherapy Inhaler ! \n" +
                "\n" +
                "100% NATURAL Remedy. Fresh Essential Oils help you manage quitting! This holistic remedy will help curb your cravings, but you MUST WANT to stop smoking! It will help, and if you hold it like a cigarette, the essential oils help satisfy the urge."));
        allProduct.add(new ProductList("Nicoderm CQ S1", R.drawable.nicoderm, "One package of 14 - 21mg NicoDerm CQ Step 1 Nicotine Patches to Help Quit Smoking with Behavioral Support Program - Stop Smoking Aid \n" +
                "\n" +
                "Nicotine patch helps prevent the urge to smoke and relieves the frustration, irritability and restlessness associated with smoking cessation" +
                "\n" +
                "Includes Behavioral Support Program to increase your chances of success in quitting"));
        allProduct.add(new ProductList("Nicoderm CQ S2", R.drawable.nicoderm2, "One package of 14 Step 2 NicoDerm CQ Nicotine Patches to Stop Smoking \n" +
                "\n" +
                "Designed to calm the cravings and other symptoms that come with quitting smoking \n" + "Start with these Step 2 quit smoking patches if you smoke fewer than 10 cigarettes a day"));
        allProduct.add(new ProductList("Nicorette Gum Icy Mint", R.drawable.nicorettegum_icymint, "Works fast to relieve your cravings and withdrawal symptoms. \n" +
                "\n" +
                "Comes In 2mg Or 4mg To Let You Choose The Right Strength Based On How Much You Smoke. Nicotine Absorbs Quickly Through The Lining Of Your Mouth"));
        allProduct.add(new ProductList("Nicorette Invisi Transdermal Patch", R.drawable.nicorettegum_invisi, "A once-a-day way to beat nicotine cravings. \n" +
                "\n" +
                "Controlled Amounts Of Nicotine Absorb Through Your Skin Into Your Bloodstream"));
        allProduct.add(new ProductList("Nicorette Lozenges", R.drawable.lozenge, "Helps reduce your nicotine cravings and relieves some nicotine withdrawal symptoms to help you quit \n" +
                "\n" +
                "Commit Lozenge® Lozenge delivers strong, lasting medicine, providing controlled amounts of nicotine at a slower, less intense pace than cigarettes to help stop cravings before they start."));
        allProduct.add(new ProductList("Quit Smoking Spray", R.drawable.quitsmoking_spray, "It can effectively improve body damage caused by smoking, suppress the desire to smoke, and gradually eliminate the addiction. \n" +
                "\n" +
                "It is made from natural plant extracts. suppressing the taste of tobacco by the taste of botanicals, influencing people's sense of smell and taste, make the person dislike smoking, so as to get rid of the psychological dependence on cigarettes."));

        return allProduct;
    }*/
}