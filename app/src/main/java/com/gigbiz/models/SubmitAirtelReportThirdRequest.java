package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAirtelReportThirdRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("fse_name")
    @Expose
    private String fseName;
    @SerializedName("fse_mobile")
    @Expose
    private String fseMobile;
    @SerializedName("trans_done")
    @Expose
    private String transDone;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitAirtelReportThirdRequest(String userId, String token, String reportId, String fseName, String fseMobile, String transDone, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.fseName = fseName;
        this.fseMobile = fseMobile;
        this.transDone = transDone;
        this.userType = userType;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFseName() {
        return fseName;
    }

    public void setFseName(String fseName) {
        this.fseName = fseName;
    }

    public String getFseMobile() {
        return fseMobile;
    }

    public void setFseMobile(String fseMobile) {
        this.fseMobile = fseMobile;
    }

    public String getTransDone() {
        return transDone;
    }

    public void setTransDone(String transDone) {
        this.transDone = transDone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}