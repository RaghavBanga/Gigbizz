package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferLetterResponseModel {
    @SerializedName("offerletter_url")
    @Expose
    private List<OfferletterUrl> offerletterUrl;

    public List<OfferletterUrl> getOfferletterUrl() {
        return offerletterUrl;
    }

    public void setOfferletterUrl(List<OfferletterUrl> offerletterUrl) {
        this.offerletterUrl = offerletterUrl;
    }

}
