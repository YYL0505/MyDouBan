package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("username")
    private String name;
}
