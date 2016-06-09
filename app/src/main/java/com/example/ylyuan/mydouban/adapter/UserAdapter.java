package com.example.ylyuan.mydouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.model.User;
import com.example.ylyuan.mydouban.viewHolder.UserShotViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserShotViewHolder>  {

    private final LayoutInflater inflater;
    private Context context;
    private List<Shot> shots;

    public UserAdapter(Context context, List<Shot> shots) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shots = shots;
    }

    @Override
    public UserShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserShotViewHolder(inflater.inflate(R.layout.item_shot, parent, false));
    }

    @Override
    public void onBindViewHolder(UserShotViewHolder holder, int position) {
        holder.populate(context, shots.get(position));
    }

    @Override
    public int getItemCount() {
        return shots == null? 0 : shots.size();
    }

    public void notifyData(User user) {
        shots.clear();
        shots.addAll(user.getShots());

        notifyDataSetChanged();
    }
}
