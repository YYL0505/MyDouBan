package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
import com.example.ylyuan.mydouban.model.User;

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

    public void loadingUser(int userId) {
        DouBanApp.getInstance().getRestApi().getUsers(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                view.updateUI(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface UserListView {
        void updateUI(User user);
    }
}
