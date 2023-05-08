package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponse {

    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;


    public BannerResponse() {
    }

    public BannerResponse(List<Banner> banner) {
        super();
        this.banner = banner;
    }

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

}