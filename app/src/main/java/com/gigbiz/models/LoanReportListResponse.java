package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<SubmittedLoan> submitted;
    @SerializedName("approved")
    @Expose
    private List<ApprovedLoan> approved;
    @SerializedName("rejected")
    @Expose
    private List<RejectedLoan> rejected;


    public LoanReportListResponse(List<SubmittedLoan> submitted, List<ApprovedLoan> approved, List<RejectedLoan> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedLoan> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedLoan> submitted) {
        this.submitted = submitted;
    }

    public List<ApprovedLoan> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedLoan> approved) {
        this.approved = approved;
    }

    public List<RejectedLoan> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedLoan> rejected) {
        this.rejected = rejected;
    }

}