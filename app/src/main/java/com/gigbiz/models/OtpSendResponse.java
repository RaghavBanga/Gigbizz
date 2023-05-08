package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpSendResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_phone")
    @Expose
    private String shopPhone;
    @SerializedName("shop_otp")
    @Expose
    private String shopOtp;
    @SerializedName("report_status")
    @Expose
    private String reportStatus;


    public OtpSendResponse(Integer status, String msg, String reportId, String shopName, String shopPhone, String shopOtp, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.shopOtp = shopOtp;
        this.reportStatus = reportStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopOtp() {
        return shopOtp;
    }

    public void setShopOtp(String shopOtp) {
        this.shopOtp = shopOtp;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}