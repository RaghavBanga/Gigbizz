package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResponse {

    @SerializedName("user_data")
    @Expose
    private List<UserDatum> userData = null;

    public SignUpResponse() {
    }

    public SignUpResponse(List<UserDatum> userData) {
        super();
        this.userData = userData;
    }

    public List<UserDatum> getUserData() {
        return userData;
    }

    public void setUserData(List<UserDatum> userData) {
        this.userData = userData;
    }

}