package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendBankRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("account_holder_name")
    @Expose
    private String accountHolderName;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("account_type")
    @Expose
    private String accountType;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;

    public SendBankRequest(String userId, String token, String bankName, String accountHolderName, String accountNo, String accountType, String ifscCode) {
        super();
        this.userId = userId;
        this.token = token;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.ifscCode = ifscCode;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

}