package com.example.ylyuan.mydouban.network;

import com.example.ylyuan.mydouban.model.Comment;
import com.example.ylyuan.mydouban.model.Shot;
import com.example.ylyuan.mydouban.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface DouBanRestApi {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 06dde48a787703eabbb9b42f68ed8b24ab5be606eb03a837637cf47145ebded2"
    })
    @GET("/v1/shots")
    Call<List<Shot>> getShots(@QueryMap Map<String, String> options);


    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 06dde48a787703eabbb9b42f68ed8b24ab5be606eb03a837637cf47145ebded2"
    })
    @GET("/v1/users/{userId}/shots")
    Call<List<Shot>> getShotsByUser(@Path("userId") int userId);


    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 06dde48a787703eabbb9b42f68ed8b24ab5be606eb03a837637cf47145ebded2"
    })
    @GET("/v1/shots/{shotId}/comments")
    Call<List<Comment>> getComments(@Path("shotId") int shotid);


    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 06dde48a787703eabbb9b42f68ed8b24ab5be606eb03a837637cf47145ebded2"
    })
    @GET("/v1/users/{userId}")
    Call<User> getUsers(@Path("userId") int userId);


}
