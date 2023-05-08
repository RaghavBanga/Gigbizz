package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FydoReportListResponse {
    @SerializedName("submitted")
    @Expose
    private List<SubmittedFydo> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedFydo> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedFydo> rejected = null;

    public FydoReportListResponse(List<SubmittedFydo> submitted, List<ApprovedFydo> approved, List<RejectedFydo> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedFydo> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedFydo> submitted) {
        this.submitted = submitted;
    }

    public FydoReportListResponse withSubmitted(List<SubmittedFydo> submitted) {
        this.submitted = submitted;
        return this;
    }

    public List<ApprovedFydo> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedFydo> approved) {
        this.approved = approved;
    }

    public FydoReportListResponse withApproved(List<ApprovedFydo> approved) {
        this.approved = approved;
        return this;
    }

    public List<RejectedFydo> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedFydo> rejected) {
        this.rejected = rejected;
    }

    public FydoReportListResponse withRejected(List<RejectedFydo> rejected) {
        this.rejected = rejected;
        return this;
    }

}
