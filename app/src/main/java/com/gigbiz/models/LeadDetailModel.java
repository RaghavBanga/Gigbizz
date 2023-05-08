package com.gigbiz.models;

public class LeadDetailModel {

    private String projectName;
    private String inprocesss;
    private String approved;
    private String rejected;
    private String totalpayout;

    public LeadDetailModel(String projectName, String inprocesss, String approved, String rejected, String totalpayout) {
        this.projectName = projectName;
        this.inprocesss = inprocesss;
        this.approved = approved;
        this.rejected = rejected;
        this.totalpayout = totalpayout;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInprocesss() {
        return inprocesss;
    }

    public void setInprocesss(String inprocesss) {
        this.inprocesss = inprocesss;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getTotalpayout() {
        return totalpayout;
    }

    public void setTotalpayout(String totalpayout) {
        this.totalpayout = totalpayout;
    }
}
