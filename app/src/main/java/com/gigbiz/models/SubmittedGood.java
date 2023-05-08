package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmittedGood {

    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("gw_type")
    @Expose
    private String gwType;
    @SerializedName("gw_fse_name")
    @Expose
    private String gwFseName;
    @SerializedName("gw_fse_mobile")
    @Expose
    private String gwFseMobile;
    @SerializedName("gw_teamleader_name")
    @Expose
    private String gwTeamleaderName;
    @SerializedName("gw_emp_name")
    @Expose
    private String gwEmpName;
    @SerializedName("gw_emp_code")
    @Expose
    private String gwEmpCode;
    @SerializedName("gw_emp_mobile")
    @Expose
    private String gwEmpMobile;
    @SerializedName("gw_company_name")
    @Expose
    private String gwCompanyName;
    @SerializedName("gw_hr_name")
    @Expose
    private String gwHrName;
    @SerializedName("gw_hr_register_name")
    @Expose
    private String gwHrRegisterName;
    @SerializedName("gw_applied_job_screenshot")
    @Expose
    private String gwAppliedJobScreenshot;
    @SerializedName("gw_selfie")
    @Expose
    private String gwSelfie;
    @SerializedName("gw_upload_document")
    @Expose
    private String gwUploadDocument;
    @SerializedName("gw_hiring_post_screenshot")
    @Expose
    private String gwHiringPostScreenshot;
    @SerializedName("comments")
    @Expose
    private Object comments;
    @SerializedName("reason")
    @Expose
    private Object reason;

    public SubmittedGood(String reportId, String userId, String projectId, String gwType, String gwFseName, String gwFseMobile, String gwTeamleaderName, String gwEmpName, String gwEmpCode, String gwEmpMobile, String gwCompanyName, String gwHrName, String gwHrRegisterName, String gwAppliedJobScreenshot, String gwSelfie, String gwUploadDocument, String gwHiringPostScreenshot, Object comments, Object reason) {
        super();
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.gwType = gwType;
        this.gwFseName = gwFseName;
        this.gwFseMobile = gwFseMobile;
        this.gwTeamleaderName = gwTeamleaderName;
        this.gwEmpName = gwEmpName;
        this.gwEmpCode = gwEmpCode;
        this.gwEmpMobile = gwEmpMobile;
        this.gwCompanyName = gwCompanyName;
        this.gwHrName = gwHrName;
        this.gwHrRegisterName = gwHrRegisterName;
        this.gwAppliedJobScreenshot = gwAppliedJobScreenshot;
        this.gwSelfie = gwSelfie;
        this.gwUploadDocument = gwUploadDocument;
        this.gwHiringPostScreenshot = gwHiringPostScreenshot;
        this.comments = comments;
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

    public String getGwType() {
        return gwType;
    }

    public void setGwType(String gwType) {
        this.gwType = gwType;
    }

    public String getGwFseName() {
        return gwFseName;
    }

    public void setGwFseName(String gwFseName) {
        this.gwFseName = gwFseName;
    }

    public String getGwFseMobile() {
        return gwFseMobile;
    }

    public void setGwFseMobile(String gwFseMobile) {
        this.gwFseMobile = gwFseMobile;
    }

    public String getGwTeamleaderName() {
        return gwTeamleaderName;
    }

    public void setGwTeamleaderName(String gwTeamleaderName) {
        this.gwTeamleaderName = gwTeamleaderName;
    }

    public String getGwEmpName() {
        return gwEmpName;
    }

    public void setGwEmpName(String gwEmpName) {
        this.gwEmpName = gwEmpName;
    }

    public String getGwEmpCode() {
        return gwEmpCode;
    }

    public void setGwEmpCode(String gwEmpCode) {
        this.gwEmpCode = gwEmpCode;
    }

    public String getGwEmpMobile() {
        return gwEmpMobile;
    }

    public void setGwEmpMobile(String gwEmpMobile) {
        this.gwEmpMobile = gwEmpMobile;
    }

    public String getGwCompanyName() {
        return gwCompanyName;
    }

    public void setGwCompanyName(String gwCompanyName) {
        this.gwCompanyName = gwCompanyName;
    }

    public String getGwHrName() {
        return gwHrName;
    }

    public void setGwHrName(String gwHrName) {
        this.gwHrName = gwHrName;
    }

    public String getGwHrRegisterName() {
        return gwHrRegisterName;
    }

    public void setGwHrRegisterName(String gwHrRegisterName) {
        this.gwHrRegisterName = gwHrRegisterName;
    }

    public String getGwAppliedJobScreenshot() {
        return gwAppliedJobScreenshot;
    }

    public void setGwAppliedJobScreenshot(String gwAppliedJobScreenshot) {
        this.gwAppliedJobScreenshot = gwAppliedJobScreenshot;
    }

    public String getGwSelfie() {
        return gwSelfie;
    }

    public void setGwSelfie(String gwSelfie) {
        this.gwSelfie = gwSelfie;
    }

    public String getGwUploadDocument() {
        return gwUploadDocument;
    }

    public void setGwUploadDocument(String gwUploadDocument) {
        this.gwUploadDocument = gwUploadDocument;
    }

    public String getGwHiringPostScreenshot() {
        return gwHiringPostScreenshot;
    }

    public void setGwHiringPostScreenshot(String gwHiringPostScreenshot) {
        this.gwHiringPostScreenshot = gwHiringPostScreenshot;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

}
