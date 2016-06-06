package com.example.ylyuan.mydouban;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.ylyuan.mydouban.network.DouBanRestApi;
import com.example.ylyuan.mydouban.network.DouBanRestManager;
import com.example.ylyuan.mydouban.network.NetworkConnectionManager;


public class DouBanApp extends Application{
    private static DouBanApp instance;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static DouBanApp getInstance() {
        return instance;
    }

    public boolean isConnectionAvailable() {
        return NetworkConnectionManager.isConnectionAvailable(instance);
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public DouBanRestApi getRestApi() {
        return DouBanRestManager.getInstance(this).getDoubanRestApi();
    }


}
