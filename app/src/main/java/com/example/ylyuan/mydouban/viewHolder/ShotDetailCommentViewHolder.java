package com.example.ylyuan.mydouban.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.model.Comment;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.util.DateUtil;
import com.example.ylyuan.mydouban.view.UserActivity;
import com.squareup.picasso.Picasso;

import java.util.Date;


public class ShotDetailCommentViewHolder  extends RecyclerView.ViewHolder  {

    private ImageView shotCommentUserAvatar;
    private TextView shotCommentUserName;
    private TextView shotCommentDetail;
    private TextView shotCommentIssued;
    private ImageView shotCommentLikeIcon;
    private TextView shotCommentSupportCount;

    private ImageView shotUserAvatar;
    private TextView shotAuthor;
    private TextView shotTitle;
    private ImageView shotImage;
    private TextView shotViewCount;
    private TextView shotCommentCount;
    private TextView shotLikeCount;
    private TextView shotDescription;

    public ShotDetailCommentViewHolder(View itemView) {
        super(itemView);

        shotCommentUserAvatar = (ImageView) itemView.findViewById(R.id.shot_comment_user_avatar);
        shotCommentUserName = (TextView) itemView.findViewById(R.id.shot_comment_user);

        shotCommentDetail = (TextView) itemView.findViewById(R.id.shot_comment_detail);
        shotCommentIssued = (TextView) itemView.findViewById(R.id.shot_comment_issued);
        shotCommentLikeIcon = (ImageView) itemView.findViewById(R.id.shot_comment_like_icon);
        shotCommentSupportCount = (TextView) itemView.findViewById(R.id.shot_comment_support_count);



        shotUserAvatar = (ImageView) itemView.findViewById(R.id.shot_user_avatar);
        shotTitle = (TextView) itemView.findViewById(R.id.shot_title);
        shotAuthor = (TextView) itemView.findViewById(R.id.shot_author);

        shotImage = (ImageView) itemView.findViewById(R.id.shot_image);
        shotViewCount = (TextView) itemView.findViewById(R.id.shot_view_count);
        shotCommentCount = (TextView) itemView.findViewById(R.id.shot_comment_count);
        shotLikeCount = (TextView) itemView.findViewById(R.id.shot_like_count);

        shotDescription = (TextView) itemView.findViewById(R.id.shot_description);

    }

    public void populate(final Context context, final Comment comment) {
        Picasso.with(context)
                .load(Uri.parse(comment.getUser().getAvatarUrl()))
                .into(shotCommentUserAvatar);
        shotCommentUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(UserActivity.getIntentToMe(context, comment.getUser().getId()));
            }
        });

        shotCommentUserName.setText(comment.getUser().getName());

        shotCommentDetail.setText(Html.fromHtml(comment.getDetail()));

        calculateIssuedTime(context, comment.getUpdatedAt());
        shotCommentLikeIcon.setVisibility(comment.getSupport() > 0 ? View.VISIBLE : View.GONE);
        shotCommentSupportCount.setText(String.valueOf(comment.getSupport()));
        shotCommentSupportCount.setVisibility(comment.getSupport() > 0 ? View.VISIBLE : View.GONE);

    }
    public void populate(final Context context, final Shots shots) {
        Picasso.with(context)
                .load(Uri.parse(shots.getUser().getAvatarUrl()))
                .into(shotUserAvatar);
        shotUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(UserActivity.getIntentToMe(context, shots.getUser().getId()));
            }
        });

        shotTitle.setText(shots.getTitle());
        shotAuthor.setText(shots.getUser().getName());

        Picasso.with(context)
                .load(Uri.parse(shots.getImageUrl().getTeaserType()))
                .into(shotImage);

        shotCommentCount.setText(shots.getCommentsCount().toString());
        shotViewCount.setText(shots.getViewsCount().toString());
        shotLikeCount.setText(shots.getViewsCount().toString());

        if (null != shots.getDescription()) {
            shotDescription.setText(Html.fromHtml(shots.getDescription()));
        } else {
            shotDescription.setText("");
        }

    }

    private void calculateIssuedTime(Context context, Date createdAt) {
        long diff = new Date().getTime() - createdAt.getTime();
        if (diff < DateUtil.MILLISECONDS_HOUR) {
            int quantity = (int) (diff / DateUtil.MILLISECONDS_MINUTE);
            shotCommentIssued.setText(context.getResources().getQuantityString(R.plurals.minute_unit, quantity, quantity));
        } else if (diff < DateUtil.MILLISECONDS_DAY) {
            int quantity = (int) (diff / DateUtil.MILLISECONDS_HOUR);
            shotCommentIssued.setText(context.getResources().getQuantityString(R.plurals.hour_unit, quantity, quantity));
        } else {
            int quantity = (int) (diff / DateUtil.MILLISECONDS_DAY);
            shotCommentIssued.setText(context.getResources().getQuantityString(R.plurals.day_unit, quantity, quantity));
        }
    }
}
