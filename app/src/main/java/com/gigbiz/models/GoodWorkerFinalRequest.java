package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodWorkerFinalRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("report_id")
    @Expose
    private String reportId;
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
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public GoodWorkerFinalRequest(String userId, String token, String reportId, String gwFseName, String gwFseMobile, String gwTeamleaderName, String gwEmpName, String gwEmpCode, String gwEmpMobile, String gwCompanyName, String gwHrName, String gwHrRegisterName, String usertype) {
        super();
        this.userId = userId;
        this.token = token;
        this.reportId = reportId;
        this.gwFseName = gwFseName;
        this.gwFseMobile = gwFseMobile;
        this.gwTeamleaderName = gwTeamleaderName;
        this.gwEmpName = gwEmpName;
        this.gwEmpCode = gwEmpCode;
        this.gwEmpMobile = gwEmpMobile;
        this.gwCompanyName = gwCompanyName;
        this.gwHrName = gwHrName;
        this.gwHrRegisterName = gwHrRegisterName;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}