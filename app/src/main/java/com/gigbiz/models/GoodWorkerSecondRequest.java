package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodWorkerSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("gw_applied_job_screenshot")
    @Expose
    private String gwAppliedJobScreenshot;
    @SerializedName("gw_selfie")
    @Expose
    private String gwSelfie;
    @SerializedName("gw_upload_document")
    @Expose
    private String gwUploadDocument;
    @SerializedName("gw_hiring_post_screenshot")
    @Expose
    private String gwHiringPostScreenshot;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public GoodWorkerSecondRequest(String userId, String token, String reportId, String gwAppliedJobScreenshot, String gwSelfie, String gwUploadDocument, String gwHiringPostScreenshot, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.gwAppliedJobScreenshot = gwAppliedJobScreenshot;
        this.gwSelfie = gwSelfie;
        this.gwUploadDocument = gwUploadDocument;
        this.gwHiringPostScreenshot = gwHiringPostScreenshot;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getGwAppliedJobScreenshot() {
        return gwAppliedJobScreenshot;
    }

    public void setGwAppliedJobScreenshot(String gwAppliedJobScreenshot) {
        this.gwAppliedJobScreenshot = gwAppliedJobScreenshot;
    }

    public String getGwSelfie() {
        return gwSelfie;
    }

    public void setGwSelfie(String gwSelfie) {
        this.gwSelfie = gwSelfie;
    }

    public String getGwUploadDocument() {
        return gwUploadDocument;
    }

    public void setGwUploadDocument(String gwUploadDocument) {
        this.gwUploadDocument = gwUploadDocument;
    }

    public String getGwHiringPostScreenshot() {
        return gwHiringPostScreenshot;
    }

    public void setGwHiringPostScreenshot(String gwHiringPostScreenshot) {
        this.gwHiringPostScreenshot = gwHiringPostScreenshot;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}