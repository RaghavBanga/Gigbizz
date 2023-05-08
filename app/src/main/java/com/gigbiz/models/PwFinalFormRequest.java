package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PwFinalFormRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("gst_no")
    @Expose
    private String gstNo;
    @SerializedName("pw_emp_email")
    @Expose
    private String pwEmpEmail;
    @SerializedName("pw_emp_name")
    @Expose
    private String pwEmpName;
    @SerializedName("pw_emp_id")
    @Expose
    private String pwEmpId;
    @SerializedName("usertype")
    @Expose
    private String usertype;



    public PwFinalFormRequest(String userId, String token, String reportId, String accountNo, String accountName, String bankName, String ifscCode, String panNo, String gstNo, String pwEmpEmail, String pwEmpName, String pwEmpId, String usertype) {
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.panNo = panNo;
        this.gstNo = gstNo;
        this.pwEmpEmail = pwEmpEmail;
        this.pwEmpName = pwEmpName;
        this.pwEmpId = pwEmpId;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPwEmpEmail() {
        return pwEmpEmail;
    }

    public void setPwEmpEmail(String pwEmpEmail) {
        this.pwEmpEmail = pwEmpEmail;
    }

    public String getPwEmpName() {
        return pwEmpName;
    }

    public void setPwEmpName(String pwEmpName) {
        this.pwEmpName = pwEmpName;
    }

    public String getPwEmpId() {
        return pwEmpId;
    }

    public void setPwEmpId(String pwEmpId) {
        this.pwEmpId = pwEmpId;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}