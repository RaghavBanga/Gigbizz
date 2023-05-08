package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAirtelReportSecondRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("trans_pic")
    @Expose
    private String transPic;
    @SerializedName("upload_code_pic")
    @Expose
    private String uploadCodePic;
    @SerializedName("user_type")
    @Expose
    private String userType;

    public SubmitAirtelReportSecondRequest(String userId, String token, String reportId, String transPic, String uploadCodePic, String userType) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.transPic = transPic;
        this.uploadCodePic = uploadCodePic;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}