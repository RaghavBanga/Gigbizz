package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFirstReportResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
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

    public SubmitFirstReportResponse(Integer status, String msg, String reportId, String token, String shopStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.token = token;
        this.shopStatus = shopStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SubmitFirstReportResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SubmitFirstReportResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public SubmitFirstReportResponse withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SubmitFirstReportResponse withToken(String token) {
        this.token = token;
        return this;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public SubmitFirstReportResponse withShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

}