package com.example.ylyuan.mydouban.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.view.ShotDetailActivity;
import com.squareup.picasso.Picasso;

public class UserShotViewHolder extends RecyclerView.ViewHolder  {

    private ImageView shotImage;
    private TextView shotViewCount;
    private TextView shotCommentCount;
    private TextView shotLikeCount;

    public UserShotViewHolder(View inflate) {
        super(inflate);
        shotImage = (ImageView) inflate.findViewById(R.id.shot_image);
        shotViewCount = (TextView) inflate.findViewById(R.id.shot_view_count);
        shotCommentCount = (TextView) inflate.findViewById(R.id.shot_comment_count);
        shotLikeCount = (TextView) inflate.findViewById(R.id.shot_like_count);
    }

    public void populate(final Context context, final Shot shot) {
        shotImage.setImageURI(Uri.parse(shot.getImageUrl().getTeaserType()));
        Picasso.with(context)
                .load(Uri.parse(shot.getImageUrl().getTeaserType()))
                .into(shotImage);

        shotImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ShotDetailActivity.getIntentToMe(context, shot));
            }
        });

        shotCommentCount.setText(shot.getCommentsCount().toString());
        shotViewCount.setText(shot.getViewsCount().toString());
        shotLikeCount.setText(shot.getViewsCount().toString());
    }
}
