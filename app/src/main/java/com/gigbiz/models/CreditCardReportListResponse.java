package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditCardReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<SubmittedCredit> submitted;
    @SerializedName("approved")
    @Expose
    private List<ApprovedCredit> approved;
    @SerializedName("rejected")
    @Expose
    private List<RejectedCredit> rejected;


    public CreditCardReportListResponse(List<SubmittedCredit> submitted, List<ApprovedCredit> approved, List<RejectedCredit> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedCredit> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedCredit> submitted) {
        this.submitted = submitted;
    }

    public List<ApprovedCredit> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedCredit> approved) {
        this.approved = approved;
    }

    public List<RejectedCredit> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedCredit> rejected) {
        this.rejected = rejected;
    }

}
