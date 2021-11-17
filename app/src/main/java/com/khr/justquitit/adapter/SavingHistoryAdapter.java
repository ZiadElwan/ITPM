package com.khr.justquitit.adapter;

import android.content.Context;// <-- nnti edit ke firebase
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.R;
import com.khr.justquitit.SavingHistory;

import java.util.ArrayList;

public class SavingHistoryAdapter extends RecyclerView.Adapter<SavingHistoryAdapter.saving_Holder> {

    //CardAdapter Class
    private Context context;
    private ArrayList<SavingHistory> historys;

    //Construct

    public SavingHistoryAdapter(Context context, ArrayList<SavingHistory> historys) {
        this.context = context;
        this.historys = historys;
    }

    @NonNull
    @Override
    public saving_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new saving_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull saving_Holder holder, int position) {
        SavingHistory saving = historys.get(position);
        holder.setDetail(saving);

        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

    }

    @Override
    public int getItemCount() {
        return historys.size();
    }


    //saving_history_adapter class

    //view Holder: saving_Holder
    public class saving_Holder extends RecyclerView.ViewHolder{
        private TextView txtsavingDate, txtsavingActivity, txtsaving;
        CardView cardView;

        public saving_Holder(@NonNull View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.card_contain);
            txtsavingDate = itemView.findViewById(R.id.text_date);
            txtsavingActivity = itemView.findViewById(R.id.text_activity);
            txtsaving = itemView.findViewById(R.id.text_saving);
        }

        void setDetail(SavingHistory history){
            txtsavingDate.setText(history.getSavingDate());
            txtsavingActivity.setText(history.getSavingActivity());
            txtsaving.setText(history.getSaving());
        }


    }
}
