package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


import lombok.Data;


@Data
public class Shots {
    private String title;
    private String description;
    private List<String> images;

    @SerializedName("views_count")
    private Long viewsCount;

    @SerializedName("likes_count")
    private Long likesCount;

    @SerializedName("comments_count")
    private Long commentsCount;
}
