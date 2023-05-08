package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodWorkerFirstRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("gw_type")
    @Expose
    private String gwType;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public GoodWorkerFirstRequest(String userId, String token, String projectId, String gwType, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.projectId = projectId;
        this.gwType = gwType;
        this.usertype = usertype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getGwType() {
        return gwType;
    }

    public void setGwType(String gwType) {
        this.gwType = gwType;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}