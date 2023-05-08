package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryResponse {

    @SerializedName("redeem_list")
    @Expose
    private List<Redeem> redeemList = null;

    public TransactionHistoryResponse(List<Redeem> redeemList) {
        super();
        this.redeemList = redeemList;
    }

    public List<Redeem> getRedeemList() {
        return redeemList;
    }

    public void setRedeemList(List<Redeem> redeemList) {
        this.redeemList = redeemList;
    }

}