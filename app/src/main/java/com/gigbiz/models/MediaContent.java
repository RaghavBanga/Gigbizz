package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaContent {
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("img1")
    @Expose
    private String img1;


    public MediaContent(String projectId, String projectName, String title, String img1) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
        this.title = title;
        this.img1 = img1;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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