package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PwReportListRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("usertype")
    @Expose
    private String usertype;


    public PwReportListRequest(String userId, String token, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String reportId) {
        this.usertype = reportId;
    }

}
