package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ImageUrl {
    @SerializedName("hidpi")
    private String hidpiType;

    @SerializedName("normal")
    private String normalType;

    @SerializedName("teaser")
    private String teaserType;
}
