package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
    private User user;

    @SerializedName("body")
    private String detail;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("likes_count")
    private int support;
}
