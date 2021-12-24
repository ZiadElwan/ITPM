package com.khr.justquitit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khr.justquitit.MyApplication;
import com.khr.justquitit.QuitSmokingTipsFirebase;
import com.khr.justquitit.TipsDetailActivity;
import com.khr.justquitit.TipsFirebase;
import com.khr.justquitit.databinding.ActivityFavTipsBinding;
import com.khr.justquitit.databinding.FavRowBinding;
//import com.khr.justquitit.databinding.FavtipsRowBinding;

import java.util.ArrayList;

public class AdapterFavTips extends RecyclerView.Adapter<AdapterFavTips.HolderFavTips> {
    private Context mContext;
    private ArrayList<TipsFirebase> favtipsArrayList;
    private @NonNull
    FavRowBinding binding;

    public AdapterFavTips(Context context, ArrayList<TipsFirebase> favtipsArrayList) {
        this.mContext = context;
        this.favtipsArrayList = favtipsArrayList;
    }

    @NonNull
    @Override
    public HolderFavTips onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FavRowBinding.inflate(LayoutInflater.from(mContext),parent,false);

        return new HolderFavTips(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFavTips holder, int position) {
        TipsFirebase tips = favtipsArrayList.get(position);

        loadTipsDetails(tips, holder, position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TipsDetailActivity.class);
                intent.putExtra("tipsId", tips.getTipsId());
                intent.putExtra("tipsName", tips.getTitle());
                intent.putExtra("tipsDetail", tips.getDescription());
                intent.putExtra("image", tips.getImage());
                mContext.startActivity(intent);
            }
        });

        holder.removeFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuitSmokingTipsFirebase.removeFromFavourite(mContext, tips.getTipsId());
            }
        });
    }

    private void loadTipsDetails(TipsFirebase tips, HolderFavTips holder, int position) {
    String tipsId = tips.getTipsId();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TipsQuitSmoking");
        ref.child(tipsId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = "" + snapshot.child("Title").getValue();
                        String description = "" + snapshot.child("Description").getValue();
                        String image = "" + snapshot.child("Image").getValue();

                        tips.setFavourite(true);
                        tips.setTitle(title);
                        tips.setDescription(description);
                        tips.setImage(image);

                        Glide.with(mContext)
                                .load(favtipsArrayList.get(position).getImage())
//                                .skipMemoryCache(true)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(holder.imageView);
                        holder.textView.setText(title);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return favtipsArrayList.size();
    }

    class HolderFavTips extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView description;
        CardView cardView;
        ImageButton removeFavButton;

        public HolderFavTips(@NonNull View itemView) {
            super(itemView);

            imageView = binding.imageFavtips;
            textView = binding.tvFavtips;
            removeFavButton = binding.removeFavButton;
        }
    }
}
