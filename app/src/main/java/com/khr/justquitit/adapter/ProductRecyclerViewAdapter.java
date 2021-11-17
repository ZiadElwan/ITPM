package com.khr.justquitit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.ProductDetails;
import com.khr.justquitit.ProductList;
import com.khr.justquitit.R;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {

    private Context context;
    private List<ProductList> productLists;

    public ProductRecyclerViewAdapter(Context context, List<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
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
        holder.tvProductName.setText(productLists.get(position).getName());
        holder.imgProduct.setImageResource(productLists.get(position).getImage());

        //set click listener
        holder.prodCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing data to product details
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productImage", productLists.get(position).getImage());
                intent.putExtra("productName", productLists.get(position).getName());
                intent.putExtra("productDescription", productLists.get(position).getDescription());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductName;
        ImageView imgProduct;
        CardView prodCardview;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            imgProduct = itemView.findViewById(R.id.img_product);
            prodCardview = itemView.findViewById(R.id.prod_cardview);

        }
    }




}
