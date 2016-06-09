package com.example.ylyuan.mydouban.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.adapter.ShotDetailViewAdapter;
import com.example.ylyuan.mydouban.model.Comment;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.presenter.ShotDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShotDetailActivity extends BaseActivity implements ShotDetailPresenter.ShotsCommentListView {
    public static final String SHOTS = "Shot";

    private ShotDetailPresenter presenter;
    private ShotDetailViewAdapter shotDetailViewAdapter;

    private Shot shot;
    private RecyclerView shotDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);

        presenter = new ShotDetailPresenter();
        presenter.attachView(ShotDetailActivity.this);

        shot = (Shot) getIntent().getSerializableExtra(SHOTS);

        shotDetail = (RecyclerView) findViewById(R.id.shot_detail);
        shotDetail.setLayoutManager(new LinearLayoutManager(this));
        shotDetailViewAdapter = new ShotDetailViewAdapter(this, new ArrayList<Comment>(), shot);
        shotDetail.setAdapter(shotDetailViewAdapter);

        presenter.loadingComments(shot.getId());
    }


    public static Intent getIntentToMe(Context context, Shot shot) {
        Intent intent = new Intent(context, ShotDetailActivity.class);
        intent.putExtra(SHOTS, shot);
        return intent;
    }

    @Override
    public void refreshShots(List<Comment> comments) {
        shotDetailViewAdapter.refreshData(comments);
    }
}
