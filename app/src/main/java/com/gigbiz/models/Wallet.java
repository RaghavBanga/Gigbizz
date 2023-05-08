package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wallet {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("current_amount")
    @Expose
    private String currentAmount;
    @SerializedName("remaning_amount")
    @Expose
    private String remaningAmount;
    @SerializedName("referral_url")
    @Expose
    private String referral_url;

    public Wallet(int status, String msg, String currentAmount, String remaningAmount, String referral_url) {
        super();
        this.status = status;
        this.msg = msg;
        this.currentAmount = currentAmount;
        this.remaningAmount = remaningAmount;
        this.referral_url = referral_url;
    }

    public String getReferral_url() {
        return referral_url;
    }

    public void setReferral_url(String referral_url) {
        this.referral_url = referral_url;
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

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getRemaningAmount() {
        return remaningAmount;
    }

    public void setRemaningAmount(String remaningAmount) {
        this.remaningAmount = remaningAmount;
    }

}