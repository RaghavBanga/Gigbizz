package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainingContent {
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;

    public TrainingContent() {
    }

    public TrainingContent(String projectId, String projectName, String title, String videoUrl) {
        super();
        this.projectId = projectId;
        this.projectName = projectName;
        this.title = title;
        this.videoUrl = videoUrl;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}