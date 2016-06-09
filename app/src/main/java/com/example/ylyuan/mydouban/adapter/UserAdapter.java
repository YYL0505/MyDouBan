package com.example.ylyuan.mydouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.model.User;
import com.example.ylyuan.mydouban.viewHolder.UserShotViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserShotViewHolder>  {

    private final LayoutInflater inflater;
    private Context context;
    private List<Shots> shotses;

    public UserAdapter(Context context, List<Shots> shotses) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shotses = shotses;
    }

    @Override
    public UserShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserShotViewHolder(inflater.inflate(R.layout.item_shot, parent, false));
    }

    @Override
    public void onBindViewHolder(UserShotViewHolder holder, int position) {
        holder.populate(context, shotses.get(position));
    }

    @Override
    public int getItemCount() {
        return shotses == null? 0 : shotses.size();
    }

    public void notifyData(User user) {
        shotses.clear();
        shotses.addAll(user.getShotses());

        notifyDataSetChanged();
    }
}
