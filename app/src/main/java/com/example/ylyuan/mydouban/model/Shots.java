package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


@Data
public class Shots {
    private String title;
    private String description;

    @SerializedName("images")
    private ImageUrl imageUrl;

    @SerializedName("views_count")
    private Long viewsCount;

    @SerializedName("likes_count")
    private Long likesCount;

    @SerializedName("comments_count")
    private Long commentsCount;
}
