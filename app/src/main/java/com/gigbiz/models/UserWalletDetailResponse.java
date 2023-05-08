package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserWalletDetailResponse {

    @SerializedName("redeem_list")
    @Expose
    private List<Wallet> redeemList = null;

    public UserWalletDetailResponse(List<Wallet> redeemList) {
        super();
        this.redeemList = redeemList;
    }

    public List<Wallet> getRedeemList() {
        return redeemList;
    }

    public void setRedeemList(List<Wallet> redeemList) {
        this.redeemList = redeemList;
    }

}