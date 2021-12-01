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
import com.khr.justquitit.R;
import com.khr.justquitit.TipsDetailActivity;
import com.khr.justquitit.TipsFirebase;

import java.util.ArrayList;

public class TipsAdapterFirebase extends RecyclerView.Adapter<TipsAdapterFirebase.ViewHolder> {
    private static final String  Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<TipsFirebase> tipsList;


    public TipsAdapterFirebase(Context mContext, ArrayList<TipsFirebase> tipsList) {
        this.mContext = mContext;
        this.tipsList = tipsList;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_container);
            imageView = itemView.findViewById(R.id.image_tips);
            textView = itemView.findViewById(R.id.tv_tips);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Tips: " + tipsList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), TipsDetailActivity.class);
            intent.putExtra("tipsName", tipsList.get(getAdapterPosition()).getTitle());
            intent.putExtra("tipsDetail", tipsList.get(getAdapterPosition()).getDescription());
            intent.putExtra("image", tipsList.get(getAdapterPosition()).getImage());
            view.getContext().startActivity(intent);
        }
    }

    @NonNull
    @Override
    public TipsAdapterFirebase.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tips_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsAdapterFirebase.ViewHolder holder, int position) {
        holder.textView.setText(tipsList.get(position).getTitle());

        Glide.with(mContext)
                .load(tipsList.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }
}
//
//    private  Context context;
//    public ArrayList<TipsFirebase> tipsList;
//
//    private TipsRowBinding binding;
//
//    private static final String TAG = "ADAPTER_TIPS_TAG";
//
//    public TipsAdapterFirebase(Context context, ArrayList<TipsFirebase> tipsList) {
//        this.context = context;
//        this.tipsList = tipsList;
//    }
//
//    @NonNull
//    @Override
//    public HolderTips onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        binding = TipsRowBinding.inflate(LayoutInflater.from(context),parent,false);
//        return new HolderTips(binding.getRoot());
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HolderTips holder, int position) {
//        TipsFirebase tipsFirebase = tipsList.get(position);
//        String title = tipsFirebase.getTitle();
//        String description = tipsFirebase.getDescription();
//        String image = tipsFirebase.getImage();
//
//        holder.textView.setText(title);
//
////        Glide.with(context)
////                .load(tipsList.get(position).getImage())
////                .into(holder.imageView);
//
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, TipsDetailActivity.class);
//                intent
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return tipsList.size();
//    }
//
//    class HolderTips extends RecyclerView.ViewHolder {
//
//        ImageView imageView;
//        TextView textView;
//
//        public HolderTips(@NonNull View itemView) {
//            super(itemView);
//
//            textView = binding.tvTips;
//            imageView = binding.imageTips;
//        }
//    }
//    private static final String Tag = "RecyclerView";
//    private ArrayList<TipsFirebase> tipsList;
//    private Context mContext;
//
//    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//    private StorageReference ref = FirebaseStorage.getInstance().getReference();
//
//    public TipsAdapterFirebase(ArrayList<TipsFirebase> tipsList, Context mContext) {
//        this.tipsList = tipsList;
//        this.mContext = mContext;
//    }
//
//
//    @NonNull
//    @Override
//    public TipsAdapterFirebase.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.tips_row,parent,false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(tipsList.get(position).getTitle());
//        Glide.with(mContext)
//                .load(tipsList.get(position).getImage())
//                .into(holder.imageView);
//
////        Picasso.get().load(tipsList.get(position).getImage()).into(holder.imageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return tipsList.size();
//    }
//
//    public  class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//        TextView textView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            textView = itemView.findViewById(R.id.tv_tips);
//            imageView = itemView.findViewById(R.id.image_tips);
//        }



//    public TipsAdapterFirebase(Context context, List<TipsFirebase> tipsList) {
//        this.context = context;
//        this.tipsList = tipsList;
//    }

//    @NonNull
//    @Override
//    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View tips_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row, parent, false);
//        TipsViewHolder tipsVH = new TipsViewHolder(tips_row);
//        return tipsVH;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
//        holder.tvTipsName.setText((tipsList.get(position).getTitle()));
////        holder.imgViewTipsImage.setImageResource(tipsList.get(position).getImage());
//        Glide.with(context).load(tipsList.get(position).getImage()).into(holder.imgViewTipsImage);
//
//        //animation -- saja test
//        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return tipsList.size();
//    }
//
//    public class TipsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        //        public TextView tvTips;
//        public ImageView imgViewTipsImage;
//        public TextView tvTipsName;
//
//        //ini jugak
//        CardView cardView;
//
//        public TipsViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            //barang animation
//            cardView = itemView.findViewById(R.id.card_container);
//
//
////            tvTips = itemView.findViewById(R.id.tv_tips_name);
//            tvTipsName = itemView.findViewById(R.id.tv_tips);
//            imgViewTipsImage = itemView.findViewById(R.id.image_tips);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }

//        @Override
//        public void onClick(View view) {
//            Toast.makeText(view.getContext(),"Tips: " + tipsList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(view.getContext(), TipsDetailActivity.class);
//            intent.putExtra("tipsName", tipsList.get(getAdapterPosition()).getName());
//            intent.putExtra("tipsDetail", tipsList.get(getAdapterPosition()).getDetails());
//            intent.putExtra("image", tipsList.get(getAdapterPosition()).getImage());
//            view.getContext().startActivity(intent);
//        }

