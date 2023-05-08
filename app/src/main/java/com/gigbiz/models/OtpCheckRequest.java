package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpCheckRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("shop_otp")
    @Expose
    private String shopOtp;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public OtpCheckRequest(String userId, String token, String reportId, String shopOtp, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.shopOtp = shopOtp;
        this.usertype = usertype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OtpCheckRequest withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OtpCheckRequest withToken(String token) {
        this.token = token;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public OtpCheckRequest withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getShopOtp() {
        return shopOtp;
    }

    public void setShopOtp(String shopOtp) {
        this.shopOtp = shopOtp;
    }

    public OtpCheckRequest withShopOtp(String shopOtp) {
        this.shopOtp = shopOtp;
        return this;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public OtpCheckRequest withUsertype(String usertype) {
        this.usertype = usertype;
        return this;
    }

}