package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmittedAirtel {
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
    @SerializedName("agency_name")
    @Expose
    private String agencyName;
    @SerializedName("shop_mobile")
    @Expose
    private String shopMobile;
    @SerializedName("fse_name")
    @Expose
    private String fseName;
    @SerializedName("fse_mobile")
    @Expose
    private String fseMobile;
    @SerializedName("trans_done")
    @Expose
    private String transDone;
    @SerializedName("trans_pic")
    @Expose
    private String transPic;
    @SerializedName("upload_code_pic")
    @Expose
    private String uploadCodePic;
    @SerializedName("reason")
    @Expose
    private String reason;


    public SubmittedAirtel(String reportId, String userId, String projectId, String userType, String shopName, String agencyName, String shopMobile, String fseName, String fseMobile, String transDone, String transPic, String uploadCodePic, String reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.userType = userType;
        this.shopName = shopName;
        this.agencyName = agencyName;
        this.shopMobile = shopMobile;
        this.fseName = fseName;
        this.fseMobile = fseMobile;
        this.transDone = transDone;
        this.transPic = transPic;
        this.uploadCodePic = uploadCodePic;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getFseName() {
        return fseName;
    }

    public void setFseName(String fseName) {
        this.fseName = fseName;
    }

    public String getFseMobile() {
        return fseMobile;
    }

    public void setFseMobile(String fseMobile) {
        this.fseMobile = fseMobile;
    }

    public String getTransDone() {
        return transDone;
    }

    public void setTransDone(String transDone) {
        this.transDone = transDone;
    }

    public String getTransPic() {
        return transPic;
    }

    public void setTransPic(String transPic) {
        this.transPic = transPic;
    }

    public String getUploadCodePic() {
        return uploadCodePic;
    }

    public void setUploadCodePic(String uploadCodePic) {
        this.uploadCodePic = uploadCodePic;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
