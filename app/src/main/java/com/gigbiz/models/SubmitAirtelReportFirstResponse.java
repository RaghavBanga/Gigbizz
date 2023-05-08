package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAirtelReportFirstResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("agency_name")
    @Expose
    private String agencyName;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_mobile")
    @Expose
    private String shopMobile;
    @SerializedName("report_status")
    @Expose
    private String reportStatus;

    public SubmitAirtelReportFirstResponse(int status, String msg, String reportId, String agencyName, String shopName, String shopMobile, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.agencyName = agencyName;
        this.shopName = shopName;
        this.shopMobile = shopMobile;
        this.reportStatus = reportStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}