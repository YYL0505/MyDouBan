package com.example.ylyuan.mydouban.presenter;

import com.example.ylyuan.mydouban.DouBanApp;
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

    public void loadingUser(int userId) {
        DouBanApp.getInstance().getRestApi().getUsers(userId).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                user = (User) response.body();
                view.updateUI(user);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public interface UserListView {
        void updateUI(User user);
    }
}
