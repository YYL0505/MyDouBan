package com.example.ylyuan.mydouban.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.view.UserActivity;
import com.squareup.picasso.Picasso;

public class ShotsViewHolder extends UserShotViewHolder {
    private ImageView shotUserAvatar;
    private TextView shotUserName;


    public ShotsViewHolder(View inflate) {
        super(inflate);
        shotUserAvatar = (ImageView) inflate.findViewById(R.id.shot_user_avatar);
        shotUserName = (TextView) inflate.findViewById(R.id.shot_user_name);
    }

    public void populate(final Context context, final Shots shots) {
        super.populate(context, shots);
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
