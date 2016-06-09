package com.example.ylyuan.mydouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Comment;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.viewHolder.ShotDetailCommentViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShotDetailViewAdapter extends RecyclerView.Adapter<ShotDetailCommentViewHolder>  {
    public static final int HEADER = 0;
    public static final int BODY = 1;
    private Context context;
    private LayoutInflater inflater;
    private List<Comment> comments;
    private Shot shot;

    public ShotDetailViewAdapter(Context context, ArrayList<Comment> comments, Shot shot) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.comments = comments;
        this.shot = shot;
    }

    @Override
    public ShotDetailCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER) {
            return new ShotDetailCommentViewHolder(inflater.inflate(R.layout.item_shot_detail_header, parent, false));
        } else {
            return new ShotDetailCommentViewHolder(inflater.inflate(R.layout.item_shot_comment, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ShotDetailCommentViewHolder holder, int position) {
        if (position == 0) {
            holder.populate(context, shot);
        } else {
            holder.populate(context, comments.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return comments == null? 0 : comments.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0? HEADER : BODY;
    }

    public void refreshData(List<Comment> data) {
        comments.clear();
        comments.addAll(data);
        notifyDataSetChanged();
    }
}
