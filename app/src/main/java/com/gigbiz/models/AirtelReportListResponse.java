package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AirtelReportListResponse {
    @SerializedName("submitted")
    @Expose
    private List<SubmittedAirtel> submitted = null;
    @SerializedName("approved")
    @Expose
    private List<ApprovedAirtel> approved = null;
    @SerializedName("rejected")
    @Expose
    private List<RejectedAirtel> rejected = null;

    public AirtelReportListResponse(List<SubmittedAirtel> submitted, List<ApprovedAirtel> approved, List<RejectedAirtel> rejected) {
        super();
        this.submitted = submitted;
        this.approved = approved;
        this.rejected = rejected;
    }

    public List<SubmittedAirtel> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<SubmittedAirtel> submitted) {
        this.submitted = submitted;
    }

    public AirtelReportListResponse withSubmitted(List<SubmittedAirtel> submitted) {
        this.submitted = submitted;
        return this;
    }

    public List<ApprovedAirtel> getApproved() {
        return approved;
    }

    public void setApproved(List<ApprovedAirtel> approved) {
        this.approved = approved;
    }

    public AirtelReportListResponse withApproved(List<ApprovedAirtel> approved) {
        this.approved = approved;
        return this;
    }

    public List<RejectedAirtel> getRejected() {
        return rejected;
    }

    public void setRejected(List<RejectedAirtel> rejected) {
        this.rejected = rejected;
    }

    public AirtelReportListResponse withRejected(List<RejectedAirtel> rejected) {
        this.rejected = rejected;
        return this;
    }

}
