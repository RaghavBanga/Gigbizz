package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectedAmazon {
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("seller_name")
    @Expose
    private String sellerName;
    @SerializedName("seller_mobile")
    @Expose
    private String sellerMobile;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("seller_email")
    @Expose
    private String sellerEmail;
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
    @SerializedName("gst_number")
    @Expose
    private String gstNumber;
    @SerializedName("gst_status")
    @Expose
    private String gstStatus;
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
    @SerializedName("reason")
    @Expose
    private Object reason;


    public RejectedAmazon(String reportId, String userId, String projectId, String userType, String shopName, String sellerName, String sellerMobile, String city, String sellerEmail, String fosName, String fosMobile, String tlName, String managerName, String gstNumber, String gstStatus, String merchantTokenNumber, String userPermissionMailId, String cosmosId, String completedSellerOnboarding, String interopAdoption, String productSharedCatalogueTeam, String priceCalculator, Object reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.userType = userType;
        this.shopName = shopName;
        this.sellerName = sellerName;
        this.sellerMobile = sellerMobile;
        this.city = city;
        this.sellerEmail = sellerEmail;
        this.fosName = fosName;
        this.fosMobile = fosMobile;
        this.tlName = tlName;
        this.managerName = managerName;
        this.gstNumber = gstNumber;
        this.gstStatus = gstStatus;
        this.merchantTokenNumber = merchantTokenNumber;
        this.userPermissionMailId = userPermissionMailId;
        this.cosmosId = cosmosId;
        this.completedSellerOnboarding = completedSellerOnboarding;
        this.interopAdoption = interopAdoption;
        this.productSharedCatalogueTeam = productSharedCatalogueTeam;
        this.priceCalculator = priceCalculator;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
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

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getGstStatus() {
        return gstStatus;
    }

    public void setGstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
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

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }
}
