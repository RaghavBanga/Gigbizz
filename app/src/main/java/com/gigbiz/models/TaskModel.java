package com.gigbiz.models;


public class TaskModel {

    private String projectid;
    private String projectName;
    private String comp_name;
    private String image;
    private String mode;
    private String project_status;
    private String project_price;
    private String handler;


    public TaskModel(String projectId,String projectName, String comp_name, String image, String mode, String project_status, String project_price, String handler) {
        this.projectName = projectName;
        this.projectid = projectId;
        this.comp_name = comp_name;
        this.image = image;
        this.mode = mode;
        this.project_status = project_status;
        this.project_price = project_price;
        this.handler = handler;

    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getProject_price() {
        return project_price;
    }

    public void setProject_price(String project_price) {
        this.project_price = project_price;
    }
}
