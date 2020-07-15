package com.somercelik.mesajlamauygulamasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context context;
    List<MessageModel> userList;
    Activity activity;
    String userName;
    Boolean state;
    int view_send = 1, view_received = 2;

    public MessageAdapter(Context context, List<MessageModel> userList, Activity activity, String userName) {
        this.context = context;
        this.userList = userList;
        this.activity = activity;
        this.userName = userName;
        state = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == view_send) {
            view = LayoutInflater.from(context).inflate(R.layout.send_layout, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.received_layout, parent, false);
            return new ViewHolder(view);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.userNameTextView.setText(userList.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        LinearLayout userMainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (state == true) {
                userNameTextView = itemView.findViewById(R.id.send_TextView);
            } else {
                userNameTextView = itemView.findViewById(R.id.received_TextView);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (userList.get(position).getFrom().equals(userName)) {
            state = true;
            return view_send;
        } else {
            state = false;
            return view_received;
        }
    }
}
