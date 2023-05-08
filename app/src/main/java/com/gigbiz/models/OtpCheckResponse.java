package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpCheckResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("shop_otp")
    @Expose
    private String shopOtp;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("shop_status")
    @Expose
    private String shopStatus;

    public OtpCheckResponse(Integer status, String msg, String shopOtp, String reportId, String token, String shopStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.shopOtp = shopOtp;
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

    public OtpCheckResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OtpCheckResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getShopOtp() {
        return shopOtp;
    }

    public void setShopOtp(String shopOtp) {
        this.shopOtp = shopOtp;
    }

    public OtpCheckResponse withShopOtp(String shopOtp) {
        this.shopOtp = shopOtp;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public OtpCheckResponse withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OtpCheckResponse withToken(String token) {
        this.token = token;
        return this;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public OtpCheckResponse withShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
        return this;
    }

}