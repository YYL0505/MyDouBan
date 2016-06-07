package com.example.ylyuan.mydouban.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.squareup.picasso.Picasso;

public class ShotsViewHolder extends RecyclerView.ViewHolder {
    private ImageView shotImage;
    private TextView shotViewCount;
    private TextView shotCommentCount;
    private TextView shotLikeCount;


    public ShotsViewHolder(View inflate) {
        super(inflate);
        shotImage = (ImageView) inflate.findViewById(R.id.shot_image);
        shotViewCount = (TextView) inflate.findViewById(R.id.shot_view_count);
        shotCommentCount = (TextView) inflate.findViewById(R.id.shot_comment_count);
        shotLikeCount = (TextView) inflate.findViewById(R.id.shot_like_count);
    }

    public void populate(Context context, Shots shots) {
        Uri uri = Uri.parse(shots.getImageUrl().getTeaserType());
        shotImage.setImageURI(uri);
        Picasso.with(context)
                .load(uri)
                .into(shotImage);
        shotCommentCount.setText(shots.getCommentsCount().toString());
        shotViewCount.setText(shots.getViewsCount().toString());
        shotLikeCount.setText(shots.getViewsCount().toString());
    }
}
