package com.example.ylyuan.mydouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.viewHolder.ShotViewHolder;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<ShotViewHolder> {
    private List<Shot> shots;

    private LayoutInflater inflater;

    private Context context;

    public HomePageAdapter(Context context, List<Shot> shots) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shots = shots;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotViewHolder(inflater.inflate(R.layout.item_shot, parent, false));
    }

    @Override
    public void onBindViewHolder(ShotViewHolder holder, int position) {
        holder.populate(context, shots.get(position));

    }

    @Override
    public int getItemCount() {
        return shots == null? 0 : shots.size();
    }


    public void refreshList(List<Shot> data, boolean cleanShots) {
        if (cleanShots) {
            shots.clear();
        }
        shots.addAll(data);

        notifyDataSetChanged();
    }
}
