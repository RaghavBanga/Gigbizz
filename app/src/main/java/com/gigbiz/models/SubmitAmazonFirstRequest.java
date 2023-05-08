package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAmazonFirstRequest {

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
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("seller_name")
    @Expose
    private String sellerName;
    @SerializedName("seller_mobile")
    @Expose
    private String sellerMobile;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("seller_email")
    @Expose
    private String sellerEmail;
    @SerializedName("gst_number")
    @Expose
    private String gstNumber;
    @SerializedName("gst_status")
    @Expose
    private String gstStatus;

    public SubmitAmazonFirstRequest(String userId, String token, String userType, String projectId, String shopName, String sellerName, String sellerMobile, String city, String sellerEmail, String gstNumber, String gstStatus) {
        super();
        this.userId = userId;
        this.token = token;
        this.userType = userType;
        this.projectId = projectId;
        this.shopName = shopName;
        this.sellerName = sellerName;
        this.sellerMobile = sellerMobile;
        this.city = city;
        this.sellerEmail = sellerEmail;
        this.gstNumber = gstNumber;
        this.gstStatus = gstStatus;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getGstStatus() {
        return gstStatus;
    }

    public void setGstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
    }

}