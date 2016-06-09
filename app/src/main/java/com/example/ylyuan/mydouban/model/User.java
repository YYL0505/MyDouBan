package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("username")
    private String name;
}
