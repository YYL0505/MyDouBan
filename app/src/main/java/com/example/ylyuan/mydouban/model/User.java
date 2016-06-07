package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User {
    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("username")
    private String name;
}
