package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodWorkerFirstResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("gw_type")
    @Expose
    private String gwType;
    @SerializedName("report_status")
    @Expose
    private String reportStatus;

    public GoodWorkerFirstResponse(int status, String msg, String reportId, String gwType, String reportStatus) {
        super();
        this.status = status;
        this.msg = msg;
        this.reportId = reportId;
        this.gwType = gwType;
        this.reportStatus = reportStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getGwType() {
        return gwType;
    }

    public void setGwType(String gwType) {
        this.gwType = gwType;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

}