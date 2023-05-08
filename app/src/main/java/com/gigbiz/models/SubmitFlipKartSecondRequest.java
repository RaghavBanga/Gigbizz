package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFlipKartSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("brand_certificates")
    @Expose
    private String brandCertificates;
    @SerializedName("registration_screenshot")
    @Expose
    private String registrationScreenshot;
    @SerializedName("bank_verification_screenshot")
    @Expose
    private String bankVerificationScreenshot;
    @SerializedName("store_pic")
    @Expose
    private String storePic;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitFlipKartSecondRequest(String userId, String token, String reportId, String brandCertificates, String registrationScreenshot, String bankVerificationScreenshot, String storePic, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.brandCertificates = brandCertificates;
        this.registrationScreenshot = registrationScreenshot;
        this.bankVerificationScreenshot = bankVerificationScreenshot;
        this.storePic = storePic;
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

    public String getBrandCertificates() {
        return brandCertificates;
    }

    public void setBrandCertificates(String brandCertificates) {
        this.brandCertificates = brandCertificates;
    }

    public String getRegistrationScreenshot() {
        return registrationScreenshot;
    }

    public void setRegistrationScreenshot(String registrationScreenshot) {
        this.registrationScreenshot = registrationScreenshot;
    }

    public String getBankVerificationScreenshot() {
        return bankVerificationScreenshot;
    }

    public void setBankVerificationScreenshot(String bankVerificationScreenshot) {
        this.bankVerificationScreenshot = bankVerificationScreenshot;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}