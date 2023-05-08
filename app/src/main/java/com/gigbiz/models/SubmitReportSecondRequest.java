package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReportSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("qrcode_primary")
    @Expose
    private String qrcodePrimary;
    @SerializedName("qrcode_secondary")
    @Expose
    private String qrcodeSecondary;
    @SerializedName("tent_card")
    @Expose
    private String tentCard;
    @SerializedName("small_stricker")
    @Expose
    private String smallStricker;
    @SerializedName("large_stricker")
    @Expose
    private String largeStricker;
    @SerializedName("hanging_collectral")
    @Expose
    private String hangingCollectral;
    @SerializedName("usertype")
    @Expose
    private String usertype;


    public SubmitReportSecondRequest(String userId, String token, String reportId, String qrcodePrimary, String qrcodeSecondary, String tentCard, String smallStricker, String largeStricker, String hangingCollectral, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.qrcodePrimary = qrcodePrimary;
        this.qrcodeSecondary = qrcodeSecondary;
        this.tentCard = tentCard;
        this.smallStricker = smallStricker;
        this.largeStricker = largeStricker;
        this.hangingCollectral = hangingCollectral;
        this.usertype = usertype;
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

    public String getQrcodePrimary() {
        return qrcodePrimary;
    }

    public void setQrcodePrimary(String qrcodePrimary) {
        this.qrcodePrimary = qrcodePrimary;
    }

    public String getQrcodeSecondary() {
        return qrcodeSecondary;
    }

    public void setQrcodeSecondary(String qrcodeSecondary) {
        this.qrcodeSecondary = qrcodeSecondary;
    }

    public String getTentCard() {
        return tentCard;
    }

    public void setTentCard(String tentCard) {
        this.tentCard = tentCard;
    }

    public String getSmallStricker() {
        return smallStricker;
    }

    public void setSmallStricker(String smallStricker) {
        this.smallStricker = smallStricker;
    }

    public String getLargeStricker() {
        return largeStricker;
    }

    public void setLargeStricker(String largeStricker) {
        this.largeStricker = largeStricker;
    }

    public String getHangingCollectral() {
        return hangingCollectral;
    }

    public void setHangingCollectral(String hangingCollectral) {
        this.hangingCollectral = hangingCollectral;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}