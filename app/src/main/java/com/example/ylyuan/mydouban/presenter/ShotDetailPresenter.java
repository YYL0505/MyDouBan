package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShotDetailPresenter {
    private List<Comment> comments;
    private ShotsCommentListView view ;

    public void attachView(ShotsCommentListView v)  {
        if (v instanceof ShotsCommentListView) {
            view = v;
        }
    }

    public void loadingComments(int id) {

        DouBanApp.getInstance().getRestApi().getComments(id).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                comments = response.body();
                view.refreshShots(comments);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface ShotsCommentListView {
        void refreshShots(List<Comment> comments);

    }
}
