package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private User user;
    private UserListView view;

    public void attachView(UserListView v)  {
        if (v instanceof UserListView) {
            view = v;
        }
    }

    public void loadingUser(final int userId) {
        DouBanApp.getInstance().getRestApi().getUsers(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                loadingShotsById(userId);
                view.updateUI(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadingShotsById(int userId) {
        DouBanApp.getInstance().getRestApi().getShotsByUser(userId).enqueue(new Callback<List<Shot>>() {
            @Override
            public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                if (user.getShots() != null) {
                    user.getShots().clear();
                    user.getShots().addAll(response.body());
                } else {
                    user.setShots(response.body());
                }

                for (Shot shot:user.getShots()) {
                    shot.setUser(user);
                }

                view.notifyData(user);
            }

            @Override
            public void onFailure(Call<List<Shot>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface UserListView {
        void updateUI(User user);

        void notifyData(User user);
    }
}
