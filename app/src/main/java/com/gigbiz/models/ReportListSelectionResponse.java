package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportListSelectionResponse {

    @SerializedName("report_list")
    @Expose
    private List<ReportList> reportList = null;

    public ReportListSelectionResponse() {
    }


    public ReportListSelectionResponse(List<ReportList> reportList) {
        super();
        this.reportList = reportList;
    }

    public List<ReportList> getReportList() {
        return reportList;
    }

    public void setReportList(List<ReportList> reportList) {
        this.reportList = reportList;
    }

}
