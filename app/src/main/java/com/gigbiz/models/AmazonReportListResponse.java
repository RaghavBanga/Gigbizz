package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AmazonReportListResponse {
    @SerializedName("submitted")
    @Expose
    private List<SubmittedAmazon> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedAmazon> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedAmazon> rejected = null;

    public AmazonReportListResponse(List<SubmittedAmazon> submitted, List<ApprovedAmazon> approved, List<RejectedAmazon> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedAmazon> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedAmazon> submitted) {
        this.submitted = submitted;
    }

    public AmazonReportListResponse withSubmitted(List<SubmittedAmazon> submitted) {
        this.submitted = submitted;
        return this;
    }

    public List<ApprovedAmazon> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedAmazon> approved) {
        this.approved = approved;
    }

    public AmazonReportListResponse withApproved(List<ApprovedAmazon> approved) {
        this.approved = approved;
        return this;
    }

    public List<RejectedAmazon> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedAmazon> rejected) {
        this.rejected = rejected;
    }

    public AmazonReportListResponse withRejected(List<RejectedAmazon> rejected) {
        this.rejected = rejected;
        return this;
    }
}
