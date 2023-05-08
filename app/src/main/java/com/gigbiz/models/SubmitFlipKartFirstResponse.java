package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFlipKartFirstResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
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
    @SerializedName("report_status")
    @Expose
    private String reportStatus;

    public SubmitFlipKartFirstResponse(int status, String msg, String reportId, String shopCatid, String shopName, String supplierEmail, String supplierPassword, String supplierMobile, String supplierCity, String supplierGst, String supplierPincode, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.shopCatid = shopCatid;
        this.shopName = shopName;
        this.supplierEmail = supplierEmail;
        this.supplierPassword = supplierPassword;
        this.supplierMobile = supplierMobile;
        this.supplierCity = supplierCity;
        this.supplierGst = supplierGst;
        this.supplierPincode = supplierPincode;
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

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}