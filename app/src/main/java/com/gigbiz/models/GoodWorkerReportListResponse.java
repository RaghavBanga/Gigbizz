package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodWorkerReportListResponse {

    @SerializedName("submitted")
    @Expose
    private List<SubmittedGood> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedGood> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedGood> rejected = null;

    public GoodWorkerReportListResponse(List<SubmittedGood> submitted, List<ApprovedGood> approved, List<RejectedGood> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedGood> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedGood> submitted) {
        this.submitted = submitted;
    }


    public List<ApprovedGood> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedGood> approved) {
        this.approved = approved;
    }


    public List<RejectedGood> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedGood> rejected) {
        this.rejected = rejected;
    }

}