package com.khr.justquitit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.R;
import com.khr.justquitit.RecoveryHistory;

import java.util.ArrayList;

public class RecoveryHistoryAdapter extends RecyclerView.Adapter<RecoveryHistoryAdapter.RecoveryHolder {

    private Context context;
    private ArrayList<RecoveryHistory> rhistory;

    public RecoveryHistoryAdapter(Context context, ArrayList<RecoveryHistory> rhistory) {
        this.context = context;
        this.rhistory = rhistory;
    }

    @NonNull
    @Override
    public RecoveryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card2, parent, false);
        return new RecoveryHolder(view);
    }

    public void onBindViewHolder(@NonNull RecoveryHolder holder, int position) {
        RecoveryHistory health = rhistory.get(position);
        holder.setDetail(health);
    }

    @Override
    public int getItemCount() {
        return rhistory.size();
    }

    public class RecoveryHolder extends RecyclerView.ViewHolder {
        private TextView txtrecoveryDay, txtrecoveryActivity;

        public RecoveryHolder(@NonNull View itemView) {
            super(itemView);
            txtrecoveryDay = itemView.findViewById(R.id.tv_recoveryday);
            txtrecoveryActivity = itemView.findViewById(R.id.tv_recoveryactivity);
        }

        void setDetail(RecoveryHistory rhistory) {
            txtrecoveryDay.setText(rhistory.getRecoveryDay());
            txtrecoveryActivity.setText(rhistory.getRecoveryActivity());
        }
    }
}
