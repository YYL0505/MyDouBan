package com.example.ylyuan.mydouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.viewHolder.ShotsViewHolder;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<ShotsViewHolder> {
    private List<Shots> shotses;

    private LayoutInflater inflater;

    public HomePageAdapter(Context context, List<Shots> shotses) {
        this.inflater = LayoutInflater.from(context);
        this.shotses = shotses;
    }

    @Override
    public ShotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotsViewHolder(inflater.inflate(R.layout.item_shot, parent, false));
    }

    @Override
    public void onBindViewHolder(ShotsViewHolder holder, int position) {
        holder.populate(shotses.get(position));

    }

    @Override
    public int getItemCount() {
        return shotses == null? 0 : shotses.size();
    }


    public void refreshList(List<Shots> data) {
        shotses.clear();
        shotses.addAll(data);

        notifyDataSetChanged();
    }
}
