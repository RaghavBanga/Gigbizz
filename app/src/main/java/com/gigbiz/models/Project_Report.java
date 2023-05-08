package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project_Report {


    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("project_id")
    @Expose
    private String project_id;
    @SerializedName("project_name")
    @Expose
    private String project_name;
    @SerializedName("Submitted")
    @Expose
    private int Submitted;
    @SerializedName("Not Completed")
    @Expose
    private int NotCompleted;
    @SerializedName("Approved")
    @Expose
    private int Approved;
    @SerializedName("Rejected")
    @Expose
    private int Rejected;
    @SerializedName("total_payout")
    @Expose
    private String total_payout;
    @SerializedName("paid")
    @Expose
    private String paid;
    @SerializedName("balance")
    @Expose
    private String balance;

    public Project_Report(int status, String msg, String project_id, String project_name, int submitted, int notCompleted, int approved, int rejected, String total_payout, String paid, String balance) {
        this.status = status;
        this.msg = msg;
        this.project_id = project_id;
        this.project_name = project_name;
        Submitted = submitted;
        NotCompleted = notCompleted;
        Approved = approved;
        Rejected = rejected;
        this.total_payout = total_payout;
        this.paid = paid;
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(int submitted) {
        Submitted = submitted;
    }

    public int getNotCompleted() {
        return NotCompleted;
    }

    public void setNotCompleted(int notCompleted) {
        NotCompleted = notCompleted;
    }

    public int getApproved() {
        return Approved;
    }

    public void setApproved(int approved) {
        Approved = approved;
    }

    public int getRejected() {
        return Rejected;
    }

    public void setRejected(int rejected) {
        Rejected = rejected;
    }

    public String getTotal_payout() {
        return total_payout;
    }

    public void setTotal_payout(String total_payout) {
        this.total_payout = total_payout;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
