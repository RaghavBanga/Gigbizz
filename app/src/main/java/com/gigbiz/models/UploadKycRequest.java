package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadKycRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_pic")
    @Expose
    private String userPic;
    @SerializedName("user_aadhar")
    @Expose
    private String userAadhar;
    @SerializedName("user_pan")
    @Expose
    private String userPan;
    @SerializedName("user_driving")
    @Expose
    private String userDriving;
    @SerializedName("pan_card_no")
    @Expose
    private String pan_card_no;
    @SerializedName("aadhar_card_no")
    @Expose
    private String aadhar_card_no;

    public UploadKycRequest(String userId, String token, String userPic, String userAadhar, String userPan, String userDriving, String pan_card_no, String aadhar_card_no) {
        this.userId = userId;
        this.token = token;
        this.userPic = userPic;
        this.userAadhar = userAadhar;
        this.userPan = userPan;
        this.userDriving = userDriving;
        this.pan_card_no = pan_card_no;
        this.aadhar_card_no = aadhar_card_no;
    }

    public String getPan_card_no() {
        return pan_card_no;
    }

    public void setPan_card_no(String pan_card_no) {
        this.pan_card_no = pan_card_no;
    }

    public String getAadhar_card_no() {
        return aadhar_card_no;
    }

    public void setAadhar_card_no(String aadhar_card_no) {
        this.aadhar_card_no = aadhar_card_no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserAadhar() {
        return userAadhar;
    }

    public void setUserAadhar(String userAadhar) {
        this.userAadhar = userAadhar;
    }

    public String getUserPan() {
        return userPan;
    }

    public void setUserPan(String userPan) {
        this.userPan = userPan;
    }

    public String getUserDriving() {
        return userDriving;
    }

    public void setUserDriving(String userDriving) {
        this.userDriving = userDriving;
    }

}