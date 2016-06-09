package com.example.ylyuan.mydouban.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.view.ShotDetailActivity;
import com.example.ylyuan.mydouban.view.UserActivity;
import com.squareup.picasso.Picasso;

public class ShotsViewHolder extends RecyclerView.ViewHolder {
    private ImageView shotImage;
    private TextView shotViewCount;
    private TextView shotCommentCount;
    private TextView shotLikeCount;
    private ImageView shotUserAvatar;
    private TextView shotUserName;


    public ShotsViewHolder(View inflate) {
        super(inflate);
        shotImage = (ImageView) inflate.findViewById(R.id.shot_image);
        shotViewCount = (TextView) inflate.findViewById(R.id.shot_view_count);
        shotCommentCount = (TextView) inflate.findViewById(R.id.shot_comment_count);
        shotLikeCount = (TextView) inflate.findViewById(R.id.shot_like_count);
        shotUserAvatar = (ImageView) inflate.findViewById(R.id.shot_user_avatar);
        shotUserName = (TextView) inflate.findViewById(R.id.shot_user_name);
    }

    public void populate(final Context context, final Shots shots) {
        shotImage.setImageURI(Uri.parse(shots.getImageUrl().getTeaserType()));
        Picasso.with(context)
                .load(Uri.parse(shots.getImageUrl().getTeaserType()))
                .into(shotImage);

        shotImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ShotDetailActivity.getIntentToMe(context, shots));
            }
        });

        shotCommentCount.setText(shots.getCommentsCount().toString());
        shotViewCount.setText(shots.getViewsCount().toString());
        shotLikeCount.setText(shots.getViewsCount().toString());

        Picasso.with(context)
                .load(Uri.parse(shots.getUser().getAvatarUrl()))
                .into(shotUserAvatar);

        shotUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(UserActivity.getIntentToMe(context, shots.getUser().getId()));
            }
        });
        shotUserName.setText(shots.getUser().getName());
    }
}
