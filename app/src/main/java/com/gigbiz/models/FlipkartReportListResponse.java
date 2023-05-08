package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlipkartReportListResponse {
    @SerializedName("submitted")
    @Expose
    private List<SubmittedFlipkart> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedFlipkart> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedFlipkart> rejected = null;

    public FlipkartReportListResponse(List<SubmittedFlipkart> submitted, List<ApprovedFlipkart> approved, List<RejectedFlipkart> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedFlipkart> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedFlipkart> submitted) {
        this.submitted = submitted;
    }

    public FlipkartReportListResponse withSubmitted(List<SubmittedFlipkart> submitted) {
        this.submitted = submitted;
        return this;
    }

    public List<ApprovedFlipkart> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedFlipkart> approved) {
        this.approved = approved;
    }

    public FlipkartReportListResponse withApproved(List<ApprovedFlipkart> approved) {
        this.approved = approved;
        return this;
    }

    public List<RejectedFlipkart> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedFlipkart> rejected) {
        this.rejected = rejected;
    }

    public FlipkartReportListResponse withRejected(List<RejectedFlipkart> rejected) {
        this.rejected = rejected;
        return this;
    }
}
