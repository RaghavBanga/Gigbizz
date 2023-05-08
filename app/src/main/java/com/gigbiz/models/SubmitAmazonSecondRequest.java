package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAmazonSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("fos_name")
    @Expose
    private String fosName;
    @SerializedName("fos_mobile")
    @Expose
    private String fosMobile;
    @SerializedName("tl_name")
    @Expose
    private String tlName;
    @SerializedName("manager_name")
    @Expose
    private String managerName;
    @SerializedName("merchant_token_number")
    @Expose
    private String merchantTokenNumber;
    @SerializedName("user_permission_mail_id")
    @Expose
    private String userPermissionMailId;
    @SerializedName("cosmos_id")
    @Expose
    private String cosmosId;
    @SerializedName("completed_seller_onboarding")
    @Expose
    private String completedSellerOnboarding;
    @SerializedName("interop_adoption")
    @Expose
    private String interopAdoption;
    @SerializedName("product_shared_catalogue_team")
    @Expose
    private String productSharedCatalogueTeam;
    @SerializedName("price_calculator")
    @Expose
    private String priceCalculator;

    public SubmitAmazonSecondRequest(String userId, String token, String reportId, String userType, String fosName, String fosMobile, String tlName, String managerName, String merchantTokenNumber, String userPermissionMailId, String cosmosId, String completedSellerOnboarding, String interopAdoption, String productSharedCatalogueTeam, String priceCalculator) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.userType = userType;
        this.fosName = fosName;
        this.fosMobile = fosMobile;
        this.tlName = tlName;
        this.managerName = managerName;
        this.merchantTokenNumber = merchantTokenNumber;
        this.userPermissionMailId = userPermissionMailId;
        this.cosmosId = cosmosId;
        this.completedSellerOnboarding = completedSellerOnboarding;
        this.interopAdoption = interopAdoption;
        this.productSharedCatalogueTeam = productSharedCatalogueTeam;
        this.priceCalculator = priceCalculator;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFosName() {
        return fosName;
    }

    public void setFosName(String fosName) {
        this.fosName = fosName;
    }

    public String getFosMobile() {
        return fosMobile;
    }

    public void setFosMobile(String fosMobile) {
        this.fosMobile = fosMobile;
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

    public String getMerchantTokenNumber() {
        return merchantTokenNumber;
    }

    public void setMerchantTokenNumber(String merchantTokenNumber) {
        this.merchantTokenNumber = merchantTokenNumber;
    }

    public String getUserPermissionMailId() {
        return userPermissionMailId;
    }

    public void setUserPermissionMailId(String userPermissionMailId) {
        this.userPermissionMailId = userPermissionMailId;
    }

    public String getCosmosId() {
        return cosmosId;
    }

    public void setCosmosId(String cosmosId) {
        this.cosmosId = cosmosId;
    }

    public String getCompletedSellerOnboarding() {
        return completedSellerOnboarding;
    }

    public void setCompletedSellerOnboarding(String completedSellerOnboarding) {
        this.completedSellerOnboarding = completedSellerOnboarding;
    }

    public String getInteropAdoption() {
        return interopAdoption;
    }

    public void setInteropAdoption(String interopAdoption) {
        this.interopAdoption = interopAdoption;
    }

    public String getProductSharedCatalogueTeam() {
        return productSharedCatalogueTeam;
    }

    public void setProductSharedCatalogueTeam(String productSharedCatalogueTeam) {
        this.productSharedCatalogueTeam = productSharedCatalogueTeam;
    }

    public String getPriceCalculator() {
        return priceCalculator;
    }

    public void setPriceCalculator(String priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

}