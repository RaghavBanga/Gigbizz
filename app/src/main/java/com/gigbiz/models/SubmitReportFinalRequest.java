package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitReportFinalRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("flip_product_mrp")
    @Expose
    private String flipProductMrp;
    @SerializedName("flip_product_selling_price")
    @Expose
    private String flipProductSellingPrice;
    @SerializedName("flip_product_color")
    @Expose
    private String flipProductColor;
    @SerializedName("flip_product_material")
    @Expose
    private String flipProductMaterial;
    @SerializedName("flip_product_size")
    @Expose
    private String flipProductSize;
    @SerializedName("flip_product_weight")
    @Expose
    private String flipProductWeight;
    @SerializedName("flip_product_quantity")
    @Expose
    private String flipProductQuantity;
    @SerializedName("flip_second_product_pic")
    @Expose
    private String flipSecondProductPic;
    @SerializedName("flip_second_product_name")
    @Expose
    private String flipSecondProductName;
    @SerializedName("flip_second_product_mrp")
    @Expose
    private String flipSecondProductMrp;
    @SerializedName("flip_second_product_selling_price")
    @Expose
    private String flipSecondProductSellingPrice;
    @SerializedName("flip_second_product_colour")
    @Expose
    private String flipSecondProductColour;
    @SerializedName("flip_second_product_material")
    @Expose
    private String flipSecondProductMaterial;
    @SerializedName("flip_second_product_size")
    @Expose
    private String flipSecondProductSize;
    @SerializedName("flip_second_product_weight")
    @Expose
    private String flipSecondProductWeight;
    @SerializedName("flip_second_product_quantity")
    @Expose
    private String flipSecondProductQuantity;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public SubmitReportFinalRequest(String userId, String token, String reportId, String flipProductMrp, String flipProductSellingPrice, String flipProductColor, String flipProductMaterial, String flipProductSize, String flipProductWeight, String flipProductQuantity, String flipSecondProductPic, String flipSecondProductName, String flipSecondProductMrp, String flipSecondProductSellingPrice, String flipSecondProductColour, String flipSecondProductMaterial, String flipSecondProductSize, String flipSecondProductWeight, String flipSecondProductQuantity, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.flipProductMrp = flipProductMrp;
        this.flipProductSellingPrice = flipProductSellingPrice;
        this.flipProductColor = flipProductColor;
        this.flipProductMaterial = flipProductMaterial;
        this.flipProductSize = flipProductSize;
        this.flipProductWeight = flipProductWeight;
        this.flipProductQuantity = flipProductQuantity;
        this.flipSecondProductPic = flipSecondProductPic;
        this.flipSecondProductName = flipSecondProductName;
        this.flipSecondProductMrp = flipSecondProductMrp;
        this.flipSecondProductSellingPrice = flipSecondProductSellingPrice;
        this.flipSecondProductColour = flipSecondProductColour;
        this.flipSecondProductMaterial = flipSecondProductMaterial;
        this.flipSecondProductSize = flipSecondProductSize;
        this.flipSecondProductWeight = flipSecondProductWeight;
        this.flipSecondProductQuantity = flipSecondProductQuantity;
        this.usertype = usertype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SubmitReportFinalRequest withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SubmitReportFinalRequest withToken(String token) {
        this.token = token;
        return this;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public SubmitReportFinalRequest withReportId(String reportId) {
        this.reportId = reportId;
        return this;
    }

    public String getFlipProductMrp() {
        return flipProductMrp;
    }

    public void setFlipProductMrp(String flipProductMrp) {
        this.flipProductMrp = flipProductMrp;
    }

    public SubmitReportFinalRequest withFlipProductMrp(String flipProductMrp) {
        this.flipProductMrp = flipProductMrp;
        return this;
    }

    public String getFlipProductSellingPrice() {
        return flipProductSellingPrice;
    }

    public void setFlipProductSellingPrice(String flipProductSellingPrice) {
        this.flipProductSellingPrice = flipProductSellingPrice;
    }

    public SubmitReportFinalRequest withFlipProductSellingPrice(String flipProductSellingPrice) {
        this.flipProductSellingPrice = flipProductSellingPrice;
        return this;
    }

    public String getFlipProductColor() {
        return flipProductColor;
    }

    public void setFlipProductColor(String flipProductColor) {
        this.flipProductColor = flipProductColor;
    }

    public SubmitReportFinalRequest withFlipProductColor(String flipProductColor) {
        this.flipProductColor = flipProductColor;
        return this;
    }

    public String getFlipProductMaterial() {
        return flipProductMaterial;
    }

    public void setFlipProductMaterial(String flipProductMaterial) {
        this.flipProductMaterial = flipProductMaterial;
    }

    public SubmitReportFinalRequest withFlipProductMaterial(String flipProductMaterial) {
        this.flipProductMaterial = flipProductMaterial;
        return this;
    }

    public String getFlipProductSize() {
        return flipProductSize;
    }

    public void setFlipProductSize(String flipProductSize) {
        this.flipProductSize = flipProductSize;
    }

    public SubmitReportFinalRequest withFlipProductSize(String flipProductSize) {
        this.flipProductSize = flipProductSize;
        return this;
    }

    public String getFlipProductWeight() {
        return flipProductWeight;
    }

    public void setFlipProductWeight(String flipProductWeight) {
        this.flipProductWeight = flipProductWeight;
    }

    public SubmitReportFinalRequest withFlipProductWeight(String flipProductWeight) {
        this.flipProductWeight = flipProductWeight;
        return this;
    }

    public String getFlipProductQuantity() {
        return flipProductQuantity;
    }

    public void setFlipProductQuantity(String flipProductQuantity) {
        this.flipProductQuantity = flipProductQuantity;
    }

    public SubmitReportFinalRequest withFlipProductQuantity(String flipProductQuantity) {
        this.flipProductQuantity = flipProductQuantity;
        return this;
    }

    public String getFlipSecondProductPic() {
        return flipSecondProductPic;
    }

    public void setFlipSecondProductPic(String flipSecondProductPic) {
        this.flipSecondProductPic = flipSecondProductPic;
    }

    public SubmitReportFinalRequest withFlipSecondProductPic(String flipSecondProductPic) {
        this.flipSecondProductPic = flipSecondProductPic;
        return this;
    }

    public String getFlipSecondProductName() {
        return flipSecondProductName;
    }

    public void setFlipSecondProductName(String flipSecondProductName) {
        this.flipSecondProductName = flipSecondProductName;
    }

    public SubmitReportFinalRequest withFlipSecondProductName(String flipSecondProductName) {
        this.flipSecondProductName = flipSecondProductName;
        return this;
    }

    public String getFlipSecondProductMrp() {
        return flipSecondProductMrp;
    }

    public void setFlipSecondProductMrp(String flipSecondProductMrp) {
        this.flipSecondProductMrp = flipSecondProductMrp;
    }

    public SubmitReportFinalRequest withFlipSecondProductMrp(String flipSecondProductMrp) {
        this.flipSecondProductMrp = flipSecondProductMrp;
        return this;
    }

    public String getFlipSecondProductSellingPrice() {
        return flipSecondProductSellingPrice;
    }

    public void setFlipSecondProductSellingPrice(String flipSecondProductSellingPrice) {
        this.flipSecondProductSellingPrice = flipSecondProductSellingPrice;
    }

    public SubmitReportFinalRequest withFlipSecondProductSellingPrice(String flipSecondProductSellingPrice) {
        this.flipSecondProductSellingPrice = flipSecondProductSellingPrice;
        return this;
    }

    public String getFlipSecondProductColour() {
        return flipSecondProductColour;
    }

    public void setFlipSecondProductColour(String flipSecondProductColour) {
        this.flipSecondProductColour = flipSecondProductColour;
    }

    public SubmitReportFinalRequest withFlipSecondProductColour(String flipSecondProductColour) {
        this.flipSecondProductColour = flipSecondProductColour;
        return this;
    }

    public String getFlipSecondProductMaterial() {
        return flipSecondProductMaterial;
    }

    public void setFlipSecondProductMaterial(String flipSecondProductMaterial) {
        this.flipSecondProductMaterial = flipSecondProductMaterial;
    }

    public SubmitReportFinalRequest withFlipSecondProductMaterial(String flipSecondProductMaterial) {
        this.flipSecondProductMaterial = flipSecondProductMaterial;
        return this;
    }

    public String getFlipSecondProductSize() {
        return flipSecondProductSize;
    }

    public void setFlipSecondProductSize(String flipSecondProductSize) {
        this.flipSecondProductSize = flipSecondProductSize;
    }

    public SubmitReportFinalRequest withFlipSecondProductSize(String flipSecondProductSize) {
        this.flipSecondProductSize = flipSecondProductSize;
        return this;
    }

    public String getFlipSecondProductWeight() {
        return flipSecondProductWeight;
    }

    public void setFlipSecondProductWeight(String flipSecondProductWeight) {
        this.flipSecondProductWeight = flipSecondProductWeight;
    }

    public SubmitReportFinalRequest withFlipSecondProductWeight(String flipSecondProductWeight) {
        this.flipSecondProductWeight = flipSecondProductWeight;
        return this;
    }

    public String getFlipSecondProductQuantity() {
        return flipSecondProductQuantity;
    }

    public void setFlipSecondProductQuantity(String flipSecondProductQuantity) {
        this.flipSecondProductQuantity = flipSecondProductQuantity;
    }

    public SubmitReportFinalRequest withFlipSecondProductQuantity(String flipSecondProductQuantity) {
        this.flipSecondProductQuantity = flipSecondProductQuantity;
        return this;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public SubmitReportFinalRequest withUsertype(String usertype) {
        this.usertype = usertype;
        return this;
    }

}