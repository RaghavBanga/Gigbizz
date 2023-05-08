package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFydoFirstRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("project_id")
    @Expose
    private String projectId;
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

    public SubmitFydoFirstRequest(String userId, String token, String userType, String projectId, String reportSubmittedFor, String shopName, String shopOwnerName, String shopMobile, String shopCategory, String city) {
        super();
        this.userId = userId;
        this.token = token;
        this.userType = userType;
        this.projectId = projectId;
        this.reportSubmittedFor = reportSubmittedFor;
        this.shopName = shopName;
        this.shopOwnerName = shopOwnerName;
        this.shopMobile = shopMobile;
        this.shopCategory = shopCategory;
        this.city = city;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

}