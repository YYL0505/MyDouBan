package com.example.ylyuan.mydouban.network;

import android.content.Context;

import com.example.ylyuan.mydouban.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DouBanRestManager {

    private static DouBanRestManager instance;
    private DouBanRestApi doubanRestApi;

    protected DouBanRestManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build();

        doubanRestApi = retrofit.create(DouBanRestApi.class);
        instance = this;
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    public static DouBanRestManager getInstance(Context context) {
        if (instance == null) {
            instance = new DouBanRestManager(context);
        }
        return instance;
    }

    public DouBanRestApi getDouBanRestApi() {
        return doubanRestApi;
    }

}

