package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectedFlipkart {
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("shop_catid")
    @Expose
    private String shopCatid;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("supplier_email")
    @Expose
    private String supplierEmail;
    @SerializedName("supplier_password")
    @Expose
    private String supplierPassword;
    @SerializedName("supplier_mobile")
    @Expose
    private String supplierMobile;
    @SerializedName("supplier_city")
    @Expose
    private String supplierCity;
    @SerializedName("supplier_gst")
    @Expose
    private String supplierGst;
    @SerializedName("supplier_pincode")
    @Expose
    private String supplierPincode;
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
    @SerializedName("reason")
    @Expose
    private String reason;

    public RejectedFlipkart(String reportId, String userId, String projectId, String shopCatid, String shopName, String supplierEmail, String supplierPassword, String supplierMobile, String supplierCity, String supplierGst, String supplierPincode, String bdeName, String bdeMobileno, String tlName, String managerName, String bdeEmail, String productSharedCatalogue, String brandCertificates, String registrationScreenshot, String bankVerificationScreenshot, String storePic, String collectionFees, String fixedFees, String shippingFees, String sellerInterestedToSellProduct, String reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.shopCatid = shopCatid;
        this.shopName = shopName;
        this.supplierEmail = supplierEmail;
        this.supplierPassword = supplierPassword;
        this.supplierMobile = supplierMobile;
        this.supplierCity = supplierCity;
        this.supplierGst = supplierGst;
        this.supplierPincode = supplierPincode;
        this.bdeName = bdeName;
        this.bdeMobileno = bdeMobileno;
        this.tlName = tlName;
        this.managerName = managerName;
        this.bdeEmail = bdeEmail;
        this.productSharedCatalogue = productSharedCatalogue;
        this.brandCertificates = brandCertificates;
        this.registrationScreenshot = registrationScreenshot;
        this.bankVerificationScreenshot = bankVerificationScreenshot;
        this.storePic = storePic;
        this.collectionFees = collectionFees;
        this.fixedFees = fixedFees;
        this.shippingFees = shippingFees;
        this.sellerInterestedToSellProduct = sellerInterestedToSellProduct;
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

    public String getShopCatid() {
        return shopCatid;
    }

    public void setShopCatid(String shopCatid) {
        this.shopCatid = shopCatid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierPassword() {
        return supplierPassword;
    }

    public void setSupplierPassword(String supplierPassword) {
        this.supplierPassword = supplierPassword;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierGst() {
        return supplierGst;
    }

    public void setSupplierGst(String supplierGst) {
        this.supplierGst = supplierGst;
    }

    public String getSupplierPincode() {
        return supplierPincode;
    }

    public void setSupplierPincode(String supplierPincode) {
        this.supplierPincode = supplierPincode;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
