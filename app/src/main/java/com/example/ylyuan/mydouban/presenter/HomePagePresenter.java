package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.Shots;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePagePresenter {
    private List<Shots> shotses;
    private ShotsListView view ;

    public void attachView(ShotsListView v)  {
        if (v instanceof ShotsListView) {
            view = (ShotsListView) v;
        }
    }

    public void loadingData() {

        DouBanApp.getInstance().getRestApi().getShots().enqueue(new Callback<List<Shots>>() {
            @Override
            public void onResponse(Call<List<Shots>> call, Response<List<Shots>> response) {
                shotses = response.body();
                view.refreshShots(shotses);
            }

            @Override
            public void onFailure(Call<List<Shots>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface ShotsListView {
        void refreshShots(List<Shots> shotses);

    }
}