package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAirtelReportFirstRequest {

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
    @SerializedName("agency_name")
    @Expose
    private String agencyName;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_mobile")
    @Expose
    private String shopMobile;

    public SubmitAirtelReportFirstRequest(String userId, String token, String userType, String projectId, String agencyName, String shopName, String shopMobile) {
        super();
        this.userId = userId;
        this.token = token;
        this.userType = userType;
        this.projectId = projectId;
        this.agencyName = agencyName;
        this.shopName = shopName;
        this.shopMobile = shopMobile;
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

}