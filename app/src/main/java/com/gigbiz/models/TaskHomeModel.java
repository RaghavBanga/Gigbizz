package com.gigbiz.models;

public class TaskHomeModel {

    private String projectName;
    private int image;

    public TaskHomeModel(String projectName, int image) {
        this.projectName = projectName;
        this.image = image;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
