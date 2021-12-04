package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductDetails extends AppCompatActivity {
    String productid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");

        //Receive data
        Intent intent = getIntent();

        TextView tvproductName = findViewById(R.id.tv_prodname);
        TextView tvproductDescription = findViewById(R.id.tv_proddetail);
        ImageView imageView = findViewById(R.id.img_prod);
        FloatingActionButton floatingActionButton = findViewById(R.id.btn_linkprod);

        tvproductName.setText(intent.getStringExtra("productName"));
        tvproductDescription.setText(intent.getStringExtra("productDescription"));

        String image = intent.getStringExtra("productImage");
        Glide.with(ProductDetails.this).load(image).into(imageView);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = intent.getStringExtra("productLink");
                gotoUrl(link);
            }
        });



    }

    //link to product website
    private void gotoUrl(String link) {
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}