package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFirstReportRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("qrcode_id")
    @Expose
    private String qrcodeId;
    @SerializedName("usertype")
    @Expose
    private String usertype;


    public SubmitFirstReportRequest(String userId, String token, String reportId, String catId, String city, String pincode, String qrcodeId, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.catId = catId;
        this.city = city;
        this.pincode = pincode;
        this.qrcodeId = qrcodeId;
        this.usertype = usertype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SubmitFirstReportRequest withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SubmitFirstReportRequest withToken(String token) {
        this.token = token;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public SubmitFirstReportRequest withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public SubmitFirstReportRequest withCatId(String catId) {
        this.catId = catId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SubmitFirstReportRequest withCity(String city) {
        this.city = city;
        return this;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public SubmitFirstReportRequest withPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public SubmitFirstReportRequest withQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
        return this;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public SubmitFirstReportRequest withUsertype(String usertype) {
        this.usertype = usertype;
        return this;
    }

}