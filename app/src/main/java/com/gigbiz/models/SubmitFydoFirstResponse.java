package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFydoFirstResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("report_submitted_for")
    @Expose
    private String reportSubmittedFor;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_owner_name")
    @Expose
    private String shopOwnerName;
    @SerializedName("shop_mobile")
    @Expose
    private String shopMobile;
    @SerializedName("shop_category")
    @Expose
    private String shopCategory;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("report_status")
    @Expose
    private String reportStatus;

    public SubmitFydoFirstResponse(int status, String msg, String reportId, String reportSubmittedFor, String shopName, String shopOwnerName, String shopMobile, String shopCategory, String city, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.reportSubmittedFor = reportSubmittedFor;
        this.shopName = shopName;
        this.shopOwnerName = shopOwnerName;
        this.shopMobile = shopMobile;
        this.shopCategory = shopCategory;
        this.city = city;
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

    public String getReportSubmittedFor() {
        return reportSubmittedFor;
    }

    public void setReportSubmittedFor(String reportSubmittedFor) {
        this.reportSubmittedFor = reportSubmittedFor;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}