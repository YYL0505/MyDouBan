package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class User implements Serializable {
    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private String useName;

    private String bio;

    private String location;

    @SerializedName("followers_count")
    private int followersCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("shots_count")
    private int shotsCount;

    private List<Shot> shotses;
}
