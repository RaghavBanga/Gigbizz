package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApprovedLoan {
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("aadhar_front")
    @Expose
    private String aadharFront;
    @SerializedName("aadhar_back")
    @Expose
    private String aadharBack;
    @SerializedName("pan_card")
    @Expose
    private String panCard;
    @SerializedName("sixmonth_bank_statement")
    @Expose
    private String sixmonthBankStatement;
    @SerializedName("sixmonth_salary_slip")
    @Expose
    private String sixmonthSalarySlip;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reason")
    @Expose
    private String reason;

    public ApprovedLoan(String reportId, String userId, String projectId, String name, String email, String mobile, String aadharFront, String aadharBack, String panCard, String sixmonthBankStatement, String sixmonthSalarySlip, String status, String reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.aadharFront = aadharFront;
        this.aadharBack = aadharBack;
        this.panCard = panCard;
        this.sixmonthBankStatement = sixmonthBankStatement;
        this.sixmonthSalarySlip = sixmonthSalarySlip;
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadharFront() {
        return aadharFront;
    }

    public void setAadharFront(String aadharFront) {
        this.aadharFront = aadharFront;
    }

    public String getAadharBack() {
        return aadharBack;
    }

    public void setAadharBack(String aadharBack) {
        this.aadharBack = aadharBack;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getSixmonthBankStatement() {
        return sixmonthBankStatement;
    }

    public void setSixmonthBankStatement(String sixmonthBankStatement) {
        this.sixmonthBankStatement = sixmonthBankStatement;
    }

    public String getSixmonthSalarySlip() {
        return sixmonthSalarySlip;
    }

    public void setSixmonthSalarySlip(String sixmonthSalarySlip) {
        this.sixmonthSalarySlip = sixmonthSalarySlip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}