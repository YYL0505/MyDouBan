package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.Shot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePagePresenter {
    private List<Shot> shots;
    private ShotsListView view ;

    public void attachView(ShotsListView v)  {
        if (v instanceof ShotsListView) {
            view = (ShotsListView) v;
        }
    }

    public void loadingData(final int pages) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", String.valueOf(pages));
        params.put("per_page", "20");

        DouBanApp.getInstance().getRestApi().getShots(params).enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                shots = response.body();
                view.refreshShots(shots, pages == 0);
            }

            @Override
            public void onFailure(Call<List<Shot>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface ShotsListView {
        void refreshShots(List<Shot> shots, boolean cleanShots);

    }
}
