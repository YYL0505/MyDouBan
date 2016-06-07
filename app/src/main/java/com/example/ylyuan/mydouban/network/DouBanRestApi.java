package com.example.ylyuan.mydouban.network;

import com.example.ylyuan.mydouban.model.Shots;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DouBanRestApi {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 06dde48a787703eabbb9b42f68ed8b24ab5be606eb03a837637cf47145ebded2"
    })
    @GET("/v1/users/1//shots/")
    Call<List<Shots>> getShots();
}
