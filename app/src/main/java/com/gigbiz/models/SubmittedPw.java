package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmittedPw {
    @SerializedName("report_id")
    @Expose
    private String reportId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("seller_email_id")
    @Expose
    private String sellerEmailId;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("alt_phone_no")
    @Expose
    private String altPhoneNo;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("book_store_name")
    @Expose
    private String bookStoreName;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("shop_location_link")
    @Expose
    private String shopLocationLink;
    @SerializedName("seller_supplying_book_school")
    @Expose
    private String sellerSupplyingBookSchool;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("no_of_staff")
    @Expose
    private String noOfStaff;
    @SerializedName("montly_income")
    @Expose
    private String monthlyIncome;
    @SerializedName("shop_pic")
    @Expose
    private String shopPic;
    @SerializedName("shop_front_pic")
    @Expose
    private String shopFrontPic;
    @SerializedName("passbook_cheque_pic")
    @Expose
    private String passbookChequePic;
    @SerializedName("pan_card")
    @Expose
    private String panCard;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("gst_no")
    @Expose
    private String gstNo;
    @SerializedName("pw_emp_email")
    @Expose
    private String pwEmpEmail;
    @SerializedName("pw_emp_name")
    @Expose
    private String pwEmpName;
    @SerializedName("pw_emp_id")
    @Expose
    private String pwEmpId;
    @SerializedName("discount_coupon_code")
    @Expose
    private String discount_coupon_code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reason")
    @Expose
    private String reason;


    public SubmittedPw(String reportId, String userId, String projectId, String name, String email, String age, String gender, String sellerEmailId, String phoneNo, String altPhoneNo, String education, String state, String city, String bookStoreName, String storeAddress, String pincode, String shopLocationLink, String sellerSupplyingBookSchool, String schoolName, String noOfStaff, String monthlyIncome, String shopPic, String shopFrontPic, String passbookChequePic, String panCard, String accountNo, String accountName, String bankName, String ifscCode, String panNo, String gstNo, String pwEmpEmail, String pwEmpName, String pwEmpId, String discount_coupon_code, String status, String reason) {
        this.reportId = reportId;
        this.userId = userId;
        this.projectId = projectId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.sellerEmailId = sellerEmailId;
        this.phoneNo = phoneNo;
        this.altPhoneNo = altPhoneNo;
        this.education = education;
        this.state = state;
        this.city = city;
        this.bookStoreName = bookStoreName;
        this.storeAddress = storeAddress;
        this.pincode = pincode;
        this.shopLocationLink = shopLocationLink;
        this.sellerSupplyingBookSchool = sellerSupplyingBookSchool;
        this.schoolName = schoolName;
        this.noOfStaff = noOfStaff;
        this.monthlyIncome = monthlyIncome;
        this.shopPic = shopPic;
        this.shopFrontPic = shopFrontPic;
        this.passbookChequePic = passbookChequePic;
        this.panCard = panCard;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.panNo = panNo;
        this.gstNo = gstNo;
        this.pwEmpEmail = pwEmpEmail;
        this.pwEmpName = pwEmpName;
        this.pwEmpId = pwEmpId;
        this.discount_coupon_code = discount_coupon_code;
        this.status = status;
        this.reason = reason;
    }

    public String getDiscount_coupon_code() {
        return discount_coupon_code;
    }

    public void setDiscount_coupon_code(String discount_coupon_code) {
        this.discount_coupon_code = discount_coupon_code;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSellerEmailId() {
        return sellerEmailId;
    }

    public void setSellerEmailId(String sellerEmailId) {
        this.sellerEmailId = sellerEmailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBookStoreName() {
        return bookStoreName;
    }

    public void setBookStoreName(String bookStoreName) {
        this.bookStoreName = bookStoreName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getShopLocationLink() {
        return shopLocationLink;
    }

    public void setShopLocationLink(String shopLocationLink) {
        this.shopLocationLink = shopLocationLink;
    }

    public String getSellerSupplyingBookSchool() {
        return sellerSupplyingBookSchool;
    }

    public void setSellerSupplyingBookSchool(String sellerSupplyingBookSchool) {
        this.sellerSupplyingBookSchool = sellerSupplyingBookSchool;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getNoOfStaff() {
        return noOfStaff;
    }

    public void setNoOfStaff(String noOfStaff) {
        this.noOfStaff = noOfStaff;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getShopFrontPic() {
        return shopFrontPic;
    }

    public void setShopFrontPic(String shopFrontPic) {
        this.shopFrontPic = shopFrontPic;
    }

    public String getPassbookChequePic() {
        return passbookChequePic;
    }

    public void setPassbookChequePic(String passbookChequePic) {
        this.passbookChequePic = passbookChequePic;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPwEmpEmail() {
        return pwEmpEmail;
    }

    public void setPwEmpEmail(String pwEmpEmail) {
        this.pwEmpEmail = pwEmpEmail;
    }

    public String getPwEmpName() {
        return pwEmpName;
    }

    public void setPwEmpName(String pwEmpName) {
        this.pwEmpName = pwEmpName;
    }

    public String getPwEmpId() {
        return pwEmpId;
    }

    public void setPwEmpId(String pwEmpId) {
        this.pwEmpId = pwEmpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}