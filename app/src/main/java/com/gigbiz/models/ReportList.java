package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportList {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("handler")
    @Expose
    private String handler;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img1")
    @Expose
    private String img1;


    public ReportList() {
    }

    public ReportList(String id, String handler, String title, String img1) {
        super();
        this.id = id;
        this.handler = handler;
        this.title = title;
        this.img1 = img1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }
}
