package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<Submitted> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<Approved> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<Rejected> rejected = null;

    public ReportListResponse(List<Submitted> submitted, List<Approved> approved, List<Rejected> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<Submitted> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<Submitted> submitted) {
        this.submitted = submitted;
    }

    public ReportListResponse withSubmitted(List<Submitted> submitted) {
        this.submitted = submitted;
        return this;
    }

    public List<Approved> getApproved() {
        return approved;
    }

    public void setApproved(List<Approved> approved) {
        this.approved = approved;
    }

    public ReportListResponse withApproved(List<Approved> approved) {
        this.approved = approved;
        return this;
    }

    public List<Rejected> getRejected() {
        return rejected;
    }

    public void setRejected(List<Rejected> rejected) {
        this.rejected = rejected;
    }

    public ReportListResponse withRejected(List<Rejected> rejected) {
        this.rejected = rejected;
        return this;
    }

}