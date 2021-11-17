package com.khr.justquitit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        TextView productName = findViewById(R.id.tv_prodname);
        TextView productDescription = findViewById(R.id.tv_proddetail);
        ImageView imageView = findViewById(R.id.img_prod);

        //Receive data
        Intent intent = getIntent();
        String prodName = intent.getStringExtra("productName");
        String prodDescription = intent.getStringExtra("productDescription");
        int image = intent.getExtras().getInt("productImage");

        //Set values
        productName.setText(prodName);
        productDescription.setText(prodDescription);
        imageView.setImageResource(image);





    }
}