package com.gigbiz.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmittedFydo {
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
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
    @SerializedName("fse_name")
    @Expose
    private String fseName;
    @SerializedName("fse_mobile")
    @Expose
    private String fseMobile;
    @SerializedName("tl_name")
    @Expose
    private String tlName;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_mobile")
    @Expose
    private String customerMobile;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("shop_pic")
    @Expose
    private String shopPic;
    @SerializedName("store_front_pic")
    @Expose
    private String storeFrontPic;
    @SerializedName("bank_details_pic")
    @Expose
    private String bankDetailsPic;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("reason")
    @Expose
    private Object reason;

    public SubmittedFydo(String reportId, String userId, String projectId, String reportSubmittedFor, String shopName, String shopOwnerName, String shopMobile, String shopCategory, String fseName, String fseMobile, String tlName, String customerName, String customerMobile, String profilePic, String shopPic, String storeFrontPic, String bankDetailsPic, String city, Object reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.reportSubmittedFor = reportSubmittedFor;
        this.shopName = shopName;
        this.shopOwnerName = shopOwnerName;
        this.shopMobile = shopMobile;
        this.shopCategory = shopCategory;
        this.fseName = fseName;
        this.fseMobile = fseMobile;
        this.tlName = tlName;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.profilePic = profilePic;
        this.shopPic = shopPic;
        this.storeFrontPic = storeFrontPic;
        this.bankDetailsPic = bankDetailsPic;
        this.city = city;
        this.reason = reason;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getFseName() {
        return fseName;
    }

    public void setFseName(String fseName) {
        this.fseName = fseName;
    }

    public String getFseMobile() {
        return fseMobile;
    }

    public void setFseMobile(String fseMobile) {
        this.fseMobile = fseMobile;
    }

    public String getTlName() {
        return tlName;
    }

    public void setTlName(String tlName) {
        this.tlName = tlName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getStoreFrontPic() {
        return storeFrontPic;
    }

    public void setStoreFrontPic(String storeFrontPic) {
        this.storeFrontPic = storeFrontPic;
    }

    public String getBankDetailsPic() {
        return bankDetailsPic;
    }

    public void setBankDetailsPic(String bankDetailsPic) {
        this.bankDetailsPic = bankDetailsPic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }
}
