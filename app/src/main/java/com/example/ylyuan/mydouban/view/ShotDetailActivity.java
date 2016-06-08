package com.example.ylyuan.mydouban.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.adapter.ShotDetailViewAdapter;
import com.example.ylyuan.mydouban.model.Comment;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.presenter.ShotDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShotDetailActivity extends BaseActivity implements ShotDetailPresenter.ShotsCommentListView {
    public static final String SHOTS = "Shots";

    private ShotDetailPresenter presenter;
    private ShotDetailViewAdapter shotDetailViewAdapter;

    private Shots shots;
    private RecyclerView shotDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ShotDetailPresenter();
        presenter.attachView(ShotDetailActivity.this);

        shots = (Shots) getIntent().getSerializableExtra(SHOTS);

        shotDetail = (RecyclerView) findViewById(R.id.shot_detail);
        shotDetail.setLayoutManager(new LinearLayoutManager(this));
        shotDetailViewAdapter = new ShotDetailViewAdapter(this, new ArrayList<Comment>(), shots);
        shotDetail.setAdapter(shotDetailViewAdapter);

        presenter.loadingComments(shots.getId());
    }


    public static Intent getIntentToMe(Context context, Shots shots) {
        Intent intent = new Intent(context, ShotDetailActivity.class);
        intent.putExtra(SHOTS, shots);
        return intent;
    }

    @Override
    public void refreshShots(List<Comment> comments) {
        shotDetailViewAdapter.refreshData(comments);
    }
}
