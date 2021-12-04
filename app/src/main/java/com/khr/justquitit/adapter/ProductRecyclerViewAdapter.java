package com.khr.justquitit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.khr.justquitit.ProductDetails;
import com.khr.justquitit.ProductList;
import com.khr.justquitit.R;

import java.util.ArrayList;


public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {
    private static final String Tag = "RecyclerView";
    private Context context;
    private ArrayList<ProductList> productLists;

    public ProductRecyclerViewAdapter(Context context, ArrayList<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvProductName;
        ImageView imgProduct;
        CardView prodCardview;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            imgProduct = itemView.findViewById(R.id.img_product);
            prodCardview = itemView.findViewById(R.id.prod_cardview);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Product: " + productLists.get(getAdapterPosition()).getProductname(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), ProductDetails.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("productImage", productLists.get(getAdapterPosition()).getImageurl());
            intent.putExtra("productName", productLists.get(getAdapterPosition()).getProductname());
            intent.putExtra("productDescription", productLists.get(getAdapterPosition()).getProductdescription());
            intent.putExtra("productLink", productLists.get(getAdapterPosition()).getLinkUrl());
            view.getContext().startActivity(intent);

        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productview;
        LayoutInflater minflater = LayoutInflater.from(context);
        productview = minflater.inflate(R.layout.product_grid_layout,parent,false);
        return new ProductViewHolder(productview);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.tvProductName.setText(productLists.get(position).getProductname());

        //imageView:Glide library
        Glide.with(context).load(productLists.get(position).getImageurl()).into(holder.imgProduct);

        //set click listener
        /*holder.prodCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing data to product details
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productImage", productLists.get(position).getImageurl());
                intent.putExtra("productName", productLists.get(position).getProductname());
                intent.putExtra("productDescription", productLists.get(position).getProductdescription());
                intent.putExtra("productLink", productLists.get(position).getLinkUrl());
                context.startActivity(intent);

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }


}
