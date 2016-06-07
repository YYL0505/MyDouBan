package com.example.ylyuan.mydouban.viewHolder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.facebook.drawee.view.SimpleDraweeView;

public class ShotsViewHolder extends RecyclerView.ViewHolder {
    private SimpleDraweeView shotImage;
    private TextView shotViewCount;
    private TextView shotCommentCount;
    private TextView shotLikeCount;


    public ShotsViewHolder(View inflate) {
        super(inflate);
        shotImage = (SimpleDraweeView) inflate.findViewById(R.id.shot_image);
        shotViewCount = (TextView) inflate.findViewById(R.id.shot_view_count);
        shotCommentCount = (TextView) inflate.findViewById(R.id.shot_comment_count);
        shotLikeCount = (TextView) inflate.findViewById(R.id.shot_like_count);
    }

    public void populate(Shots shots) {
        Uri uri = Uri.parse(shots.getImageUrl().getNormalType());
        shotImage.setImageURI(uri);
        shotCommentCount.setText(shots.getCommentsCount().toString());
        shotViewCount.setText(shots.getViewsCount().toString());
        shotLikeCount.setText(shots.getViewsCount().toString());
    }
}
