package com.example.ylyuan.mydouban.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class ImageUrl implements Serializable{
    @SerializedName("hidpi")
    private String hidpiType;

    @SerializedName("normal")
    private String normalType;

    @SerializedName("teaser")
    private String teaserType;
}
