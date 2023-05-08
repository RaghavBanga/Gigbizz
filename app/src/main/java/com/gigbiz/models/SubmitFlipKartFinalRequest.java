package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFlipKartFinalRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("bde_name")
    @Expose
    private String bdeName;
    @SerializedName("bde_mobileno")
    @Expose
    private String bdeMobileno;
    @SerializedName("tl_name")
    @Expose
    private String tlName;
    @SerializedName("manager_name")
    @Expose
    private String managerName;
    @SerializedName("bde_email")
    @Expose
    private String bdeEmail;
    @SerializedName("product_shared_catalogue")
    @Expose
    private String productSharedCatalogue;
    @SerializedName("collection_fees")
    @Expose
    private String collectionFees;
    @SerializedName("fixed_fees")
    @Expose
    private String fixedFees;
    @SerializedName("shipping_fees")
    @Expose
    private String shippingFees;
    @SerializedName("seller_interested_to_sell_product")
    @Expose
    private String sellerInterestedToSellProduct;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitFlipKartFinalRequest(String userId, String token, String reportId, String bdeName, String bdeMobileno, String tlName, String managerName, String bdeEmail, String productSharedCatalogue, String collectionFees, String fixedFees, String shippingFees, String sellerInterestedToSellProduct, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.bdeName = bdeName;
        this.bdeMobileno = bdeMobileno;
        this.tlName = tlName;
        this.managerName = managerName;
        this.bdeEmail = bdeEmail;
        this.productSharedCatalogue = productSharedCatalogue;
        this.collectionFees = collectionFees;
        this.fixedFees = fixedFees;
        this.shippingFees = shippingFees;
        this.sellerInterestedToSellProduct = sellerInterestedToSellProduct;
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

    public String getBdeName() {
        return bdeName;
    }

    public void setBdeName(String bdeName) {
        this.bdeName = bdeName;
    }

    public String getBdeMobileno() {
        return bdeMobileno;
    }

    public void setBdeMobileno(String bdeMobileno) {
        this.bdeMobileno = bdeMobileno;
    }

    public String getTlName() {
        return tlName;
    }

    public void setTlName(String tlName) {
        this.tlName = tlName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getBdeEmail() {
        return bdeEmail;
    }

    public void setBdeEmail(String bdeEmail) {
        this.bdeEmail = bdeEmail;
    }

    public String getProductSharedCatalogue() {
        return productSharedCatalogue;
    }

    public void setProductSharedCatalogue(String productSharedCatalogue) {
        this.productSharedCatalogue = productSharedCatalogue;
    }

    public String getCollectionFees() {
        return collectionFees;
    }

    public void setCollectionFees(String collectionFees) {
        this.collectionFees = collectionFees;
    }

    public String getFixedFees() {
        return fixedFees;
    }

    public void setFixedFees(String fixedFees) {
        this.fixedFees = fixedFees;
    }

    public String getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(String shippingFees) {
        this.shippingFees = shippingFees;
    }

    public String getSellerInterestedToSellProduct() {
        return sellerInterestedToSellProduct;
    }

    public void setSellerInterestedToSellProduct(String sellerInterestedToSellProduct) {
        this.sellerInterestedToSellProduct = sellerInterestedToSellProduct;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}