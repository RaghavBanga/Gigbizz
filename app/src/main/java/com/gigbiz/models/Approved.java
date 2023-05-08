package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Approved {

    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("shop_no")
    @Expose
    private String shopNo;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("shop_phone")
    @Expose
    private String shopPhone;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("qrcode_id")
    @Expose
    private String qrcodeId;
    @SerializedName("qrcode_name")
    @Expose
    private String qrcodeName;
    @SerializedName("qrcode_pic_primary")
    @Expose
    private String qrcodePicPrimary;
    @SerializedName("qrcode_pic_secondary")
    @Expose
    private String qrcodePicSecondary;
    @SerializedName("tent_card_pic")
    @Expose
    private String tentCardPic;
    @SerializedName("small_sticker_pic")
    @Expose
    private String smallStickerPic;
    @SerializedName("large_sticker_pic")
    @Expose
    private String largeStickerPic;
    @SerializedName("hanging_collectral_pic")
    @Expose
    private String hangingCollectralPic;
    @SerializedName("no_of_tent_card")
    @Expose
    private String noOfTentCard;
    @SerializedName("no_of_afour_poster")
    @Expose
    private String noOfAfourPoster;
    @SerializedName("no_of_small_sticker")
    @Expose
    private String noOfSmallSticker;
    @SerializedName("no_of_traditional_dangler")
    @Expose
    private String noOfTraditionalDangler;
    @SerializedName("no_of_bunting")
    @Expose
    private String noOfBunting;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("reason")
    @Expose
    private Object reason;


    public Approved(String reportId, String userId, String projectId, String shopNo, String shopName, String shopPhone, String catId, String catName, String city, String pincode, String qrcodeId, String qrcodeName, String qrcodePicPrimary, String qrcodePicSecondary, String tentCardPic, String smallStickerPic, String largeStickerPic, String hangingCollectralPic, String noOfTentCard, String noOfAfourPoster, String noOfSmallSticker, String noOfTraditionalDangler, String noOfBunting, String comments, Object reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.shopNo = shopNo;
        this.shopName = shopName;
        this.shopPhone = shopPhone;
        this.catId = catId;
        this.catName = catName;
        this.city = city;
        this.pincode = pincode;
        this.qrcodeId = qrcodeId;
        this.qrcodeName = qrcodeName;
        this.qrcodePicPrimary = qrcodePicPrimary;
        this.qrcodePicSecondary = qrcodePicSecondary;
        this.tentCardPic = tentCardPic;
        this.smallStickerPic = smallStickerPic;
        this.largeStickerPic = largeStickerPic;
        this.hangingCollectralPic = hangingCollectralPic;
        this.noOfTentCard = noOfTentCard;
        this.noOfAfourPoster = noOfAfourPoster;
        this.noOfSmallSticker = noOfSmallSticker;
        this.noOfTraditionalDangler = noOfTraditionalDangler;
        this.noOfBunting = noOfBunting;
        this.comments = comments;
        this.reason = reason;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Approved withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Approved withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Approved withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public Approved withShopNo(String shopNo) {
        this.shopNo = shopNo;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Approved withShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public Approved withShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
        return this;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Approved withCatId(String catId) {
        this.catId = catId;
        return this;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Approved withCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Approved withCity(String city) {
        this.city = city;
        return this;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Approved withPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public Approved withQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
        return this;
    }

    public String getQrcodeName() {
        return qrcodeName;
    }

    public void setQrcodeName(String qrcodeName) {
        this.qrcodeName = qrcodeName;
    }

    public Approved withQrcodeName(String qrcodeName) {
        this.qrcodeName = qrcodeName;
        return this;
    }

    public String getQrcodePicPrimary() {
        return qrcodePicPrimary;
    }

    public void setQrcodePicPrimary(String qrcodePicPrimary) {
        this.qrcodePicPrimary = qrcodePicPrimary;
    }

    public Approved withQrcodePicPrimary(String qrcodePicPrimary) {
        this.qrcodePicPrimary = qrcodePicPrimary;
        return this;
    }

    public String getQrcodePicSecondary() {
        return qrcodePicSecondary;
    }

    public void setQrcodePicSecondary(String qrcodePicSecondary) {
        this.qrcodePicSecondary = qrcodePicSecondary;
    }

    public Approved withQrcodePicSecondary(String qrcodePicSecondary) {
        this.qrcodePicSecondary = qrcodePicSecondary;
        return this;
    }

    public String getTentCardPic() {
        return tentCardPic;
    }

    public void setTentCardPic(String tentCardPic) {
        this.tentCardPic = tentCardPic;
    }

    public Approved withTentCardPic(String tentCardPic) {
        this.tentCardPic = tentCardPic;
        return this;
    }

    public String getSmallStickerPic() {
        return smallStickerPic;
    }

    public void setSmallStickerPic(String smallStickerPic) {
        this.smallStickerPic = smallStickerPic;
    }

    public Approved withSmallStickerPic(String smallStickerPic) {
        this.smallStickerPic = smallStickerPic;
        return this;
    }

    public String getLargeStickerPic() {
        return largeStickerPic;
    }

    public void setLargeStickerPic(String largeStickerPic) {
        this.largeStickerPic = largeStickerPic;
    }

    public Approved withLargeStickerPic(String largeStickerPic) {
        this.largeStickerPic = largeStickerPic;
        return this;
    }

    public String getHangingCollectralPic() {
        return hangingCollectralPic;
    }

    public void setHangingCollectralPic(String hangingCollectralPic) {
        this.hangingCollectralPic = hangingCollectralPic;
    }

    public Approved withHangingCollectralPic(String hangingCollectralPic) {
        this.hangingCollectralPic = hangingCollectralPic;
        return this;
    }

    public String getNoOfTentCard() {
        return noOfTentCard;
    }

    public void setNoOfTentCard(String noOfTentCard) {
        this.noOfTentCard = noOfTentCard;
    }

    public Approved withNoOfTentCard(String noOfTentCard) {
        this.noOfTentCard = noOfTentCard;
        return this;
    }

    public String getNoOfAfourPoster() {
        return noOfAfourPoster;
    }

    public void setNoOfAfourPoster(String noOfAfourPoster) {
        this.noOfAfourPoster = noOfAfourPoster;
    }

    public Approved withNoOfAfourPoster(String noOfAfourPoster) {
        this.noOfAfourPoster = noOfAfourPoster;
        return this;
    }

    public String getNoOfSmallSticker() {
        return noOfSmallSticker;
    }

    public void setNoOfSmallSticker(String noOfSmallSticker) {
        this.noOfSmallSticker = noOfSmallSticker;
    }

    public Approved withNoOfSmallSticker(String noOfSmallSticker) {
        this.noOfSmallSticker = noOfSmallSticker;
        return this;
    }

    public String getNoOfTraditionalDangler() {
        return noOfTraditionalDangler;
    }

    public void setNoOfTraditionalDangler(String noOfTraditionalDangler) {
        this.noOfTraditionalDangler = noOfTraditionalDangler;
    }

    public Approved withNoOfTraditionalDangler(String noOfTraditionalDangler) {
        this.noOfTraditionalDangler = noOfTraditionalDangler;
        return this;
    }

    public String getNoOfBunting() {
        return noOfBunting;
    }

    public void setNoOfBunting(String noOfBunting) {
        this.noOfBunting = noOfBunting;
    }

    public Approved withNoOfBunting(String noOfBunting) {
        this.noOfBunting = noOfBunting;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Approved withComments(String comments) {
        this.comments = comments;
        return this;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Approved withReason(Object reason) {
        this.reason = reason;
        return this;
    }

}