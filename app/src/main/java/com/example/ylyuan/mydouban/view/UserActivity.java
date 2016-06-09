package com.example.ylyuan.mydouban.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.ylyuan.mydouban.R;

public class UserActivity extends BaseActivity {
    public static final String USER_NAME = "userName";
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Intent intent = getIntent();
        userId = intent.getIntExtra(USER_NAME, 1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_detail);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.user_header);
        header.attachTo(recyclerView);
    }

    public static Intent getIntentToMe(Context context, int id) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(USER_NAME, id);
        return intent;
    }
}
