package com.example.ylyuan.mydouban.network;

import android.content.Context;

import com.example.ylyuan.mydouban.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DouBanRestManager {

    private static DouBanRestManager instance;
    private DouBanRestApi doubanRestApi;

    protected DouBanRestManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doubanRestApi = retrofit.create(DouBanRestApi.class);
        instance = this;
    }


    public static DouBanRestManager getInstance(Context context) {
        if (instance == null) {
            instance = new DouBanRestManager(context);
        }
        return instance;
    }

    public DouBanRestApi getDoubanRestApi() {
        return doubanRestApi;
    }

}

