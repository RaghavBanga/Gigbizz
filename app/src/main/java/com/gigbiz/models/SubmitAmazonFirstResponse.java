package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAmazonFirstResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("seller_name")
    @Expose
    private String sellerName;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("supplier_email")
    @Expose
    private Object supplierEmail;
    @SerializedName("seller_mobile")
    @Expose
    private String sellerMobile;
    @SerializedName("supplier_mobile")
    @Expose
    private Object supplierMobile;
    @SerializedName("seller_email")
    @Expose
    private String sellerEmail;
    @SerializedName("supplier_gst")
    @Expose
    private Object supplierGst;
    @SerializedName("gst_status")
    @Expose
    private String gstStatus;
    @SerializedName("report_status")
    @Expose
    private String reportStatus;

    public SubmitAmazonFirstResponse(int status, String msg, String reportId, String sellerName, String shopName, Object supplierEmail, String sellerMobile, Object supplierMobile, String sellerEmail, Object supplierGst, String gstStatus, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.sellerName = sellerName;
        this.shopName = shopName;
        this.supplierEmail = supplierEmail;
        this.sellerMobile = sellerMobile;
        this.supplierMobile = supplierMobile;
        this.sellerEmail = sellerEmail;
        this.supplierGst = supplierGst;
        this.gstStatus = gstStatus;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Object getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(Object supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public Object getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(Object supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public Object getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(Object supplierGst) {
        this.supplierGst = supplierGst;
    }

    public String getGstStatus() {
        return gstStatus;
    }

    public void setGstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}