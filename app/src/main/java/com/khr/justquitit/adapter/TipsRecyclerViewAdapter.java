package com.khr.justquitit.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.QuitSmokingTips;
import com.khr.justquitit.R;
import com.khr.justquitit.Tips;
import com.khr.justquitit.TipsDetailActivity;

import java.util.List;

public class TipsRecyclerViewAdapter extends RecyclerView.Adapter<TipsRecyclerViewAdapter.TipsViewHolder> {
    public List<Tips> tipsList;
    private Context context;


    public TipsRecyclerViewAdapter(Context context, List<Tips> tipsList) {
        this.context = context;
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View tips_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row, null);
        TipsViewHolder tipsVH = new TipsViewHolder(tips_row);
        return tipsVH;
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        holder.tvTipsName.setText((tipsList.get(position).getName()));
        holder.imgViewTipsImage.setImageResource(tipsList.get(position).getImage());

        //animation -- saja test
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView tvTips;
        public ImageView imgViewTipsImage;
        public TextView tvTipsName;

        //ini jugak
        CardView cardView;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);

            //barang animation
            cardView = itemView.findViewById(R.id.card_container);


//            tvTips = itemView.findViewById(R.id.tv_tips_name);
            tvTipsName = itemView.findViewById(R.id.tv_tips);
            imgViewTipsImage = itemView.findViewById(R.id.image_tips);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Tips: " + tipsList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), TipsDetailActivity.class);
            intent.putExtra("tipsName", tipsList.get(getAdapterPosition()).getName());
            intent.putExtra("tipsDetail", tipsList.get(getAdapterPosition()).getDetails());
            intent.putExtra("image", tipsList.get(getAdapterPosition()).getImage());
            view.getContext().startActivity(intent);
        }
    }
}
