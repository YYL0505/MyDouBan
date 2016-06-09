package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.Shots;
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
        DouBanApp.getInstance().getRestApi().getShotsByUser(userId).enqueue(new Callback<List<Shots>>() {
            @Override
            public void onResponse(Call<List<Shots>> call, Response<List<Shots>> response) {
                if (user.getShotses() != null) {
                    user.getShotses().clear();
                    user.getShotses().addAll(response.body());
                } else {
                    user.setShotses(response.body());
                }

                for (Shots shot:user.getShotses()) {
                    shot.setUser(user);
                }

                view.notifyData(user);
            }

            @Override
            public void onFailure(Call<List<Shots>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface UserListView {
        void updateUI(User user);

        void notifyData(User user);
    }
}
