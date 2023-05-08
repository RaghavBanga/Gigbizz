package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PwPhotoFormRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("shop_pic")
    @Expose
    private String shopPic;
    @SerializedName("shop_front_pic")
    @Expose
    private String shopFrontPic;
    @SerializedName("passbook_cheque_pic")
    @Expose
    private String passbookChequePic;
    @SerializedName("pan_card")
    @Expose
    private String panCard;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public PwPhotoFormRequest(String userId, String token, String reportId, String shopPic, String shopFrontPic, String passbookChequePic, String panCard, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.shopPic = shopPic;
        this.shopFrontPic = shopFrontPic;
        this.passbookChequePic = passbookChequePic;
        this.panCard = panCard;
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

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getShopFrontPic() {
        return shopFrontPic;
    }

    public void setShopFrontPic(String shopFrontPic) {
        this.shopFrontPic = shopFrontPic;
    }

    public String getPassbookChequePic() {
        return passbookChequePic;
    }

    public void setPassbookChequePic(String passbookChequePic) {
        this.passbookChequePic = passbookChequePic;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
