package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayoutReportResponse {

    @SerializedName("project_report")
    @Expose
    private List<Project_Report> project_report = null;

    public PayoutReportResponse(List<Project_Report> project_report) {
        this.project_report = project_report;
    }

    public List<Project_Report> getProject_report() {
        return project_report;
    }

    public void setProject_report(List<Project_Report> project_report) {
        this.project_report = project_report;
    }
}
