package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PwReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<SubmittedPw> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedPw> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedPw> rejected = null;

    public PwReportListResponse(List<SubmittedPw> submitted, List<ApprovedPw> approved, List<RejectedPw> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedPw> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedPw> submitted) {
        this.submitted = submitted;
    }

    public List<ApprovedPw> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedPw> approved) {
        this.approved = approved;
    }

    public List<RejectedPw> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedPw> rejected) {
        this.rejected = rejected;
    }

}
