package com.khr.justquitit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khr.justquitit.R;
import com.khr.justquitit.UserNotification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    Context context;
    ArrayList<UserNotification>list;

    public NotificationAdapter(Context context, ArrayList<UserNotification> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_card3, parent, false);
        return new NotificationHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        UserNotification user = list.get(position);
        holder.noti.setText(user.getNoti());
        holder.notidetails.setText(user.getNotidetails());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NotificationHolder extends RecyclerView.ViewHolder{

        TextView noti, notidetails;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            noti = itemView.findViewById(R.id.tv_noti);
            notidetails = itemView.findViewById(R.id.tv_notidetails);
        }
    }


}
