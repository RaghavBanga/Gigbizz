package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kycstatus {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("kyc_status")
    @Expose
    private String kycStatus;

    public Kycstatus(String userId, String kycStatus) {
        super();
        this.userId = userId;
        this.kycStatus = kycStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

}