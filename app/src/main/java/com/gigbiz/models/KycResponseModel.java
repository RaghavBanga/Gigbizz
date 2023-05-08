package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KycResponseModel {

    @SerializedName("kycstatus")
    @Expose
    private List<Kycstatus> kycstatus = null;


    public KycResponseModel() {
    }

    public KycResponseModel(List<Kycstatus> kycstatus) {
        super();
        this.kycstatus = kycstatus;
    }

    public List<Kycstatus> getKycstatus() {
        return kycstatus;
    }

    public void setKycstatus(List<Kycstatus> kycstatus) {
        this.kycstatus = kycstatus;
    }

}