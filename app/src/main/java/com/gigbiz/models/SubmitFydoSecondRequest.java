package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFydoSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
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
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitFydoSecondRequest(String userId, String token, String reportId, String profilePic, String shopPic, String storeFrontPic, String bankDetailsPic, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.profilePic = profilePic;
        this.shopPic = shopPic;
        this.storeFrontPic = storeFrontPic;
        this.bankDetailsPic = bankDetailsPic;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}