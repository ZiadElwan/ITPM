package com.khr.justquitit.adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.QuitSmokingTips;
import com.khr.justquitit.R;
import com.khr.justquitit.Tips;

import java.util.List;

public class TipsRecyclerViewAdapter extends RecyclerView.Adapter<TipsRecyclerViewAdapter.TipsViewHolder> {
    public List<Tips> tipsList;
    private Context context;


    public TipsRecyclerViewAdapter(Context context, List<Tips> tips) {
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
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder{

//        public TextView tvTips;
        public ImageView imgViewTipsImage;
        public TextView tvTipsName;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvTips = itemView.findViewById(R.id.tv_tips_name);
            tvTipsName = itemView.findViewById(R.id.tv_tips);
            imgViewTipsImage = itemView.findViewById(R.id.constraint);

        }
    }
}
