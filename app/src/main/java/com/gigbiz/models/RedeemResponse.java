package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedeemResponse {

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

    public RedeemResponse(int status, String msg, String currentAmount, String remaningAmount) {
        this.status = status;
        this.msg = msg;
        this.currentAmount = currentAmount;
        this.remaningAmount = remaningAmount;
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

}