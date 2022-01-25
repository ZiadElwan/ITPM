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
import com.khr.justquitit.ListTrophy;
import com.khr.justquitit.R;

import java.util.ArrayList;

public class TrophyRecyclerViewAdapter extends RecyclerView.Adapter<TrophyRecyclerViewAdapter.TrophyViewHolder> {
    private static final String Tag = "RecyclerView";

    private Context context;
    private ArrayList<ListTrophy> trophyLists;

    public TrophyRecyclerViewAdapter(Context context, ArrayList<ListTrophy> trophyLists) {
        this.context = context;
        this.trophyLists = trophyLists;
    }

    public class TrophyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTrophyName;
        ImageView imgTrophy;
        CardView trophyCardview;

        public TrophyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrophyName = itemView.findViewById(R.id.tv_trophy_name);
            imgTrophy = itemView.findViewById(R.id.img_trophy);
            trophyCardview = itemView.findViewById(R.id.trophy_cardview);

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
           // Toast.makeText(view.getContext(), "Product: " + trophyLists.get(getAdapterPosition()).getTrophyName(),Toast.LENGTH_SHORT).show();

            //Intent intent = new Intent(view.getContext(), ProductDetails.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.putExtra("productImage", productLists.get(getAdapterPosition()).getImageurl());
            //intent.putExtra("productName", productLists.get(getAdapterPosition()).getProductname());
            //intent.putExtra("productDescription", productLists.get(getAdapterPosition()).getProductdescription());
            //intent.putExtra("productLink", productLists.get(getAdapterPosition()).getLinkUrl());
            //view.getContext().startActivity(intent);

        }

    }
    @NonNull
    @Override
    public TrophyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View trophyview;
        LayoutInflater minflater = LayoutInflater.from(context);
        trophyview = minflater.inflate(R.layout.trophy_grid_layout,parent,false);
        return new TrophyViewHolder(trophyview);
    }

    @Override
    public void onBindViewHolder(@NonNull TrophyViewHolder holder, int position) {
        holder.tvTrophyName.setText(trophyLists.get(position).getTrophyName());

        //imageView:Glide library
        Glide.with(context).load(trophyLists.get(position).getImageUrl()).into(holder.imgTrophy);

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
        return trophyLists.size();
    }
}
