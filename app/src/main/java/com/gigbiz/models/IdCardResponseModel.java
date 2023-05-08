package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IdCardResponseModel {

    @SerializedName("icard_url")
    @Expose
    private List<IcardUrl> icardUrl;

    public List<IcardUrl> getIcardUrl() {
        return icardUrl;
    }

    public void setIcardUrl(List<IcardUrl> icardUrl) {
        this.icardUrl = icardUrl;
    }

}
