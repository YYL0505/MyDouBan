package com.example.ylyuan.mydouban.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ylyuan.mydouban.R;
import com.example.ylyuan.mydouban.adapter.HomePageAdapter;
import com.example.ylyuan.mydouban.model.Shots;
import com.example.ylyuan.mydouban.presenter.HomePagePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomePagePresenter.ShotsListView, SwipeRefreshLayout.OnRefreshListener{
    private HomePagePresenter presenter;

    private RecyclerView shotsViews;
    private SwipeRefreshLayout swipeRefreshLayout;
    private StaggeredGridLayoutManager layoutManager;
    private HomePageAdapter adapter;

    private boolean loadingData;
    private int pages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        shotsViews = (RecyclerView) findViewById(R.id.shots);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shotsViews.setLayoutManager(layoutManager);

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        setSupportActionBar(toolbar);
        presenter = new HomePagePresenter();
        presenter.attachView(HomeActivity.this);
        adapter = new HomePageAdapter(this, new ArrayList<Shots>());
        shotsViews.setAdapter(adapter);

        loadingData = true;
        presenter.loadingData(pages);
        shotsViews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int[] pastVisiblesItems = new int[2];
                int visibleItemCount;
                int totalItemCount;
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPositions(pastVisiblesItems);

                    if ((visibleItemCount + pastVisiblesItems[0]) >= totalItemCount) {
                        if (!loadingData) {
                            loadingData = true;
                            presenter.loadingData(pages);
                        }

                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshShots(List<Shots> shotses, boolean cleanShots) {
        loadingData = false;
        swipeRefreshLayout.setRefreshing(false);
        pages += 1;
        adapter.refreshList(shotses, cleanShots);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadingData = true;
        pages = 0;
        presenter.loadingData(pages);
    }
}
