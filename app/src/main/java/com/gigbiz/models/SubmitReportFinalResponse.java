package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReportFinalResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("shop_status")
    @Expose
    private String shopStatus;


    public SubmitReportFinalResponse(int status, String msg, String reportId, String token, String shopStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.token = token;
        this.shopStatus = shopStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SubmitReportFinalResponse withStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SubmitReportFinalResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public SubmitReportFinalResponse withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SubmitReportFinalResponse withToken(String token) {
        this.token = token;
        return this;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public SubmitReportFinalResponse withShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

}