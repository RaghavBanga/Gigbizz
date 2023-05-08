package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDetailResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("emp_code")
    @Expose
    private String emp_code;
    @SerializedName("user_no")
    @Expose
    private String userNo;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("doj")
    @Expose
    private String doj;
    @SerializedName("manager_name")
    @Expose
    private String managerName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("user_pic")
    @Expose
    private String userPic;
    @SerializedName("icard")
    @Expose
    private String icard;
    @SerializedName("offerletter")
    @Expose
    private String offerletter;

    public ProfileDetailResponse(Integer status, String msg, String userId, String emp_code, String userNo, String token, String userStatus, String dob, String doj, String managerName, String name, String mobile, String type, String address, String userPic, String icard, String offerletter) {
        this.status = status;
        this.msg = msg;
        this.userId = userId;
        this.emp_code = emp_code;
        this.userNo = userNo;
        this.token = token;
        this.userStatus = userStatus;
        this.dob = dob;
        this.doj = doj;
        this.managerName = managerName;
        this.name = name;
        this.mobile = mobile;
        this.type = type;
        this.address = address;
        this.userPic = userPic;
        this.icard = icard;
        this.offerletter = offerletter;
    }

    public String getIcard() {
        return icard;
    }

    public void setIcard(String icard) {
        this.icard = icard;
    }

    public String getOfferletter() {
        return offerletter;
    }

    public void setOfferletter(String offerletter) {
        this.offerletter = offerletter;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

}