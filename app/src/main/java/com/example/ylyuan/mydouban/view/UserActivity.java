package com.example.ylyuan.mydouban.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.adapter.UserAdapter;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.model.User;
import com.example.ylyuan.mydouban.presenter.UserPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserActivity extends BaseActivity implements UserPresenter.UserListView{
    public static final String USER_NAME = "userName";
    private int userId;
    private UserPresenter presenter;

    private TextView userShotsCount;
    private TextView userFollowersCount;
    private ImageView userAvatar;
    private TextView userName;
    private TextView userLocation;
    private TextView userDescription;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        presenter = new UserPresenter();
        presenter.attachView(UserActivity.this);

        Intent intent = getIntent();
        userId = intent.getIntExtra(USER_NAME, 1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_detail);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.user_header);
        header.attachTo(recyclerView);

        userAdapter = new UserAdapter(this, new ArrayList<Shot>());
        recyclerView.setAdapter(userAdapter);

        userShotsCount = (TextView) findViewById(R.id.user_shot_count);
        userFollowersCount = (TextView) findViewById(R.id.user_follower_count);
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userName = (TextView) findViewById(R.id.user_name);
        userLocation = (TextView) findViewById(R.id.user_location);
        userDescription = (TextView) findViewById(R.id.user_description);

        presenter.loadingUser(userId);
    }

    public static Intent getIntentToMe(Context context, int id) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(USER_NAME, id);
        return intent;
    }

    @Override
    public void updateUI(User user) {
        userShotsCount.setText(getResources().getQuantityString(R.plurals.user_shot_count, user.getShotsCount(), user.getShotsCount()));
        userFollowersCount.setText(getResources().getQuantityString(R.plurals.user_follower_count, user.getFollowersCount(), user.getFollowersCount()));
        Picasso.with(this)
                .load(Uri.parse(user.getAvatarUrl()))
                .into(userAvatar);
        userName.setText(user.getName());
        userLocation.setText(user.getLocation());
        userDescription.setText(Html.fromHtml(user.getBio()));
    }

    @Override
    public void notifyData(User user) {
        userAdapter.notifyData(user);
    }

}
