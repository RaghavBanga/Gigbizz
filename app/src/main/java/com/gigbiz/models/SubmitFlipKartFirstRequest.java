package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFlipKartFirstRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("shop_catid")
    @Expose
    private String shopCatid;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("supplier_email")
    @Expose
    private String supplierEmail;
    @SerializedName("supplier_password")
    @Expose
    private String supplierPassword;
    @SerializedName("supplier_mobile")
    @Expose
    private String supplierMobile;
    @SerializedName("supplier_city")
    @Expose
    private String supplierCity;
    @SerializedName("supplier_gst")
    @Expose
    private String supplierGst;
    @SerializedName("supplier_pincode")
    @Expose
    private String supplierPincode;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitFlipKartFirstRequest(String userId, String token, String projectId, String shopCatid, String shopName, String supplierEmail, String supplierPassword, String supplierMobile, String supplierCity, String supplierGst, String supplierPincode, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.projectId = projectId;
        this.shopCatid = shopCatid;
        this.shopName = shopName;
        this.supplierEmail = supplierEmail;
        this.supplierPassword = supplierPassword;
        this.supplierMobile = supplierMobile;
        this.supplierCity = supplierCity;
        this.supplierGst = supplierGst;
        this.supplierPincode = supplierPincode;
        this.userType = userType;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getShopCatid() {
        return shopCatid;
    }

    public void setShopCatid(String shopCatid) {
        this.shopCatid = shopCatid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPassword() {
        return supplierPassword;
    }

    public void setSupplierPassword(String supplierPassword) {
        this.supplierPassword = supplierPassword;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(String supplierGst) {
        this.supplierGst = supplierGst;
    }

    public String getSupplierPincode() {
        return supplierPincode;
    }

    public void setSupplierPincode(String supplierPincode) {
        this.supplierPincode = supplierPincode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}