package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsuranceReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<SubmittedInsurance> submitted;
    @SerializedName("approved")
    @Expose
    private List<ApprovedInsurance> approved;
    @SerializedName("rejected")
    @Expose
    private List<RejectedInsurance> rejected;


    public InsuranceReportListResponse(List<SubmittedInsurance> submitted, List<ApprovedInsurance> approved, List<RejectedInsurance> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedInsurance> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedInsurance> submitted) {
        this.submitted = submitted;
    }

    public List<ApprovedInsurance> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedInsurance> approved) {
        this.approved = approved;
    }

    public List<RejectedInsurance> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedInsurance> rejected) {
        this.rejected = rejected;
    }

}
